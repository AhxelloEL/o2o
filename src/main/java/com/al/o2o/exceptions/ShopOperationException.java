package com.al.o2o.exceptions;

/**
 * @author Xiahuicheng
 */
public class ShopOperationException extends RuntimeException{
    private static final long serialVersionUID = 7466789123843346429L;

    /**
     * 对shop中的RuntimeException进行封装
     * @param msg
     */
    public ShopOperationException(String msg){
        super(msg);
    }


}
