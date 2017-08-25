package org.redrock.support;

import net.sf.json.JSONObject;
import org.redrock.util.Const;
import org.redrock.util.CurlUtil;
import org.redrock.util.DatabaseUtil;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class AccessToken {

    private static void updateAccesstoken(Map<String, Object> data) {
        try {
            PreparedStatement psql;
            Connection connection = DatabaseUtil.getConnection();
            String sql = "update t_token set access_token = ?, createtime = ?";
            psql = connection.prepareStatement(sql);
            psql.setString(1, (String) data.get("accessToken"));
            psql.setInt(2, (Integer) data.get("timestamp"));
            psql.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Object> curlForAccessToken() {
        String appId = Const.AppId;
        String appSecret = Const.AppSecret;
        StringBuilder temp = new StringBuilder();
        temp.append("https://api.weixin.qq.com/cgi-bin/token?")
                .append("grant_type=client_credential")
                .append("&appid=").append(appId)
                .append("&secret=").append(appSecret);
        String tokenUrl = temp.toString();
        String jsonStr = CurlUtil.getContent(tokenUrl, null, "POST");
        JSONObject json = JSONObject.fromObject(jsonStr);
        String accessToken = json.getString("access_token");
        int timestamp = (int) (System.currentTimeMillis()/1000);
        Map<String, Object> data = new HashMap<>();
        data.put("accessToken", accessToken);
        data.put("timestamp", timestamp);
        return data;
    }

    public static String getAccessToken() {
        String accessToken = null;
        try {
            Connection connection = DatabaseUtil.getConnection();
            int tomestamp = (int) (System.currentTimeMillis() / 1000) - 7200;
            String sql = "select * from t_token where createtime > " + tomestamp;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                accessToken = resultSet.getString("access_token");
            }
            if (accessToken == null) {
                Map<String, Object> data = curlForAccessToken();
                updateAccesstoken(data);
                accessToken = (String) data.get("accessToken");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accessToken;
    }

}