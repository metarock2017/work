package mission3;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDo {
    public Connect connect = new Connect();
    public Connection con = connect.getCon();
    public PreparedStatement psql;

    public boolean MainMessage(HttpServletRequest request) {
        try{
            String messageid =request.getParameter("messageid");
            String  userid = request.getParameter("userid");
            String message = request.getParameter("message");
            psql = con.prepareStatement("insert into message(userid, messageid, message) values(?, ?, ?)");
            psql.setString(1, userid);
            psql.setString(2, messageid);
            psql.setString(3, message);
            Integer i = psql.executeUpdate();
            if (i !=0) {
                return true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connect.closeDB();
        }
        return false;
    }

    public List<Message> getMessage(String messageid) {
        List<Message> messages = new ArrayList<>();
        try{
            psql = con.prepareStatement("select * from message where messageid=?");
            psql.setString(1, messageid);
            ResultSet rs = psql.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                message.setMessageid(rs.getString("messageid"));
                message.setUserid(rs.getString("userid"));
                messages.add(message);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connect.closeDB();
        }
        return messages;
    }
}
