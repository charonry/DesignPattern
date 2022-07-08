package com.charon.patterns.creationalpatterns;


/**
 * @program: DesignPattern
 * @description 创建型模式------>工厂模式
 * @author: charon
 * @create: 2022-07-08 22:10
 **/
public class FactoryPatterns {

    public static void main(String[] args) {
        FoodFactory foodFactory = new FoodFactory();
        Food cake = foodFactory.getFood("CAKE");
        cake.createFood();
    }
}

 interface Food{
    void createFood();
}

class Cake implements Food{

    public void createFood() {
        System.out.println("制作蛋糕");
    }
}

class Meat implements Food{

    public void createFood() {
        System.out.println("制作肉食");
    }
}

class FoodFactory{
    public Food getFood(String type){
        if("CAKE".equals(type)){
            return new Cake();
        }
        if("MEAT".equals(type)){
            return new Meat();
        }
        return null;
    }
}