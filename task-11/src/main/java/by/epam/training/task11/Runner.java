package by.epam.training.task11;

import by.epam.training.task11.model.IMetaService;
import by.epam.training.task11.model.Post;
import by.epam.training.task11.model.ProxyPost;

import java.lang.reflect.Proxy;

public class Runner {
    public static void main(String[] args) {
        Post post = new Post();
        IMetaService postProxy = (IMetaService) Proxy.newProxyInstance(
                Post.class.getClassLoader(),
                Post.class.getInterfaces(),
                new ProxyPost(post)
                );
        postProxy.printMetadataInfo();
    }
}
