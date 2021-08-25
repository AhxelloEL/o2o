package com.al.o2o.enums;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.enums
 * @EnumName:ShopAuthMapEnum
 * @Description 店铺授权枚举类
 * @date2021/8/25 10:44
 */
public enum ShopAuthMapStateEnum {
    SUCCESS(1,"操作成功"),INNER_ERROR(-1001,"操作失败"),
    NULL_SHOPAUTH_ID(-1002,"ShopAuthId为空白"),NULL_SHOPAUTH_INFO(-1003,"ShopAuthInfo为空");

    private int state;
    private String stateInfo;

    ShopAuthMapStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    /**
     * 依据传入的state返回相应的enum值
     */
    public static ShopAuthMapStateEnum stateOf(int state){
        for (ShopAuthMapStateEnum stateEnum :values()){
            if (stateEnum.getState() == state) {
                return stateEnum;
            }
        }
        return null;
    }

}
