package org.redrock.ljj;

import org.redrock.play.Room;
import org.redrock.util.DatabaseUtil;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "WordServlet")
public class WordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            request.setCharacterEncoding("utf-8");
            Integer total_num = Integer.parseInt(request.getParameter("total_num"));
            String wodici = request.getParameter("wodici");
            String pingminci = request.getParameter("pingminci");
            Integer wodi_num = total_num/3;
            Random random = new Random();
            Integer min = 1;
            Set<Integer> set = new HashSet<>();
            for (int j=0; j<wodi_num; j++) {
                int num = random.nextInt(total_num)%(total_num - min+1) + min;
                set.add(num);
            }
            Integer pingmin_num = total_num - wodi_num;
            Long expiretime = System.currentTimeMillis()+30*60*60*1000;
            Integer room_num = (int)(Math.random()*8999)+1000;
            String openner = request.getParameter("openner");
            Connection con = DatabaseUtil.getConnection();
            Integer in = delroom(openner);
            PreparedStatement pstmt = con.prepareStatement("insert into room(openner, room_num, total_num, wodi_num, pingmin_num, wodici, pingminci, expiretime) values(?, ?, ?, ?, ?, ?, ?, ?)");
            //            PreparedStatement pstmt = DatabaseUtil.preparedStmt(con, "insert into room(openner, room_num, total_num, wodi_num, pingmin_num, wodici, pingminci, expiretime) values(?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, openner);
            pstmt.setInt(2, room_num);
            pstmt.setInt(3, total_num);
            pstmt.setInt(4, wodi_num);
            pstmt.setInt(5, pingmin_num);
            pstmt.setString(6, wodici);
            pstmt.setString(7, pingminci);
            pstmt.setLong(8, (expiretime));
            Integer i = DatabaseUtil.executeUpdate(pstmt);

            if (i != 0 && in != 0) {
                Integer id = getRoomId(openner);
                Jedis jedis = new Jedis("127.0.0.1", 6379);
                Iterator<Integer> it = set.iterator();
                while (it.hasNext()) {
                    jedis.lpush("wodi:"+id, it.next().toString());
                }
                Room room = new Room();
                room.setOpenner(openner);
                room.setRoom_num(room_num);
                room.setTotal_num(total_num);
                room.setWodi_num(wodi_num);
                room.setPingmin_num(pingmin_num);
                room.setWodici(wodici);
                room.setPingminci(pingminci);
                room.setExpiretime(expiretime);

                request.setAttribute("room", room);
                request.setAttribute("set", set);
                request.getRequestDispatcher("/myjsp/result.jsp").forward(request, response);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Integer getRoomId(String openner) {
        Integer id =0;
        try{
            Connection con = DatabaseUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement("select id from room where openner=?");
            pstmt.setString(1, openner);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public Integer delroom(String openner) {
        try{
            Connection con = DatabaseUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement("delete from room where openner=?");
            pstmt.setString(1, openner);
            Integer in = DatabaseUtil.executeUpdate(pstmt);
            return 1;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
