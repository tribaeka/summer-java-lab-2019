package by.epam.training.task11.model;

import by.epam.training.task11.annotations.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;

public class ProxyPost implements InvocationHandler {
    private Post post;
    Consumer<Before> beforePrint = before -> System.out.println(before.message());
    Consumer<After> afterPrint = after -> System.out.println(after.message());
    public ProxyPost(Post post) {
        this.post = post;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Optional<Ignore> ignore = Optional.ofNullable(post.getClass().getMethod(method.getName())
                .getAnnotation(Ignore.class));
        if (ignore.isPresent()) return method.invoke(post, args);
        doBefore(method);
        method.invoke(post, args);
        doAfter(method);
        return null;
    }

    private void doBefore(Method method) throws NoSuchMethodException {
        Optional<BeforeRepeatable> befores = Optional.ofNullable(Post.class.getAnnotation(BeforeRepeatable.class));
        if (befores.isPresent()){
            Arrays.stream(befores.get().value()).forEach(beforePrint);
        }else {
            Optional<Before> beforeClass = Optional.ofNullable(Post.class.getAnnotation(Before.class));
            beforeClass.ifPresent(beforePrint);
        }

        befores = Optional.ofNullable(post.getClass().getMethod(method.getName())
                .getAnnotation(BeforeRepeatable.class));
        if (befores.isPresent()){
            Arrays.stream(befores.get().value()).forEach(beforePrint);
        }else {
            Optional<Before> beforeMethod = Optional.ofNullable(post.getClass().getMethod(method.getName())
                    .getAnnotation(Before.class));
            beforeMethod.ifPresent(beforePrint);
        }
    }

    private void doAfter(Method method) throws NoSuchMethodException {
        Optional<AfterRepeatable> afters = Optional.ofNullable(Post.class.getAnnotation(AfterRepeatable.class));
        if (afters.isPresent()){
            Arrays.stream(afters.get().value()).forEach(afterPrint);
        }else {
            Optional<After> afterClass = Optional.ofNullable(Post.class.getAnnotation(After.class));
            afterClass.ifPresent(afterPrint);
        }

        afters = Optional.ofNullable(post.getClass().getMethod(method.getName())
                .getAnnotation(AfterRepeatable.class));
        if (afters.isPresent()){
            Arrays.stream(afters.get().value()).forEach(afterPrint);
        }else {
            Optional<After> afterMethod = Optional.ofNullable(post.getClass().getMethod(method.getName())
                    .getAnnotation(After.class));
            afterMethod.ifPresent(afterPrint);
        }
    }
}

