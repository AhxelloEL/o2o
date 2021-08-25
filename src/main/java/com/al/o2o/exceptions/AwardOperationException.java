package com.al.o2o.exceptions;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.exceptions
 * @ClassName:AwardOperationException
 * @Description RunTimeException异常类封装
 * @date2021/8/20 14:19
 */
public class AwardOperationException extends RuntimeException{
    private static final long serialVersionUID = -7439553954604599153L;

    public AwardOperationException(String msg) {
        super(msg);
    }
}
