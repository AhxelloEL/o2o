package com.al.o2o.web.wechat;

import com.al.o2o.dto.UserAccessToken;
import com.al.o2o.dto.WechatAuthExecution;
import com.al.o2o.dto.WechatUser;
import com.al.o2o.entity.PersonInfo;
import com.al.o2o.entity.WechatAuth;
import com.al.o2o.enums.WechatAuthStateEnum;
import com.al.o2o.service.PersonInfoService;
import com.al.o2o.service.WechatAuthService;
import com.al.o2o.util.wechat.WechatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.web.wechat
 * @ClassName:WechatLoginController
 * @Description https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1b2fc203b1aae42f&redirect_uri=http://xiahuicheng.cn/o2o/wechatlogin/logincheck&role_type=1&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect
 * @date2021/6/29 15:42
 */
@Controller
@RequestMapping("wechatlogin")
/**
 *
 */
public class WechatLoginController {
    private static Logger log = LoggerFactory.getLogger(WechatLoginController.class);

    private static String FROMEND = "1";

    private static String SHOPEND = "2";
    @Autowired
    private WechatAuthService wechatAuthService;
    @Autowired
    private PersonInfoService personInfoService;
    @RequestMapping(value = "/logincheck",method = {RequestMethod.GET})
    public String doGet(HttpServletRequest request, HttpServletResponse response){
        log.debug("wechat login get ....");
        //获取微信公众号传输过来的code,通过code可获取access_token，进而获取用户信息
        String code = request.getParameter("code");
        //TODO 定义state传自定义信息，方便程序调用
        String roleType = request.getParameter("state");
        log.debug("wechar login code :"+code);
        WechatUser user = null;
        String openId = null;
        WechatAuth auth = null;
        if (code != null){
            UserAccessToken token;
            try {
                //通过code获取access_token
                token = WechatUtil.getUserAccessToken(code);
                log.debug("wechat login token:" +token.toString());
                //通过token获取accessToken
                String accessToken = token.getAccessToken();
                //通过token获取openId
                openId = token.getOpenId();
                //通过access_token和openId获取用户昵称等信息
                user = WechatUtil.getUserInfo(accessToken,openId);
                log.debug("wechat login user:" +user.toString());
                request.getSession().setAttribute("openId",openId);
                auth = wechatAuthService.getWechatAuthByOpenId(openId);
            }catch (IOException e){
                log.error("error in getUserAccessToken or getUserInfo");
                e.printStackTrace();
            }
        }
        if (auth == null){
            PersonInfo personInfo = WechatUtil.getPersonInfoFromRequest(user);
            auth = new WechatAuth();
            auth.setOpenId(openId);
            if (FROMEND.equals(roleType)){
                personInfo.setUserType(1);
            }else {
                personInfo.setUserType(2);
            }
            auth.setPersonInfo(personInfo);
            WechatAuthExecution wechatAuthExecution = wechatAuthService.register(auth);
            if (wechatAuthExecution.getState() != WechatAuthStateEnum.SUCCESS.getState()){
                return null;
            }else {
                personInfo = personInfoService.getPersonInfoById(auth.getPersonInfo().getUserId());
                request.getSession().setAttribute("user",personInfo);
            }
        }else {
            request.getSession().setAttribute("user",auth.getPersonInfo());
        }
        if (roleType.equals(FROMEND)){
            return "frontend/index";
        }else {
            return "shop/shoplist";
        }
    }
}
