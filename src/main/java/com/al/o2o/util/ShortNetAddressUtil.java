package com.al.o2o.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.util
 * @ClassName:ShortNetAddressUtil
 * @Description 使用xiaomark的短链接服务生成短链接
 * @date2021/8/30 10:18
 */
public class ShortNetAddressUtil {
    static String actionUrl = "https://api.xiaomark.com/v1/link/create";

    static String APIKEY = "c1428ef24786825ea7218d1b5108879d";



    public static void main(String[] args) {
        String longUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1b2fc203b1aae42f&redirect_uri=http://xiahuicheng.cn/o2o/wechatlogin/logincheck&role_type=1&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect\n";
        System.out.println(getShortURL(longUrl));

    }

    @SuppressWarnings("deprecation")
    public static String getShortURL(String longUrl) {
        //longUrl = java.net.URLEncoder.encode(longUrl);
        //传好json数据
        String apikey = APIKEY;
        JSONObject param = new JSONObject();
        param.put("apikey",apikey);
        param.put("origin_url",longUrl);
        //利用RestTemplate进行第三方接口的调用
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        //设置超时时间30s
        requestFactory.setConnectTimeout(30*1000);
        requestFactory.setReadTimeout(30*1000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        ResponseEntity<JSONObject> jsonObjectResponseEntity = restTemplate.postForEntity(actionUrl,param,JSONObject.class);
        //将得到的json对象的body部分进行处理
        JSONObject json = jsonObjectResponseEntity.getBody();
        if (json == null) {
            return "";
        }
        //读取多层嵌套的json数据
        return json.getJSONObject("data").getJSONObject("link").getString("url");
    }
}