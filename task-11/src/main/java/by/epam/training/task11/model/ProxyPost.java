package by.epam.training.task11.model;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyPost implements InvocationHandler {
    private Post post;

    public ProxyPost(Post post) {
        this.post = post;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("ProxyPost invoke : " + method.getName());
        return method.invoke(post, args) ;
    }
}
