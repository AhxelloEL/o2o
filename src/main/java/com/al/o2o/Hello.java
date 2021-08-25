package com.al.o2o;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o
 * @ClassName:Hello
 * @Description 搭建SpringBoot框架测试
 * @date2021/8/13 11:05
 */
@RestController
public class Hello {

    @RequestMapping(value = "hello",method = RequestMethod.GET)
    public String Hello(){
        return "Hello SpringBoot !!!";
    }
}
