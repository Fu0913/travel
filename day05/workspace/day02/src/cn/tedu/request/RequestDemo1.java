package cn.tedu.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取路径相关的方法
 */
@WebServlet("/demo1")
public class RequestDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取虚拟目录名称
        String contextPath = req.getContextPath();
        System.out.println(contextPath);
        //2.获取servlet映射路径
        String servletPath = req.getServletPath();
        System.out.println(servletPath);
        //3.获取访问者ip
        String remoteAddr = req.getRemoteAddr();
        System.out.println(remoteAddr);
        //4.获取请求消息数据
        String queryString = req.getQueryString();
        System.out.println(queryString);
        //5.获取统一资源标识符
        String requestURI = req.getRequestURI();
        System.out.println(requestURI);
        //6.获取统一资源定位符
        StringBuffer requestURL = req.getRequestURL();
        System.out.println(requestURL);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
