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
        //将cookie对象发送到浏览器端
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

#### 4.Cookie的练习

>
>
>需求说明：通过Cookie记录浏览器最后访问时间，并且在浏览器上显示出来
>
>实现步骤：
>
>​	1.通过响应对象resp写出一个提示信息
>
>​	2.创建Cookie对象，设置它的name和value
>
>​	3.设置Cookie的存活时间 
>
>​	4.通过响应对象resp发送Cookie对象到浏览器端
>
>​	5.通过请求对象req获取Cookie对象
>
>​	6.从Cookie对象中拿到访问时间，并进行写出

代码

```java
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
```

#### 5.cookie访问路径

```
* Cookie路径限制
* 取自第一次访问资源路径的前缀"/servlet"
* 只要以这个前缀为开头(包裹后边有子级目录)，都可以获取到
* 反之获取不到
```

代码

第一个servlet:发送cookie到客户端

```java
@WebServlet("/servlet/demo4")
public class CookieDemo4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("name","zhangsan");
        cookie.setMaxAge(3600);
        resp.addCookie(cookie);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
```

第二个cookie：测试获取cookie数据

```java
@WebServlet("/servlet/demo5")
public class CookieDemo5 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if("name".equals(cookie.getName())){
                String value = cookie.getValue();
                resp.getWriter().write(value);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
```

第三个cookie：测试获取cookie数据

```java
@WebServlet("/servlet/aaa/demo6")
public class CookieDemo6 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if("name".equals(cookie.getName())){
                String value = cookie.getValue();
                resp.getWriter().write(value);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
```

第四个cookie：测试获取cookie数据

```java
@WebServlet("/aaa/servlet/demo7")
public class CookieDemo7 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if("name".equals(cookie.getName())){
                String value = cookie.getValue();
                resp.getWriter().write(value);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
```

### 1.3服务器端会话技术Session

#### 1.HttpSession

##### 	1.简介：服务器端会话技术，可以实现服务器端数据共享

##### 	2.常用方法

###### 		1.HttpSession常用方法

| 返回值 | 方法名            | 说明             |
| ------ | ----------------- | ---------------- |
| void   | setAttribute()    | 设置共享数据     |
| Object | getAttribute()    | 获取共享数据     |
| void   | removeAttribute() | 移除共享数据     |
| String | getId()           | 获取唯一标识名称 |

###### 		2.获取HttpSession对象方法

| 返回值      | 方法名     | 说明                |
| ----------- | ---------- | ------------------- |
| HttpSession | getSession | 获取HttpSeesion对象 |

##### 3.练习

>需求说明：通过第一个servlet设置共享数据，在第二个servlet获取
>
>实现步骤：
>
>​	1.在第一个servlet中获取请求的用户名
>
>​	2.获取HttpSession对象
>
>​	3.通过HttpSession对象设置共享数据
>
>​    4.在第二个servlet中获取HttpSession对象
>
>​    5.通过HttpSession对象获取共享数据
>
>​	6.将数据响应给浏览器

第一个servlet

```java
@WebServlet("/sessionDemo1")
public class SessionDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的用户名
        String username = req.getParameter("username");
        //2.获取HttpSession对象
        HttpSession session = req.getSession();
        //3.通过HttpSession对象设置共享数据
        session.setAttribute("username",username);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
```

第二个servlet

```java
@WebServlet("/sessionDemo2")
public class SessionDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //4.获取HttpSession对象
        HttpSession session = req.getSession();
        //5.通过HttpSession对象获取共享数据
        Object username = session.getAttribute("username");
        //6.将数据响应给浏览器
        resp.getWriter().write(username+"");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
```

## 2.maven

### 1.概念：本质上是一个项目管理工具，将项目开发和管理过程抽象成一个项目对象模型(POM)

POM:项目对象模型

### 2.maven作用：

​	1.项目构建，提供标准的、跨平台的自动化构建项目的方式

​	2.依赖管理：方便快捷的管理项目所依赖的资源(jar包)，避免资源间的版本冲突问题

​	3.统一开发结构：提供标准的、统一的项目开发结构，如下图所示：

![image-20230102170124962](D:\nongdashixun\note\img\image-20230102170124962.png)

> 目录说明如下：
>
> 1.src/main/java:项目的java源码
>
> 2.src/main/resources:项目的相关配置文件(.xml配置文件，.properties配置文件)
>
> 3.src/test/java:测试代码
>
> 4.src/pom.xml:项目pom文件

### 3.maven环境搭建

#### 	1.下载：[Maven – Welcome to Apache Maven](https://maven.apache.org/)

####  	2.安装：解压缩即可

#### 	3.环境变量配置：参考JAVA_HOME，

### 4.Maven仓库

#### 		1.中央仓库：

​			maven官方提供的开源的仓库

####     	2.私服：

​					各公司自己建立的资源仓库，用于提供公司内部项目所需要资源，私服也可以从中央仓库中获取资源

​      				私服作用：

​								1.保存具有版权的资源

​								2.一定范围内共享资源，可以做到对内开放，不对外开放。

#### 		3.本地仓库：

开发者自己电脑上存储的仓库，也可以从中央仓库获取资源

###    5.坐标

#### 		1.坐标：maven中的坐标指向仓库中的资源位置

####         2.访问路径：http://mvnrepository.com/ 

####         3.坐标含义

​			

>​			1.groupId:定义当前资源隶属组织名称（一般是域名反写）
>
>​             2.artifactId:定义当前资源的名称
>
>​             3.version:定义资源的版本号
>
>​			  4.scope：确定当前资源生效的位置
>
><dependency>
>
>​    <groupId>junit</groupId>
>​    <artifactId>junit</artifactId>
>​    <version>4.13.2</version>
>​    <scope>test</scope>
></dependency>

### 6.作用：

唯一标识符，唯一性定义资源的位置，通过该标识可以将资源进行下载

### 7.使用IDEA工具搭建maven项目

#### 1.不使用原型创建项目

​	1.步骤

​		1.创建空的项目

​		第一步：

![](D:\nongdashixun\note\img\image-20230102163851446.png)

第二步：

![image-20230102163917299](D:\nongdashixun\note\img\image-20230102163917299.png)

2.配置Maven

  第一步：

![image-20230102164016561](D:\nongdashixun\note\img\image-20230102164016561.png)

第二步：创建maven本地仓库

![image-20230102164928974](D:\nongdashixun\note\img\image-20230102164928974.png)

第三步：配置maven属性

![image-20230102164801360](D:\nongdashixun\note\img\image-20230102164801360.png)

第五步：创建maven项目

![image-20230102165221886](D:\nongdashixun\note\img\image-20230102165221886.png)

第六步：

![image-20230102165358697](D:\nongdashixun\note\img\image-20230102165358697.png)

第七步：

![image-20230102165439049](D:\nongdashixun\note\img\image-20230102165439049.png)

#### 2.使用原型创建maven项目

步骤：

第一步：

![image-20230102171042124](D:\nongdashixun\note\img\image-20230102171042124.png)

第二步：

![image-20230102171210308](D:\nongdashixun\note\img\image-20230102171210308.png)

第三步：

![image-20230102171230185](D:\nongdashixun\note\img\image-20230102171230185.png)

第四步：

![image-20230102171605009](D:\nongdashixun\note\img\image-20230102171605009.png)