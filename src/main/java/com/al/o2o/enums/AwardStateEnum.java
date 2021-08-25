package com.al.o2o.enums;

/**
 * @author yunSun
 */

public enum AwardStateEnum {
    OFFLINE(-1, "非法奖品"), SUCCESS(0, "操作成功"), INNER_ERROR(-1001, "操作失败"), EMPTY(
            -1002, "奖品信息为空");
    private int state;
    private String stateInfo;

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

     AwardStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * 依据传入的state返回相应的enum值
     * @param state
     * @return
     */
    public static AwardStateEnum stateOf(int state){
        for (AwardStateEnum stateEnum :values()){
            if (stateEnum.getState() == state) {
                return stateEnum;
            }
        }
        return null;
    }
}
