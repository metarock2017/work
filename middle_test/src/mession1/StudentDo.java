package mession1;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentDo {
    public Connect connect = new Connect();
    public Connection con = connect.getCon();
    public PreparedStatement psql;
    public String addmessage = null;

    public Student student = new Student();

    public String getAddmessage() {
        return addmessage;
    }
    public boolean addStu(HttpServletRequest request) {
        String stuId = request.getParameter("stuId");
        String name = request.getParameter("name");
        Integer gender = Integer.parseInt(request.getParameter("gender"));
        Integer grade = Integer.parseInt(request.getParameter("grade"));
        String college = request.getParameter("college");
        String major = request.getParameter("major");
        String iclass = request.getParameter("class");
        try {
            if (isEmpty(stuId) || isEmpty(name) || isEmpty(grade.toString()) || isEmpty(college) || isEmpty(major) || isEmpty(iclass)) {
                addmessage = "stuId, name, gender, grade, college, major, iclass中全都不能为空！！！";
                return false;
            }else if (!okstuId(stuId)) {
                addmessage = "stuId必须为10位纯数字";
                return false;
            }else if (!okclass(iclass)){
                addmessage = "class必须为7~8位纯数字";
                return false;
            }else if (!okgender(gender)) {
                addmessage = "gender必须为0或1位纯数字";
                return false;
            } else {
                try{
                    psql = con.prepareStatement("insert into student(stuId, name, gender, grade, college, major, class) values(?, ?, ?, ?, ?, ?, ?)");
                    psql.setString(1, stuId);
                    psql.setString(2, name);
                    psql.setInt(3, gender);
                    psql.setInt(4, grade);
                    psql.setString(5, college);
                    psql.setString(6, major);
                    psql.setString(7, iclass);
                    Integer re= psql.executeUpdate();
                    if (re !=0) {
                        addmessage = "添加学生信息成功！！！";
                        return true;
                    }
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            connect.closeDB();
        }

        return false;
    }

    public boolean okgender(Integer gender) {
        if (gender == 0 || gender == 1) {
            return true;
        }
        return false;
    }

    public boolean okstuId(String stuId) {
        Pattern pattern = Pattern.compile("^[0-9]{10}$");
        Matcher matcher = pattern.matcher(stuId);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public boolean okclass(String iclass) {
        Pattern pattern = Pattern.compile("^[0-9]{7,8}$");
        Matcher matcher = pattern.matcher(iclass);
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

    public void getStu(HttpServletRequest request) {
        String stuId = request.getParameter("stuId");
        String name = request.getParameter("name");

        try {

            if (isEmpty(stuId) && isEmpty(name)) {
                addmessage = "stuId 和name至少有一个不能为空";
            } else {
                if (!isEmpty(stuId) && isEmpty(name)) {
                    psql = con.prepareStatement("select * from student where stuId=? and name=?");
                    psql.setString(1, stuId);
                    psql.setString(2, name);
                } else if (!isEmpty(stuId)) {
                    psql = con.prepareStatement("select * from student where stuId=?");
                    psql.setString(1, stuId);
                } else if (!isEmpty(name)) {
                    psql = con.prepareStatement("select * from student where name=?");
                    psql.setString(1, name);
                }
                ResultSet rs = psql.executeQuery();
                if (rs.next()) {
                    student.setId(rs.getInt("id"));
                    student.setStuId(rs.getString("stuId"));
                    student.setName(rs.getString("name"));
                    student.setGender(rs.getInt("gender"));
                    student.setGrade(rs.getInt("grade"));
                    student.setCllege(rs.getString("college"));
                    student.setMajor(rs.getString("major"));
                    student.setIclass(rs.getString("class"));
                } else {
                    addmessage = "该学生不存在";

                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connect.closeDB();
        }
    }

    public Student getStudent() {
        return  student;
    }

    public  boolean delStudent(HttpServletRequest request) {
        String stuId = request.getParameter("stuId");
        String name = request.getParameter("name");

        try {

            if (isEmpty(stuId) && isEmpty(name)) {
                addmessage = "stuId 和name至少有一个不能为空";
            } else {
                if (!isEmpty(stuId) && isEmpty(name)) {
                    psql = con.prepareStatement("delete * from student where stuId=? and name=?");
                    psql.setString(1, stuId);
                    psql.setString(2, name);
                } else if (!isEmpty(stuId)) {
                    psql = con.prepareStatement("delete * from student where stuId=?");
                    psql.setString(1, stuId);
                } else if (!isEmpty(name)) {
                    psql = con.prepareStatement("delete * from student where name=?");
                    psql.setString(1, stuId);
                }
                psql.executeUpdate();

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connect.closeDB();
        }
        return false;
    }


}
