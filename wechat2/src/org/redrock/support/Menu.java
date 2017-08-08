package org.redrock.support;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.redrock.util.CurlUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu {
    private List<Button> button;

    public static void main(String[] args) {
        Button button1 = new Button();
        button1.setType("scancode_waitmsg");
        button1.setName("扫码带提示");
        button1.setKey("rselfmenu_0_0");

        Button button2 = new Button();
        button2.setType("scancode_push");
        button2.setName("扫码推事件");
        button2.setKey("rselfmenu_0_1");

        Button button3 = new Button();
        button3.setName("扫码");

        List<Button> subButtons = new ArrayList<>();
        subButtons.add(button1);
        subButtons.add(button2);
        button3.setSubButton(subButtons);

        List<Button> button = new ArrayList<>();
        button.add(button3);
        Menu menu = new Menu();
        menu.setButton(button);

        //json
        String menuJson = menu.toString();
        String accessToken = AccessToken.getAccessToken();
        StringBuilder temp = new StringBuilder();
        temp.append(" https://api.weixin.qq.com/cgi-bin/menu/create?access_token=")
                .append(accessToken);
        String url = temp.toString();
        String result = CurlUtil.postData(url, menuJson);
        System.out.println(result);
    }


    @Override
    public String toString() {
        Map<String, String> data = new HashMap<>();
        JSONObject menu = new JSONObject();
        JSONArray buttonData = new JSONArray();
        if (button != null && button.size() > 0) {
            for (int i =0; i < button.size(); i++) {
                Button b = button.get(i);
                buttonData.add(b.toString());
            }
        }
        menu.put("button", buttonData);
        return menu.toString();
    }

    public List<Button> getButton() {
        return button;
    }

    public void setButton(List<Button> button) {
        this.button = button;
    }
}
