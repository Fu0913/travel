package cn.tedu.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/demo2")
public class RequestDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.根据请求头获取值
        String connection = req.getHeader("Connection");
        System.out.println(connection);
        //2.根据请求头名称获取多个值
        Enumeration<String> values = req.getHeaders("Accept");
        while (values.hasMoreElements()){
            String value = values.nextElement();
            System.out.println(value);
        }
        //3.获取所有请求头名称
        Enumeration<String> names = req.getHeaderNames();
        while (names.hasMoreElements()){
            String name = names.nextElement();
            String value = req.getHeader(name);
            System.out.println(name+": "+value);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
