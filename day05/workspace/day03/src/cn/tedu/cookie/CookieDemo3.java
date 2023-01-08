package cn.tedu.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/demo3")
public class CookieDemo3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.通过响应对象resp写出一个提示信息
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        pw.write("欢迎访问，您最后的访问时间为：");
        //2.创建Cookie对象，设置它的name和value
        Cookie cookie = new Cookie("time",System.currentTimeMillis()+"");
        //3.设置Cookie的存活时间
        cookie.setMaxAge(3600);
        //4.通过响应对象resp发送Cookie对象到浏览器端
        resp.addCookie(cookie);
        //5.通过请求对象获取Cookie对象
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie1 : cookies) {
            //6.从Cookie对象中拿到访问时间，并进行写出
            if("time".equals(cookie1.getName())){
                String value = cookie1.getValue();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String s = sdf.format(new Date(Long.parseLong(value)));
                pw.write(s);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
