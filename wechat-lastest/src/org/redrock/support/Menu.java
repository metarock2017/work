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
//        Button button1 = new Button();
//        button1.setType("scancode_waitmsg");
//        button1.setName("扫码带提示");
//        button1.setKey("rselfmenu_0_0");
//
//        Button button2 = new Button();
//        button2.setType("scancode_push");
//        button2.setName("扫码推事件");
//        button2.setKey("rselfmenu_0_1");
//
//        Button button3 = new Button();
//        button3.setName("扫码");



        Button button1 = new Button();
        button1.setType("click");
        button1.setName("谁是卧底" );
        button1.setKey("1");

        Button button2 = new Button();
        button2.setType("click");
        button2.setName("杀人游戏");
        button2.setKey("2");

        Button button3 = new Button();
        button3.setType("click");
        button3.setName("狼人游戏");
        button3.setKey("3");

        Button button4 = new Button();
        button4.setName("谁是卧底");

        List<Button> subButtons = new ArrayList<>();
        subButtons.add(button1);
        subButtons.add(button2);
        subButtons.add(button3);
        button4.setSubButton(subButtons);

        Button button5 = new Button();
        button5.setType("view");
        button5.setName("百度");
        button5.setUrl("https://www.baidu.com/");
        button5.setKey("4");

        Button button6 = new Button();
        button6.setType("view");
        button6.setName("视频");
        button6.setUrl("http://www.iqiyi.com/");
        button6.setKey("5");

        Button button7 = new Button();
        button7.setName("在线玩卧底");

        List<Button> subButtons2 = new ArrayList<>();
        subButtons2.add(button5);
        subButtons2.add(button6);
        button7.setSubButton(subButtons2);

        Button button8 = new Button();
        button8.setType("click");
        button8.setName("赞一下我们");
        button8.setKey("6");

        Button button9 = new Button();
        button9.setType("pic_sysphoto");
        button9.setName("系统拍照发图");
        button9.setKey("7");

        Button button10 = new Button();
        button10.setName("玩游戏");

        List<Button> subButtons3 = new ArrayList<>();
        subButtons3.add(button8);
        subButtons3.add(button9);
        button10.setSubButton(subButtons3);

        List<Button> button = new ArrayList<>();
        button.add(button4);
        button.add(button7);
        button.add(button10);
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
