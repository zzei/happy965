package com.zei.happy.cache;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 静态变量保存spring环境，使在项目内任意地方可以使用spring环境
 */
@Component
public class SpringContextHolder implements ApplicationContextAware{

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     * 获取bean
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name){
        return (T) applicationContext.getBean(name);
    }

}