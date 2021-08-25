package com.al.o2o.exceptions;

/**
 * @author Xiahuicheng
 */
public class ProductCategoryOperationException extends RuntimeException{
    private static final long serialVersionUID = -4975602655213231686L;

    /**
     * 对ProductCategory中的RuntimeException进行封装
     * @param msg
     */
    public ProductCategoryOperationException(String msg){
        super(msg);
    }


}
