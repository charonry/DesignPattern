package com.charon.patterns.behavioralpatterns;

/**
 * @program: DesignPattern
 * @description 行为型模式------>策略模式
 * @author: charon
 * @create: 2020-02-20 14:23
 **/
public class StrategyPatterns {
    public static void main(String[] args) {
        Calculater calculater = new Sub();
        Context context = new Context(calculater);
        int result = context.contextCalc(2, 3);
        System.out.println("计算结果值为:"+ result);
    }
}

interface Calculater{
    int calc(int a,int b);
}

class Add implements Calculater{
    @Override
    public int calc(int a, int b) {
        return a + b;
    }
}

class Sub implements  Calculater{
    @Override
    public int calc(int a, int b) {
        return a - b;
    }
}

/**
 * 具体运行环境
 */
class Context{
    private Calculater calculater;

    public Context(Calculater calculater) {
        this.calculater = calculater;
    }

    public int contextCalc(int a, int b){
        return calculater.calc(a,b);
    }
}

