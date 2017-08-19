package mession1;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.security.PublicKey;
import java.sql.*;
import java.util.ArrayList;

public class PageDo {
    private Connect connect = new Connect();
    private Connection con = connect.getCon();
    PreparedStatement psql;

    private Integer totalPage;
    private Integer pageCount;
    ArrayList<Student> students = new ArrayList<>();

    public PageInfo pageInfo = new PageInfo();
    public PageInfo PageInfo(HttpServletRequest request) {
        Integer page = Integer.parseInt(request.getParameter("page"));
        Integer per_page = Integer.parseInt(request.getParameter("per_page"));
        String sortby = request.getParameter("stuId");
        String sort = request.getParameter("sort");
        String name = request.getParameter("name");
        String stuId = request.getParameter("stuId");

        pageInfo.setPage(page);
        pageInfo.setPer_page(per_page);
        pageInfo.setSortby(sortby);
        pageInfo.setSort(sort);
        pageInfo.setName(name);
        pageInfo.setStuId(stuId);
        return pageInfo;
    }

    public void mohufind() {
        try {//+pageInfo.getSortby()+"
            psql = con.prepareStatement("select * from student where name like \"%"+pageInfo.getName()+"%\" and stuId like \"%"+pageInfo.getStuId()+"%\" GROUP BY id "+pageInfo.getSort());
            ResultSet rs = psql.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setStuId(rs.getString("stuId"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getInt("gender"));
                student.setGrade(rs.getInt("grade"));
                student.setCllege(rs.getString("college"));
                student.setMajor(rs.getString("major"));
                student.setIclass(rs.getString("class"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}
