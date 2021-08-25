package com.al.o2o.exceptions;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.exceptions
 * @ClassName:AreaOperationException
 * @Description area封装异常
 * @date2021/8/4 10:35
 */
public class AreaOperationException extends RuntimeException {
    private static final long serialVersionUID = 5025427708093372789L;

    public AreaOperationException(String msg){
        super(msg);
    }
}
