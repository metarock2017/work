package mession1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "PageServlet")
public class PageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        PageDo pageDo = new PageDo();
        PageInfo pageInfo = pageDo.PageInfo(request);
        pageDo.mohufind();
        ArrayList<Student> students = pageDo.getStudents();
        Integer min = pageInfo.getPer_page()*(pageInfo.getPage()-1);
        Integer max = pageInfo.getPer_page()*pageInfo.getPage();
        out.println("<tr><td>id</td><td>stuId</td><td>name</td><td>gender</td><td>grade</td><td>college</td><td>major</td><td>class</td></tr>");
        for (Integer i=min; i<max; i++) {
            Student student = students.get(i);
            out.println("<tr><td>"+student.getId()+"</td><td>"+student.getStuId()+"</td><td>"+student.getName()+"</td><td>"+student.getGender()+"</td><td>"+student.getGrade()+"</td><td>+student.getCllege()+</td><td>"+student.getMajor()+"</td><td>"+student.getIclass()+"</td></tr>");
        }
    }
}
