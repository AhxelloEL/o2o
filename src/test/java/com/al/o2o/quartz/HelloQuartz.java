package com.al.o2o.quartz;

import org.junit.Ignore;
import org.junit.Test;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.quartz
 * @ClassName:HelloQuartz
 * @Description Hello
 * @date2021/8/18 19:19
 */
public class HelloQuartz implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间："+sdf.format(date));
        System.out.println("Hello Quartz");
    }


    @Test
    @Ignore
    public void simpeDateFormat(){
        /**
         * 第一种方式获取当前时间并格式化输出
         */
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("----------------DateSimpleDateFormat Time ----------------------");
        System.out.println(simpleDateFormat.format(date));
        /**
         * 第二种 JAVA8的新API
         * LocalDateTime
         * LocalDate
         * LocalTime
         */
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        System.out.println("----------------localDateTimeNow Time ----------------------");
        System.out.println(localDateTimeNow);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("----------------localDateTimeFormatter Time ----------------------");
        System.out.println(dateTimeFormatter.format(localDateTimeNow));
        System.out.println(localDateTimeNow.format(dateTimeFormatter));
        /**
         * LocalDate
         * 获取年月日
         */
        LocalDate localDateNow = LocalDate.now();
        System.out.println("----------------LocalDate Time ----------------------");
        System.out.println(localDateNow);
        /**
         * LocalTime
         */
        LocalTime localTimeNow = LocalTime.now();
        System.out.println(localTimeNow);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println(dtf.format(localTimeNow));
        /**
         * LocalDate+ LocalTime
         */
        System.out.println(localDateNow+" "+dtf.format(localTimeNow));
    }
}
