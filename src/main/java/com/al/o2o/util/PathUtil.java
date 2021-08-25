package com.al.o2o.util;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author yunSun
 */
public class PathUtil {
    /**
     * 获取文件分隔符
     */
    private static String seperator = System.getProperty("file.separator");

    public static String getImgBasePath(){
        String os = System.getProperty("os.name");
        String basePath="";
        //判断操作系统,而决定图片存储地址
        //建议将图片保存到服务器中，用URL引用
        if (os.toLowerCase().startsWith("win")){
            basePath="D:/projectdev/image";
        }else {
            basePath ="/Users/al/work/image";
        }
        basePath = basePath.replace("/",seperator);
        return basePath;
    }
    /**
     *  获取店铺图片路径
     */
    public static String getShopImagePath(Long shopId){
        //图片存储子路径
        String imagePath = "/upload/images/item/shop/" + shopId + "/";
        return imagePath.replace("/",seperator);
       // return imagePath.replace("/",seperator);
    }
    public static String getHeadLineImagePath() {
        String imagePath = "/upload/images/item/headtitle/";
        return imagePath.replace("/", seperator);
    }
    public static String getShopCategoryPath() {
        String imagePath = "/upload/images/item/shopcategory/";
        return imagePath.replace("/", seperator);
    }

    //-------------------------------------GET/SET---------------------------------------------
    /**
     * WIN系统图片路径
     */
    private static String winBasePath;
    /**
     * Liunx系统图片路径
     */
    private static String liunxBasePath;
    /**
     * 店铺图片路径
     */
    private static String shopImgPath;
    /**
     * 头条图片路径
     */
    private static String headLineImgPath;
    /**
     * 店铺类型图片路径
     */
    private static String shopCategoryImgPath;
    @Value("${win.base.path}")
    public void setWinBasePath(String winBasePath) {
        PathUtil.winBasePath = winBasePath;
    }
    @Value("${linux.base.path}")
    public void setLiunxBasePath(String liunxBasePath) {
        PathUtil.liunxBasePath = liunxBasePath;
    }
    @Value("${shop.relevant.path}")
    public void setShopImgPath(String shopImgPath) {
        PathUtil.shopImgPath = shopImgPath;
    }
    @Value("${headline.relevant.path}")
    public void setHeadLineImgPath(String headLineImgPath) {
        PathUtil.headLineImgPath = headLineImgPath;
    }
    @Value("${shopcategory.relevant.path}")
    public void setShopCategoryImgPath(String shopCategoryImgPath) {
        PathUtil.shopCategoryImgPath = shopCategoryImgPath;
    }
}
