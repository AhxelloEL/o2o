package com.al.o2o.exceptions;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.exceptions
 * @ClassName:WechatAuthOperationException
 * @Description 封装WechatAuth异常
 * @date2021/8/3 12:33
 */
public class WechatAuthOperationException extends RuntimeException {

    private static final long serialVersionUID = 6843626995013344917L;

    public WechatAuthOperationException(String msg){
        super(msg);
    }
}
