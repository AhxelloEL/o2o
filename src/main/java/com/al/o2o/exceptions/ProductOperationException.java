package com.al.o2o.exceptions;

/**
 * @author Xiahuicheng
 */
public class ProductOperationException extends RuntimeException{
    private static final long serialVersionUID = 1959348211981558582L;

    /**
     * 对ProductCategory中的RuntimeException进行封装
     * @param msg
     */
    public ProductOperationException(String msg){
        super(msg);
    }


}
