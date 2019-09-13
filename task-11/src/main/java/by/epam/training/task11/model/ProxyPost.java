package by.epam.training.task11.model;

import by.epam.training.task11.annotations.After;
import by.epam.training.task11.annotations.Before;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
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
        Optional<Before> beforeClass = Optional.ofNullable(Post.class.getAnnotation(Before.class));
        beforeClass.ifPresent(beforePrint);
        Optional<Before> beforeMethod = Optional.ofNullable(post.getClass().getMethod(method.getName()).getAnnotation(Before.class));
        beforeMethod.ifPresent(beforePrint);
        method.invoke(post, args);
        Optional<After> afterClass = Optional.ofNullable(Post.class.getAnnotation(After.class));
        afterClass.ifPresent(afterPrint);
        Optional<After> afterMethod = Optional.ofNullable(post.getClass().getMethod(method.getName()).getAnnotation(After.class));
        afterMethod.ifPresent(afterPrint);
        /*System.out.println("ProxyPost invoke : " + method.getName());

        */
        return null;
    }
}

