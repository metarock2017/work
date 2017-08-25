package org.redrock.util;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GetInterfaceService {
    //调用微信服务接口
    public static String accessToken = null;
    public static long lastTime = 0;
    public static long nowTime = 0;

    //调用微信接口获得AccessToken
    public Map<String, Object> curlAccessToken(){
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String, Object> params = new HashMap<>();
        params.put("grant_type" ,"client_credential");
        params.put("secret", Const.AppSecret);
        params.put("appid", Const.AppId);

        String json = CurlUtil.getContent(url, params, "GET");
        //JSONObject jsonObject = new JSONObject(json);
        JSONObject jsonObject = JSONObject.fromObject(json);
        Map<String, Object> map = new HashMap<>();
        String accessToken = jsonObject.getString("access_token");
        int expiresIn = jsonObject.getInt("expires_in");
        map.put("accessToken", accessToken);
        map.put("expiresIn", expiresIn);
        return map;
    }

//    public String getAccessToken() {
//        TokenDao
//    }
}
