# day03

## 1.会话技术

​	1.会话：浏览器端和服务器端之间进行多次请求和响应。

​	2.为了实现一些功能，浏览器和服务器之间可能会产生多次的请求和响应，从浏览器开始，到访问服务器结束，这期间产生的多次请求和响应我们就称之为**一次会话**

​	3.会话过程中所产生的一些数据，可以通过会话技术（Cookie和Session）保存

### 1.2 客户端会话技术Cookie

#### 1.概念：

它是客户端浏览器的缓存文件，里面记录了客户端浏览器访问网站的一些内容，它也是Http协议请求和响应消息头的一部分。

#### 2.作用

它可以保存浏览器访问网站的相关内容，从而在每次访问同一个内容时，先从本地缓存获取，提高资源率用率，提高效率

#### 3.Cookie的属性

| 属性名称 | 属性作用               | 是否重要 |
| -------- | ---------------------- | -------- |
| name     | cookie的名称           | 必要属性 |
| value    | cookie的值(不能是中文) | 必要属性 |
| path     | cookie路径             | 重要     |
| maxAge   | cookie的生存时间       | 重要     |

4.Cookie方法

方法一：创建Cookie

| 方法名                           | 作用             |
| -------------------------------- | ---------------- |
| Cookie(String name,String value) | 构造方法创建对象 |
| 属性对应的set和get方法           | 赋值和获取值     |

方法二：

| 返回值   | 方法名                     | 说明               |
| -------- | -------------------------- | ------------------ |
| void     | addCookie（Cookie cookie） | 向客户端添加Cookie |
| Cookie[] | getCookies()               | 获取所有的Cookie   |

代码

第一个servlet

```java
@WebServlet("/demo1")
public class CookieDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //自定义一个cookie
        Cookie cookie = new Cookie("username", "zhangsan");
        //发送到浏览器端
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
```

第二个servlet

```java
@WebServlet("/demo2")
public class CookieDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取所有的Cookie
        Cookie[] cookies = req.getCookies();
        //遍历cookies数组
        for (Cookie cookie : cookies) {
            //获取自己发送到浏览器端的cookie
            if("username".equals(cookie.getName())){
                System.out.println(cookie.getValue());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
```



