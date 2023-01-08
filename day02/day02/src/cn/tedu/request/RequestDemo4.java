package cn.tedu.request;

import cn.tedu.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 封装对象---手动封装
 */
@WebServlet("/demo4")
public class RequestDemo4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //2.在servlet类中doGet（）方法中先获取请求数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String[] hobbies = req.getParameterValues("hobby");
        //3.创建Stu对象，将数据封装到Stu对象里
        Student stu = new Student(username,password,hobbies);
//        stu.setUsername(username);
//        stu.setPassword(password);
//        stu.setHobby(hobbies);
        System.out.println(stu);
        System.out.println("sdfsdfsdf");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
