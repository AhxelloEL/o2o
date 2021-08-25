package com.al.o2o.util;

/**
 * rowIndex转换工具
 */
public class PageCalculator {
    public static int calculateRowIndex(int pageIndex,int pageSize){
        //pageIndex是我们的页码，我们的页码值为1，就表示选取第一页的数据，
        //如果第一页的pageSize等于5，就表示一页有5条 0表示 rowIndex 就从第0条开始去取选5条数据
        return (pageIndex > 0) ?(pageIndex-1)*pageSize :0;
    }
}
