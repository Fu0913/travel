package cn.tedu.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/studentServlet")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.从表单获取数据  key-value
        String username1 = req.getParameter("username");
        String age1 = req.getParameter("age");
        String score1 = req.getParameter("score");
        //2.将数据保存到stu.txt记事本里
        BufferedWriter bw = new BufferedWriter(new FileWriter("d:\\stu.txt",true));
        bw.write(username1+","+age1+","+score1);
        bw.close();
    //3.响应给浏览器
    PrintWriter pw = resp.getWriter();
        pw.println("save success");
        pw.close();
}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
