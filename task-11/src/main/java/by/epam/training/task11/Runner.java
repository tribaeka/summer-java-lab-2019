package by.epam.training.task11;

import by.epam.training.task11.model.*;

import java.lang.reflect.Proxy;

public class Runner {
    public static void main(String[] args){
        IMetaService postProxy = (IMetaService) Proxy.newProxyInstance(
                Post.class.getClassLoader(),
                Post.class.getInterfaces(),
                new ProxyService(new Post())
                );
        postProxy.printMetadataInfo();
        postProxy.printMetadataBuilder();
        ITimeService blogProxy = (ITimeService) Proxy.newProxyInstance(
                Blog.class.getClassLoader(),
                Blog.class.getInterfaces(),
                new ProxyService(new Blog())
        );
        blogProxy.printTimeBuilder();
        blogProxy.printTimeUser();
    }
}
