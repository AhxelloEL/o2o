package com.al.o2o.exceptions;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.exceptions
 * @ClassName:LocalAuthOperationException
 * @Description 本地账号异常类封装
 * @date2021/8/4 16:39
 */
public class LocalAuthOperationException extends RuntimeException {
    private static final long serialVersionUID = -715557694398638462L;

    public LocalAuthOperationException(String msg){
        super(msg);
    }
}
