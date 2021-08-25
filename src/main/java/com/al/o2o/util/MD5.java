package com.al.o2o.util;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.util
 * @ClassName:MD5
 * @Description
 * @date2021/8/5 13:07
 */
public class MD5 {
    /**
     * 对字符串MD5加密（小写+字母）
     * @param str 传入要加密的字符串
     * @return md5加密后的字符串
     */
    public static String getMD5(String str){
        try {
            //生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            //计算MD函数
            md.update(str.getBytes());
            //digest（）最后确定返回的MD5 hash值，返回值为8字符串，因为md5 hash值是16位的hex值，实际上就是8位的字符
            //BigInteger函数则将8位的字符串转换成16位的hex值
            return new BigInteger(1,md.digest()).toString(16);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 对字符串MD5加密（大写+字母）
     * @param s 传入要加密的字符串
     * @return 返回加密的结果
     */
    public static String MD5(String s){
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

        try {
            byte[] btInput = s.getBytes();
            //获得MD5摘要算法的MessageDigest对象
            MessageDigest mdInt = MessageDigest.getInstance("MD5");
            //使用指定的字节更新摘要
            mdInt.update(btInput);
            //获得密文
            byte[] md = mdInt.digest();
            //把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j*2];
            int k = 0;
            for (int i =0; i<j; i++){
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 &0xf];
                str[k++] = hexDigits[byte0 &0xf];
            }
            return new String(str);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String md5 = MD5.MD5("password");
        System.out.println(md5);
        String getMd5 = MD5.getMD5("newpassw1ord");
        System.out.println(getMd5);
    }
}
