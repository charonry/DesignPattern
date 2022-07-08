package com.charon.patterns.creationalpatterns;

import com.charon.entity.User;

/**
 * @program: DesignPattern
 * @description 单例模式
 * @author: charon
 * @create: 2020-04-14 11:25
 **/
public class SingletonPatterns {
    public static void main(String[] args) {
        // 枚举
        System.out.println(Singleton.getSingleton());
        System.out.println(Singleton.getSingleton() == Singleton.getSingleton());
    }
}

/**
 * 单例模式的枚举方式
 */
enum Singleton {
    INSTANCE;

    private User user;

    /**
     * 枚举的构造方法就是private
     */
    Singleton() {
        user = new User();
    }

    private User getInstance() {
        return user;
    }

    public static User getSingleton() {
        return Singleton.INSTANCE.getInstance();
    }
}