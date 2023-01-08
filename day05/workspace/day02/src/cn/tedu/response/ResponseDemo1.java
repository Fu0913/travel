package cn.tedu.response;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 字节流响应消息，解决中文乱码问题
 */
@WebServlet("/respondemo1")
public class ResponseDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str="success";
        String str2="非常感谢";
        ServletOutputStream sos = resp.getOutputStream();
        //解决中文乱码问题
        resp.setContentType("text/html;charset=utf-8");
        sos.write(str.getBytes("utf-8"));
        sos.write(str2.getBytes("utf-8"));
        sos.close();


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
