package org.redrock.support;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.redrock.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Button {

    private String type;
    private String name;
    private String key;
    private String url;
    private String mediaId;
    private String appid;
    private String miniprogram;
    private String pagepath;
    private List<Button> subButton;





    @Override
    public String toString() {
        Map<String, String> button = new HashMap<>();
        if (!StringUtil.isBlank(type)) button.put("type", type);
        if (!StringUtil.isBlank(name)) button.put("name", name);
        if (!StringUtil.isBlank(key)) button.put("key", key);
        if (!StringUtil.isBlank(url)) button.put("url", url);
        if (!StringUtil.isBlank(mediaId)) button.put("mediaId", mediaId);
        if (!StringUtil.isBlank(appid)) button.put("appid", appid);
        if (!StringUtil.isBlank(miniprogram)) button.put("miniprogram", miniprogram);
        if (!StringUtil.isBlank(pagepath)) button.put("pagepath", pagepath);
        JSONObject jsonObject = JSONObject.fromObject(button);
        if (subButton != null && subButton.size() > 0) {
            JSONArray buttonsData = new JSONArray();
            for (int i = 0; i < subButton.size(); i++) {
                Button b = subButton.get(i);
                String data = b.toString();
                buttonsData.add(data);
            }
            jsonObject.put("sub_button", buttonsData);
        }
        String json = jsonObject.toString();
        return json;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMiniprogram() {
        return miniprogram;
    }

    public void setMiniprogram(String miniprogram) {
        this.miniprogram = miniprogram;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }

    public List<Button> getSubButton() {
        return subButton;
    }

    public void setSubButton(List<Button> subButton) {
        this.subButton = subButton;
    }
}
