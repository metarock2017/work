package mession1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ManageServlet")
public class ManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        String message = null;
        StudentDo studentDo = new StudentDo();
        if ("add".equals(action)) {
            studentDo.addStu(request);
        } else if ("get".equals(action)) {
            studentDo.getStu(request);
            Student student = studentDo.getStudent();
            if (student != null) {
                out.println("<tr><td>"+student.getId()+"</td><td>"+student.getStuId()+"</td><td>"+student.getName()+"</td><td>"+student.getGender()+"</td><td>"+student.getGrade()+"</td><td>+student.getCllege()+</td><td>"+student.getMajor()+"</td><td>"+student.getIclass()+"</td></tr>");
            }
        } else if ("del".equals(action)) {
            studentDo.delStudent(request);
        }
        message =studentDo.getAddmessage();
        if (message != null) {
            out.println(message);
        }
    }
}
