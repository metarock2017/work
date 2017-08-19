package mession1;


import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserDo {
    public Connect connect = new Connect();
    Connection  con= connect.getCon();
    PreparedStatement psql;

    public boolean okPassword(String password) {
        Pattern pattern = Pattern.compile("^[0-9]{6}$");
        Matcher matcher= pattern.matcher(password);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public boolean okUsername(String username) {
        Pattern pattern = Pattern.compile("^[a-zA-Z]{1,10}$");
        Matcher matcher = pattern.matcher(username);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public boolean isEmpty(String s) {
        if (s == null || s.length() <= 0) {
            return true;
        }
        return false;
    }

    public boolean login(User user) {
        try {
            String username = user.getUsername();
            String password = Base64.getBase64(user.getPassword());
            psql = con.prepareStatement("select username from users where username=\""+username+"\" and password=\""+password+"\"");
            ResultSet rs = psql.executeQuery();
            String username1;
            if (rs.next()) {
                username1 = rs.getString("username");
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            connect.closeDB();
        }
        return false;
    }

    public boolean regsiter(User user) {
        String username = user.getUsername();
        if(!serchUser(username)) {
            try {
                psql = con.prepareStatement("insert into users(username, password, question, answer) values(?, ?, ?, ?)");
                psql.setString(1, user.getUsername());
                psql.setString(2, user.getPassword());
                psql.setString(3, user.getQuestion());
                psql.setString(4, user.getAnswer());
                psql.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                connect.closeDB();
            }
            return true;
        }
        return false;

    }
    public boolean serchUser(String username){
        try {
            psql = con.prepareStatement("select * from users where username=\""+username+"\"");
            ResultSet rs = psql.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connect.closeDB();
        }
       return false;
    }

}
