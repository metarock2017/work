package org.redrock.util;

import jdk.nashorn.internal.parser.Token;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpConnection;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.lang.Integer;
import java.lang.String;

/**
 * 用于curl调用接口，传递参数与获取结果采用输入输出流
 */
public class CurlUtil {

    private static final int DEF_CONN_TIMEOUT = 30000;
    private static final int DEF_READ_TIMEOUT = 30000;
    private static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";


    /**
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return
     */
    public static String getContent(String strUrl, Map<String, Object> params, String method) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        HttpURLConnection conn = null;
        String paramStr = null;
        String result = null;
        //有请求参数的话拼接成字符串
        if (params != null && !params.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry i : params.entrySet()) {
                try {
                    sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                paramStr = sb.toString();
            }
            if (StringUtil.isBlank(method) || method.equalsIgnoreCase("GET")) {
                strUrl = strUrl + "?" + paramStr;
            }
        }
        try {
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setRequestProperty("User-agent", userAgent);
            conn.setInstanceFollowRedirects(false);
            if (!StringUtil.isBlank(method) && method.equalsIgnoreCase("POST")) {
                conn.setRequestMethod("POST");
                if (!StringUtil.isBlank(paramStr)) {
                    conn.setDoOutput(true);
                    writer = new BufferedWriter(
                            new OutputStreamWriter(
                                    conn.getOutputStream(), "UTF-8"
                            )
                    );
                    writer.write(paramStr);
                    writer.flush();
                }
            }
            conn.connect();
            reader = new BufferedReader(
                    new InputStreamReader(
                            conn.getInputStream(), "UTF-8"
                    )
            );
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
                if (writer != null) writer.close();
                if (conn != null) conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * post传送字符串参数后获取数据
     * @param url
     * @param str
     * @return
     */
    public static String postData(String strUrl, String str) {
        String result = null;
        try {
            URL url = new URL(strUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.connect();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            connection.getOutputStream(), "UTF-8"
                    )
            );
            writer.write(str);
            writer.flush();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream(), "UTF-8"
                    )
            );
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            writer.close();
            reader.close();
            result = builder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}

