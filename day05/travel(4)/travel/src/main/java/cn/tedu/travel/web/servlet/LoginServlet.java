package cn.tedu.travel.web.servlet;

import cn.tedu.travel.domain.ResultInfo;
import cn.tedu.travel.domain.User;
import cn.tedu.travel.service.UserService;
import cn.tedu.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取用户名和密码
        Map<String, String[]> map = req.getParameterMap();
        //2.封装数据到user对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.调用service方法
        UserService service = new UserServiceImpl();
        User u=service.login(user);
        //4.判断返回数据u是否为null
        ResultInfo info = new ResultInfo();
        if(u==null){
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }else{
            info.setFlag(true);
        }
        //5.响应数据
        //将info对象序列化为Json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        //将json对象发送给浏览器端
        resp.setContentType("application/json;charset=utf-8");
        mapper.writeValue(resp.getOutputStream(),info);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
