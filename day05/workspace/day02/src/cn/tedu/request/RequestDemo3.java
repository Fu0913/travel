package cn.tedu.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

@WebServlet("/demo3")
public class RequestDemo3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.根据名称获取数据
        String username = req.getParameter("username");
        System.out.println(username);
        String password = req.getParameter("password");
        System.out.println(password);
        //2.根据名称获取所有数据 getParameterValues()
        String[] hobbies = req.getParameterValues("hobby");
        for (String hobby : hobbies) {
            System.out.println(hobby);
        }
        System.out.println("---------------------------------------");
        //3.获取所有的名称
        Enumeration<String> names = req.getParameterNames();
        while (names.hasMoreElements()){
            String name = names.nextElement();
            System.out.println(name);
        }
        //4.获取所有参数的键值对  username=zhangsan;password=123;hobby=study,hobby=run
        Map<String, String[]> map = req.getParameterMap();
        for (String key:map.keySet()) {
            String[] values = map.get(key);
            System.out.print(key+":");
            for (String value : values) {
                System.out.print(value+"  ");
            }
            System.out.println();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
