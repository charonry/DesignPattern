package com.charon.patterns.behavioralpatterns;

import com.charon.entity.User;

import java.util.Objects;

/**
 * @program: DesignPattern
 * @description 行为型模式------>责任链模式
 * @author: charon
 * @create: 2022-07-08 15:55
 **/
public class ChainOfResponsibilityPatterns {
    public static void main(String[] args) {
        User user = new User("1","charon",7);
        handChain(user).submit(user);
    }

    /**
     * 责任链的封装
     */
    private static Handler handChain(User user) {
        HandlerA handlerA = new HandlerA(3);
        HandlerB handlerB = new HandlerB(6);
        HandlerC handlerC = new HandlerC(9);
        handlerA.setNextHandler(handlerB);
        handlerB.setNextHandler(handlerC);
        return  handlerA;
    }
}

abstract class Handler{

    /**
     * 处理最大值
     */
    private int max;

    private Handler nextHandler;

    public Handler() {
    }

    public Handler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public Handler(int max) {
        this.max = max;
    }
    /**
     * 对于用户的处理
     * @param user 用户
     */
    protected abstract void handle(User user);

    /**
     * 提交用户信息
     * @param user 用户
     */
    public  void submit(User user){
        if(user.getAge() <= max){
            handle(user);
        }else if(Objects.nonNull(nextHandler) && user.getAge() > max) {
            nextHandler.submit(user);
        }else {
            System.out.println("没有人处理该事件...");
        }
    }
}

class HandlerA extends  Handler{

    public HandlerA(int max) {
        super(max);
    }

    @Override
    protected void handle(User user) {
        System.out.println(user.getName()+"是"+user.getAge()+"岁，HandlerA有权限处理");
    }
}

class HandlerB extends  Handler{

    public HandlerB(int max) {
        super(max);
    }
    @Override
    protected void handle(User user) {
        System.out.println(user.getName()+"是"+user.getAge()+"岁，HandlerB有权限处理");
    }
}


class HandlerC extends  Handler{

    public HandlerC(int max) {
        super(max);
    }
    @Override
    protected void handle(User user) {
        System.out.println(user.getName()+"是"+user.getAge()+"岁，HandlerC有权限处理");
    }
}
