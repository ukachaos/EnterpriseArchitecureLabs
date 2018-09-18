package cs544.sph2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StudentsCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Autowired
    StudentService studentService;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        String studentIdStr = request.getParameter("studentid");

        long studentid = -1;
        Student student = null;

        if (studentIdStr != null && studentIdStr.matches("\\d+")) {
            studentid = Long.parseLong(studentIdStr);

            ServletContext context = getServletContext();
            WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);

//            StudentService studentService = applicationContext.getBean("studentService", StudentService.class);

            studentService = applicationContext.getBean("studentService", StudentService.class);

            student = studentService.getStudent(studentid);
        }

        request.setAttribute("student", student);
        request.getRequestDispatcher("student.jsp").forward(request, response);

    }
}