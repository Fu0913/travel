package cn.tedu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet生命周期
 */
public class ServletDemo4 extends HttpServlet {
    /**
     * 初始化方法
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        System.out.println("init is running...");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("接受到了客户端请求");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /**
     * 销毁的方法
     */
    @Override
    public void destroy() {
        System.out.println("destroy is running ....");
    }
}
