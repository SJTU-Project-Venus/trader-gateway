package com.sjtu.trade.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
@Configuration
@Component("applicationHelper")
public class ApplicationHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationHelper.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    public static Object getBean(String name) {
        try {
            Object _restTemplate = getApplicationContext().getBean(name);
            return _restTemplate;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }

    public static void setApplicationContext1(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

}
