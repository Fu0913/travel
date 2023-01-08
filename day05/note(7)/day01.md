# day01

## 1.web相关知识

### 1.软件架构

​	1.B/S架构：浏览器端/服务器端

​	2.C/S架构：客户端/服务器端

### 2.资源分类

​	1.静态资源：所有用户访问以后，得到的都是一样的内容，称为静态资源。比如：html页面，css样式，JavaScript

​	2.动态资源：所有用户访问以后，得到的可能不一样，称为动态资源。比如：servlet,jsp,php....

## 3.web服务器软件

1. 服务器：安装了服务器软件的计算机

2. 服务器软件：接受用户请求，处理请求，做出响应。

3. tomcat服务器：web服务器软件

   >
   >
   >1.下载：http：//tomcat.apache.org/
   >
   >2.安装:解压压缩包即可
   >
   >​		注意事项：安装目录建议不要有中文和空格
   >
   >3.启动:bin目录下运行startup.bat程序，进行启动。看到Server startup in 810 ms：启动成功
   >
   >4.常见错误
   >
   >​	1.黑窗口一闪而过
   >
   >​			原因：jdk环境变量配置java_home有问题
   >
   >​			解决方案：检查jdk环境变量配置java_hom配置
   >
   >​	2.端口号被占用
   >
   >![](D:\nongdashixun\note\img\tomcat端口被占用.png)
   >
   >​	解决方案：
   >
   >​		1.win+r：打开黑窗口，运行netstat -ano 命令
   >
   >​		2.找到127.0.0.1对应的Pid
   >
   >![](D:\nongdashixun\note\img\查询被占用的端口号Pid.png)
   >
   >​	
   >
   >​		3.打开任务管理器，找到对应的Pid，结束程序即可。
   >
   >​		![image-20221231103330165](D:\nongdashixun\note\img\image-20221231103330165.png)
   >
   >5	关闭：bin目录下运行shutdown.bat程序,进行关闭

### 3.1idea集成tomcat服务器

第一步.创建JAVA_WEB项目

![image-20221231100605455](D:\nongdashixun\note\img\image-20221231100605455.png)

第二部：

![image-20221231100839658](D:\nongdashixun\note\img\image-20221231100839658.png)

第三部：点击右上角的ADD Configuration

![image-20221231100928225](D:\nongdashixun\note\img\image-20221231100928225.png)

第四步：

![image-20221231101124362](D:\nongdashixun\note\img\image-20221231101124362.png)

第五步：

![image-20221231101219015](D:\nongdashixun\note\img\image-20221231101219015.png)

### 3.2 tomcat服务器部署项目

第一步：点击右边+,选择Artifact...，把项目达成war包。

![image-20221231103823921](D:\nongdashixun\note\img\image-20221231103823921.png)

第二步：点击OK即可

![image-20221231103850125](D:\nongdashixun\note\img\image-20221231103850125.png)

## 2.Servlet

#### 2.1概念

​	1.Servlet是一个运行在web服务器端的java小程序

​	2.它可以用于接收和响应客户端请求

#### 2.2创建方式

##### 	2.2.1方式一：实现servlet接口

​	1.实现servlet接口，重写抽象方法，编写service（）方法

```java
package cn.tedu.servlet;

import javax.servlet.*;
import java.io.IOException;

public class ServletDemo1 implements Servlet{
    /**
     * 初始化
     * @param servletConfig
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    /**
     *
     * @return
     */
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * 服务的方法
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service is running...");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
    }
}

```

​	2.在web.xml配置文件中进行servlet配置

```xml
	<servlet>
            <servlet-name>demo1</servlet-name>
            <servlet-class>cn.tedu.servlet.ServletDemo1</servlet-			  class>
   </servlet>
   <servlet-mapping>
            <servlet-name>demo1</servlet-name>
            <url-pattern>/servlet1</url-pattern>
   </servlet-mapping>
```

##### 2.2.2方式二：继承继承GenericServlet抽象类

1.继承GenericServlet抽象类，重写Service（）方法

```java
public class ServletDemo2 extends GenericServlet {

    @Override
    public void service(ServletRequest servletRequest, 		ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("ServletDemo2 is running...");
    }
}
```

2.在web.xml配置文件中进行servlet配置

```xml
		<servlet>
            <servlet-name>demo2</servlet-name>
            <servlet-class>cn.tedu.servlet.ServletDemo2</servlet-			 class>
        </servlet>
        <servlet-mapping>
            <servlet-name>demo2</servlet-name>
            <url-pattern>/servlet2</url-pattern>
        </servlet-mapping>
```

##### 2.2.3方式三：继承继承HttpServlet抽象类

1.继承HttpServlet抽象类，重写doGet()方法和doPost()方法

```java
public class ServletDemo3 extends HttpServlet {
    /**
     * 页面get方法提交后，会调用该方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletDemo3 is runing...");
    }

    /**
     * 页面post方法提交后，会调用该方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
```

2.在web.xml配置文件中进行servlet配置

```xml
		<!--        servlet创建方式三：继承HttpServlet抽象类-->
        <servlet>
            <servlet-name>demo3</servlet-name>
            <servlet-class>cn.tedu.servlet.ServletDemo3</servlet-			class>
        </servlet>
        <servlet-mapping>
            <servlet-name>demo3</servlet-name>
            <url-pattern>/servlet3</url-pattern>
        </servlet-mapping>
```

#### 2.3 Servlet执行过程

1.我们通过浏览器发送请求，请求先到达tomcat服务器

2.tomcat服务器解析请求url，去找到部署项目里对应的day01项目

3.运行day01项目里的web.xml配置文件，根据请求路径找到ServletDemo3.class文件

4.运行ServletDemo3.class文件

5.响应给浏览器

简述：

浏览器--->Tomcat服务器--->day01项目-->web.xml--->ServletDemo3--->响应浏览器

#### 2.4 Servlet生命周期

1.出生：第一次请求到达Servlet时，对象创建出来，并且初始化成功，只会生成一次，放到内存里

2.活着：服务器提供整个服务的过程的时候，该对象一直存在，每次请求只执行service方法

3.消亡：当服务器停止时，或者服务器宕机时，对象消亡。

servlet对象只有一个实例，即：单例的，单例模式。

验证步骤：

1.创建ServletDemo4类，继承HTTPServlet，重写init(),doGet(),doPost(),destory()方法

```java
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
```

2.在web.xml配置文件中进行servlet配置

```xml
		<!--        servlet的生命周期-->
        <servlet>
            <servlet-name>demo4</servlet-name>
            <servlet-class>cn.tedu.servlet.ServletDemo4</servlet-			 class>
        </servlet>
        <servlet-mapping>
            <servlet-name>demo4</servlet-name>
            <url-pattern>/servlet4</url-pattern>
        </servlet-mapping>
```

#### 2.5注解开发Servlet

1.编写ServletDemo5,继承HttpServlet抽象类，重写doGet(),doPost()方法

2.在类ServletDemo5上方添加@WebServlet("/servlet5")注解

3.启动服务器进行访问[localhost:8080/day01_war_exploded/servlet5](http://localhost:8080/day01_war_exploded/servlet5)

```java
/**
 * 自动注解配置servlet
 */
@WebServlet("/servlet5")
public class ServletDemo5 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletDemo5 is running ....");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
```



@WebServlet注解详情

```java
public @interface WebServlet {
    /**
   	制定servlet的名称，相当于web.xml配置文件中的
   	<servlet>
   		<servlet-name></servlet-name>
   	</servlet>
    */
    String name() default "";
    /**
    相当于映射servlet访问的url,相当于web.xml配置文件中的
    <url-pattern>

    */
    String[] value() default {};
    /**
    相当于web.xml配置文件中的<url-pattern>
    */
    String[] urlPatterns() default {};
}
```

#### 2.6servlet练习：学生管理系统添加

1.分析：

![](D:\nongdashixun\note\img\servlet-学生管理系统.png)

2.步骤(代码实现)：

 1. 创建web项目

 2. 在web文件夹下创建regist.html页面，进行编写

    ```html
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>添加学生信息</title>
    </head>
    <body>
        <form action="/day01_war_exploded/studentServlet" method="get">
            姓名：<input type="text" name="username"><br/>
            年龄：<input type="text" name="age"><br/>
            成绩：<input type="text" name="score"><br/>
            <button type="submit">保存</button>
        </form>
    </body>
    </html>
    ```

    

 3. 在cn.tedu.servlet下创建StudentServlet类，编写逻辑

    ```java
    @WebServlet("/studentServlet")
    public class StudentServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //1.从表单获取数据  key-value
            String username1 = req.getParameter("username");
            String age1 = req.getParameter("age");
            String score1 = req.getParameter("score");
            //2.将数据保存到stu.txt记事本里
            BufferedWriter bw = new BufferedWriter(new FileWriter("d:\\stu.txt"));
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
    ```