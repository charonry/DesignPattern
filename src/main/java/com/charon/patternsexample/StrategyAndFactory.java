package com.charon.patternsexample;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: DesignPattern
 * @description 工厂模式结合策略模式减少ifelse使用
 * @author: charon
 * @create: 2020-02-20 17:08
 **/
public class StrategyAndFactory {
    public static void main(String[] args) {
        Context context = new Context();

        Double ebankStrategyMoney = context.calRecharge(100D, RechargeTypeEnum.E_BANK.value());
        System.out.println(ebankStrategyMoney);

        Double busiAcctStrategy = context.calRecharge(100D, RechargeTypeEnum.BUSI_ACCOUNTS.value());
        System.out.println(busiAcctStrategy);


        Double mobileStrategy = context.calRecharge(100D, RechargeTypeEnum.MOBILE.value());
        System.out.println(mobileStrategy);
    }
}

/**
 *
 * 各种情况枚举分类
 */
enum RechargeTypeEnum{
    E_BANK(1, "网银"),
    BUSI_ACCOUNTS(2, "商户账号"),
    MOBILE(3,"手机卡充值");

    private int value;

    private String description;

    RechargeTypeEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    int value() {
        return value;
    }

    String description() {
        return description;
    }

    /**
     * 获取具体枚举方式
     * @param value
     * @return
     */
    public static RechargeTypeEnum valueOf(int value) {
        for(RechargeTypeEnum type : RechargeTypeEnum.values()) {
            if(type.value() == value) {
                return type;
            }
        }
        return null;
    }
}

/**
 * 抽象策略
 */
interface  Strategy{
    Double calRecharge(Double charge ,RechargeTypeEnum type );
}

/**
 * 具体策略处理一
 */
class  EbankStrategy implements Strategy{
    @Override
    public Double calRecharge(Double charge, RechargeTypeEnum type) {
        return charge*0.85;
    }
}

/**
 * 具体策略处理二
 */
class BusiAcctStrategy implements  Strategy{
    @Override
    public Double calRecharge(Double charge, RechargeTypeEnum type) {
        return charge*0.90;
    }
}

/**
 * 具体策略处理三
 */
class MobileStrategy implements Strategy{
    @Override
    public Double calRecharge(Double charge, RechargeTypeEnum type) {
        return charge;
    }
}

/**
 *
 * 工厂模式创建所有枚举情况
 */
class  StrategyFactory{
    /**
     * 由于都是public没有private，就没有采用先创建工厂
     */
    public static Map<Integer,Strategy> strategyMap = new HashMap();

    public StrategyFactory() {
    }

    static {
        strategyMap.put(RechargeTypeEnum.E_BANK.value(), new EbankStrategy());
        strategyMap.put(RechargeTypeEnum.BUSI_ACCOUNTS.value(), new BusiAcctStrategy());
        strategyMap.put(RechargeTypeEnum.MOBILE.value(), new MobileStrategy());
    }

    public Strategy creator(Integer type){
        return strategyMap.get(type);
    }
}


/**
 * 运行环境，取出具体策略处理
 */
class Context{
    private Strategy strategy;

    public Double calRecharge(Double charge, Integer type) {
        strategy = new StrategyFactory().creator(type);
        return strategy.calRecharge(charge, RechargeTypeEnum.valueOf(type));
    }

}