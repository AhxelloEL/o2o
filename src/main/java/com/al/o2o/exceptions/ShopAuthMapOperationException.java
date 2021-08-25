package com.al.o2o.exceptions;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.exceptions
 * @ClassName:ShopAuthMapException
 * @Description 店铺授权异常类封装
 * @date2021/8/25 10:51
 */
public class ShopAuthMapOperationException extends RuntimeException{
    private static final long serialVersionUID = -357232978048486915L;

    public ShopAuthMapOperationException(String message) {
        super(message);
    }
}
