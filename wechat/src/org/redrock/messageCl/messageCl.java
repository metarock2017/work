package org.redrock.messageCl;
import org.redrock.ljj.RedisUtill;
import org.redrock.util.Const;
import org.redrock.util.DatabaseUtil;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class messageCl {
    private String respContent;
    private static Integer i;

    public static void setI(Integer i) {
        messageCl.i = i;
    }

    public String getRespContent() {
        return respContent;
    }

    public void Message(Map<String, String> requestMap) throws ServletException, IOException {

        String toUserName = requestMap.get("FromUserName");
        String msgType = requestMap.get("MsgType");
        if (Const.REQ_MESSAGE_TYPE_TEXT.equals(msgType)) {
            textCl(requestMap, toUserName);
        } else if (Const.REQ_MESSAGE_TYPE_EVENT.equals(msgType)) {
            String eventType = requestMap.get("Event");
            if (Const.EVENT_TYPE_CLICK.equals(eventType)) {
                String eventKey = requestMap.get("EventKey");
                if (eventKey.equals("1")) {
                    respContent = "正在创建谁是卧底，请输入游戏人数（4-13之间，不包括法官哦） \n" +
                            "*<a href=\"http://192.168.43.71:8080/myjsp/createwodi.jsp?openner="+toUserName+"\">点击这里自定义词语</a>";
                }
            }
        }
    }
    public boolean exist(String FromUserName) {
        try {
            Connection con = DatabaseUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement("select expiretime from room where openner=?");
            pstmt.setString(1, FromUserName);
            ResultSet set = DatabaseUtil.executeQuery(pstmt);
            if (set.next()) {
                Long expiretime = set.getLong("expiretime");
                if (expiretime > System.currentTimeMillis()) {
                    return true;
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void textCl(Map<String, String> requestMap, String FromUserName) {
        try {
            String content = (String) requestMap.get("Content");
            if (isAllNum(content)) {
                if (isfourNumber(content)) {
                    room_num(FromUserName, Integer.parseInt(content));
                } else if (Integer.parseInt(content) == 6) {
                    respContent = "游戏惩罚";
                }
            } else {
                if (exist(FromUserName)) {
                    if ("换".equals(content)) {
                        //change
                        changeWord(requestMap);
                    } else if ("改".equals(content)) {
                        //mychange
                        MychangeWord();
                    } else {
                        if (content.contains("，")) {
                            String[] as = content.split("，");
                            if (as.length == 2) {
                                List list = new ArrayList();
                                for (int i = 0; i < as.length; i++) {
                                    list.add(as[i]);
                                }

                                String openner = requestMap.get("FromUserName");
                                Connection con = DatabaseUtil.getConnection();
                                PreparedStatement pstmt = con.prepareStatement("select id, wodi_num, pingmin_num, room_num from room where openner=?");
                                pstmt.setString(1, openner);
                                ResultSet set = DatabaseUtil.executeQuery(pstmt);
                                set.next();
                                Integer id = set.getInt("id");
                                Integer wodi_num = set.getInt("wodi_num");
                                Integer pingmin_num = set.getInt("pingmin_num");
                                Long expiretime = System.currentTimeMillis() + 30 * 60 * 60 * 1000;
                                Integer room_num = set.getInt("room_num");
                                PreparedStatement pstmt1 = con.prepareStatement("update room set wodici=?, pingminci=?, expiretime=? where id=?");
                                pstmt1.setString(1, (String) list.get(0));
                                pstmt1.setString(2, (String) list.get(1));
                                pstmt1.setLong(3, expiretime);
                                pstmt1.setInt(4, id);

                                String s = "卧    底：";
                                Jedis jedis = RedisUtill.getRedisInstance();
                                List list1 = jedis.lrange("wodi:" + id, 0, -1);
                                for (Object o : list1) {
                                    s += o + "号 ";

                                    setI(0);
                                    respContent = "换题成功！您是法官，请让参与游戏的玩家对我回复【" + room_num + "】进入房间。\n" +
                                            "房    号：" + room_num + "\n" +
                                            "配    置：" + wodi_num + "个卧底，" + pingmin_num + "个平民\n" +
                                            "卧底词：" + list.get(0) + "\n" +
                                            "平民词：" + list.get(1) + "\n" +
                                            s +
                                            "回复【换】，换一组词，回复【改】，自己出题；回复【6】，查看大冒险惩罚！（一局结束后，不必重新建房，回复【换】直接换词。）\n";

                                }
                            }
                        }
                    }
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void changeWord(Map<String, String> requestMap) {
        String openner = (String) requestMap.get("FromUserName");
        try {
            Connection con = DatabaseUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement("select * from room where openner=?");
            pstmt.setString(1, openner);
            ResultSet set = DatabaseUtil.executeQuery(pstmt);
            if (set.next()) {
                Integer room_num = set.getInt("room_num");
                Integer total_num = set.getInt("total_num");
                Integer wodi_num = set.getInt("wodi_num");
                Integer pingmin_num = set.getInt("pingmin_num");
                int x = 1+(int)(Math.random()*216);
                PreparedStatement pstmt1 = DatabaseUtil.preparedStmt(con, "select * from words where id=?");
                pstmt1.setInt(1, x);
                ResultSet rs = DatabaseUtil.executeQuery(pstmt1);
                rs.next();
                String wodici = rs.getString("word_one");
                String pingminci = rs.getString("word_two");
                Long expiretime = System.currentTimeMillis()+30*60*60*1000;
                PreparedStatement pstmt2 = con.prepareStatement("insert into room(openner, room_num, total_num, wodi_num, pingmin_num, wodici, pingminci, expiretime) values(?, ?, ?, ?, ?, ?, ?, ?)");
                pstmt2.setString(1, openner);
                pstmt2.setInt(2, room_num);
                pstmt2.setInt(3, total_num);
                pstmt2.setInt(4, wodi_num);
                pstmt2.setInt(5, pingmin_num);
                pstmt2.setString(6, wodici);
                pstmt2.setString(7, pingminci);
                pstmt2.setLong(8, (expiretime));
                Integer i = DatabaseUtil.executeUpdate(pstmt2);
                if (i != 0) {
                    String s = "卧    底：";
                    Jedis jedis = RedisUtill.getRedisInstance();
                    Integer id = rs.getInt("id");
                    List list = jedis.lrange("wodi:"+id, 0, -1);
                    for (Object o : list) {
                        s += o+"号 ";
                    }
                    setI(i);
                    respContent = "换题成功！您是法官，请让参与游戏的玩家对我回复【"+room_num+"】进入房间。\n" +
                            "房    号："+room_num+"\n" +
                            "配    置："+wodi_num+"个卧底，"+pingmin_num+"个平民\n" +
                            "卧底词："+wodici+"\n" +
                            "平民词："+pingminci+"\n" +
                            s +
                            "\n" +
                            "\n" +
                            "回复【换】，换一组词，回复【改】，自己出题；回复【6】，查看大冒险惩罚！（一局结束后，不必重新建房，回复【换】直接换词。）\n";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void MychangeWord(){
        respContent = "请输入卧底词和平民词，如：状元，冠军";
    }

    public static boolean isfourNumber(String num) {
        Pattern pattern = Pattern.compile("[0-9]{4}");
        Matcher isfourNum = pattern.matcher(num);
        if (isfourNum.matches()) {
            return true;
        }
        return false;
    }

    public void room_num(String FromUserName, Integer room_num) {
        try {
            Connection con = DatabaseUtil.getConnection();
            PreparedStatement pstmt = DatabaseUtil.preparedStmt(con, "select * from room where openner=? and room_num=?");
            pstmt.setString(1, FromUserName);
            pstmt.setInt(2, room_num);
            ResultSet rs = DatabaseUtil.executeQuery(pstmt);
            String s = "卧    底：";
            if (rs.next()) {
                Jedis jedis = RedisUtill.getRedisInstance();
                Integer id = rs.getInt("id");
                List list = jedis.lrange("wodi:"+id, 0, -1);
                for (Object o : list) {
                    s += o+"号 ";
                }
                respContent = "您是法官，请让参与游戏的玩家对我回复【"+rs.getString("room_num")+"】进入房间。\n" +
                        "房    号："+rs.getString("room_num")+"\n" +
                        "配    置："+rs.getInt("wodi_num")+"个卧底，"+rs.getInt("pingmin_num")+"个平民\n" +
                        "卧底词："+rs.getString("wodici")+"\n" +
                        "平民词："+rs.getString("pingminci")+"\n" +
                        s+
                        "\n" +
                        "\n" +
                        "回复【换】，换一组词，回复【改】，自己出题；回复【6】，查看大冒险惩罚！（一局结束后，不必重新建房，回复【换】直接换词。）";
            } else {
                joinRoom(FromUserName, room_num);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void joinRoom(String FromUserName, Integer room_num){

        try {
            Connection con = DatabaseUtil.getConnection();
            PreparedStatement pstmt = DatabaseUtil.preparedStmt(con, "select * from room where room_num=?");
            pstmt.setInt(1, room_num);
            ResultSet rs = DatabaseUtil.executeQuery(pstmt);
            if (rs.next()) {
                Long expiretime = rs.getLong("expiretime");
                Long nowtime = System.currentTimeMillis();
                if (expiretime<nowtime) {
                    respContent = "房间过期了，请法官重新建房。";
                } else {
                    Integer total_num = rs.getInt("total_num");
                    Integer i = getNumber(total_num);
                    if (i==0) {
                        respContent = "房间已满，请法官重新建房。";
                    } else {
                        Integer id = rs.getInt("id");
                        Jedis jedis = RedisUtill.getRedisInstance();
                        List list = jedis.lrange("wodi:"+id, 0, -1);
                        for (Object o : list) {
                            if (i == Integer.parseInt((String) o)) {
                                //卧底
                                wodi(FromUserName, id, rs, i);
                            }
                        }
                        //平民
                        pingmin(FromUserName, id, rs, i);
                    }
                }
            } else {
                respContent = "此房不存在，请法官重新建房。";
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void pingmin(String FromUserName, Integer id, ResultSet rs, Integer i) {
        try {
            Connection con2 = DatabaseUtil.getConnection();
            PreparedStatement pstmt2 = DatabaseUtil.preparedStmt(con2, "insert into pingmin(pingminner, pingmin, pid) values(?, ?, ?)");
            pstmt2.setString(1, FromUserName);
            pstmt2.setInt(2, i);
            pstmt2.setInt(3, id);
            Integer in = DatabaseUtil.executeUpdate(pstmt2);
            if (in != 0) {
                respContent = "房号："+rs.getInt("room_num")+"\n" +
                        "词语："+rs.getString("pingminci")+"\n" +
                        "你是："+i+"\n" +
                        "配置："+rs.getInt("wodi_num")+"个卧底，"+rs.getInt("pingmin_num")+"个平民\n" +
                        "输了要有惩罚哦，回复6查看大冒险惩罚！\n";
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void wodi(String FromUserName, Integer id, ResultSet rs, Integer i) {
        try {
            Connection con1 = DatabaseUtil.getConnection();
            PreparedStatement pstmt1 = DatabaseUtil.preparedStmt(con1, "insert into wodi(wodiner, wodi, pid) values(?, ?, ?)");
            pstmt1.setString(1, FromUserName);
            pstmt1.setInt(2, i);
            pstmt1.setInt(3, id);
            Integer in = DatabaseUtil.executeUpdate(pstmt1);
            if (i != 0) {
                respContent = "房号："+rs.getInt("room_num")+"\n" +
                        "词语："+rs.getString("wodici")+"\n" +
                        "你是："+i+"\n" +
                        "配置："+rs.getInt("wodi_num")+"个卧底，"+rs.getInt("pingmin_num")+"个平民\n" +
                        "输了要有惩罚哦，回复6查看大冒险惩罚！\n";
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer getNumber(Integer total_num) {
        for (Integer i=1; i<=total_num; i++) {
            return i;
        }
        return 0;
    }

    public static boolean isAllNum(String content){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(content);
        if(isNum.matches()){
            return true;
        }
        return false;
    }
}
