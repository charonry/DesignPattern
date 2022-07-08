package com.charon.patterns.creationalpatterns;

/**
 * @program: DesignPattern
 * @description 创建型模式------>抽象工厂模式
 * @author: charon
 * @create: 2022-07-08 22:32
 **/
public class AbstractFactoryPatterns {
    public static void main(String[] args) {
        /*Meal breakfast = new Breakfast();
        breakfast.getEat().createEat();
        breakfast.getDrink().createDrink();
        Meal lunch = new Lunch();
        lunch.getEat().createEat();
        lunch.getDrink().createDrink()*/;
        MealProducer producer = new MealProducer();
        Meal meal = producer.getMeal("LUNCH");
        meal.getEat().createEat();
        meal.getDrink().createDrink();
    }
}

interface Eat{
    void createEat();
}

class CakeEat implements Eat{
    public void createEat() {
        System.out.println("吃蛋糕");
    }
}

class MeatEat implements Eat{
    public void createEat() {
        System.out.println("吃肉食");
    }
}

interface Drink{
    void createDrink();
}

class MilkDrink implements Drink{
    public void createDrink() {
        System.out.println("喝牛奶");
    }
}

class CoffeeDrink implements Drink{
    public void createDrink() {
        System.out.println("喝咖啡");
    }
}
abstract class Meal{
   abstract Eat getEat();
   abstract Drink getDrink();
}

class Breakfast extends Meal{
    @Override
    Eat getEat() {
        return new CakeEat();
    }

    @Override
    Drink getDrink() {
        return new CoffeeDrink();
    }
}

class Lunch extends Meal{
    @Override
    Eat getEat() {
        return new MeatEat();
    }

    @Override
    Drink getDrink() {
        return new MilkDrink();
    }
}


class MealProducer{
    public Meal getMeal(String type){
        if("BREAKFAST".equals(type)){
            return new Breakfast();
        }
        if("LUNCH".equals(type)){
            return new Lunch();
        }
        return null;
    }
}
