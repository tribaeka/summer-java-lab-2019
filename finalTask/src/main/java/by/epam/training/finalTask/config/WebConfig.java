package by.epam.training.finalTask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "by.epam.training.finalTask.controller")
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public FreeMarkerViewResolver getFreemarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setOrder(1);
        resolver.setCache(true);
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer getFreeMarkerConfigurer() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPaths("/", "/WEB-INF/views/ftl/");
        return freeMarkerConfigurer;
    }
}
