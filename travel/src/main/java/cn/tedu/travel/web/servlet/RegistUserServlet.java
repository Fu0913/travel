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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/registUserServlet")
public class RegistUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //验证码校验
        //获取用户输入的验证码
        String check = req.getParameter("check");
        //从session获取后端生成的验证码
        HttpSession session = req.getSession();
        String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER");
        //保证验证码只能用一次
        session.removeAttribute("CHECKCODE_SERVER");
        //比较
        if(checkcode_server==null||!checkcode_server.equalsIgnoreCase(check)){
            //验证码错误
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //将info对象序列化为Json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);

            //将json对象发送给浏览器端
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);
            //结束程序，不用向下执行了
            return;
        }


        //1.获取数据
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
        //3.调用servie完成注册
        UserService userService = new UserServiceImpl();
        boolean flag=userService.regist(user);
        //4.响应结果
        ResultInfo info = new ResultInfo();
        if(flag){
            //注册成功
            info.setFlag(true);
        }else {
            //注册失败，数据库已经存在重名数据
            info.setFlag(false);
            info.setErrorMsg("注册失败，数据库已经存在重名数据");
        }
        //将info对象序列化为Json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        //将json对象发送给浏览器端
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);



    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
