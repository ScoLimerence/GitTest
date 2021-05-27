# GITIDEATest

在IDEA上测试Git的使用

# 在IDEA中配置Tomcat

必须要配置默认的artifacts，tomcat被idea接管后不会去本地的webapps 文件夹下找应用，而是直接在项目的out目录下启动应用。这就是为什么没有tomcat默认 欢迎页面的原因。

> target和out是Web项目的发布目录，target是单个模块，out是整个项目，设置了target就不会产生out

## ServletContext

由Web容器创建. web应用启动的时候，会为每个web程序创建一个对应的ServletContext，代表当前web应用</br>
相当于一个公共存放数据的容器，可以在不同的servlet中取到

1. 使用ouputStream输出示例：程序给客户机打出的是什么数据，则程序一定要记得通知浏览器以什么码表打开（ 字节流）
   ```java
   response.setHeader("content-type", "text/html;charset=UTF-8");
   String data = "中国";
   OutputStream out = response.getOutputStream();
   out.write(data.getBytes("UTF-8"));
    
2. 在使用 字符流 输出数据时，一定要设置response的码表（如果设置的码表，response在输出数据时，会以指定的码表转换数据输出），并且还要通知浏览器以相应码表打开数据
      ```java
   String data = "中国";
   response.setCharacterEncoding("UTF-8");
   response.setHeader("content-type", "text/html;charset=UTF-8");
   PrintWriter out = response.getWriter();
   out.write("中国");
   
## 读取资源文件
`Properties` 类
1. 创建对象
2. 加载文件load
3. 获得参数getProperty

##classpath
java类路径，项目发布后的classes文件夹路径

## 项目发布省去项目名称就会替换tomcat中的root目录内容

## tomcat中work文件夹的作用
work目录只是tomcat的工作目录，也就是tomcat把jsp转换为class文件的工作目录。

jsp，tomcat的工作原理：
当浏览器访问某个jsp页面时，tomcat会在work目录里把这个jsp页面转成.java文件,比如将index.jsp转换成index_jsp.java文件，而后编译为index_jsp.class文件，最后tomcat容器通过ClassLoader类把这个index_jsp.class类装载入内存,进行响应客户端的工作。
tomcat会定时扫描容器内的jsp文件,读取每个文件的属性，当发现某个jsp文件发生改变时（文件的最后修改时间与上次扫描时不相同时），tomcat会重新转换、编译这个jsp文件。但是tomcat的扫描是定时的不是实时的，这也正是为什么jsp文件修改后需要几分钟的时间来等修改过的jsp生效。

## response常见应用
1. 向浏览器输出消息，通过字节和字符输出流
2. 下载文件

> IO操作：通俗地讲就是： 
> FileInputStream在读取文件的时候，一滴一滴地把水从一个缸复制到另外一个缸 
> BufferInputStream则是一桶一桶地把水从一个缸复制到另外一个缸。

## 浏览器端默认缓存网页
为了提高网页的访问效率，浏览器可以在本地缓存以前访问过的页面，当浏览器再次访问那些已经缓存了的页面时，
浏览器就不用再去访问远程的Web服务器了，而是直接从本地取出缓存的内容。
缓存提高了浏览器的访问效率，但有时候也会带来负面效果，那就是服务端的内容变化不能实时地反应到客户端
，例如：`动态产生的图片文件(如验证码)和js脚本文件不能及时更新的问题`。有三个HTTP响应头字段可以禁止浏览器缓存当前页面，在Servlet中的示例代码如下：</br>
response.setHeader("Expires",0);</br>
response.setHeader("Cache-Control","no-cache");</br>
response.setHeader("Pragma","no-cache");</br>
并不是所有的浏览器都能完全支持这三个响应头，因此最好同时使用以上三个响应头，只要浏览器能支持其中任何一种形式，就能禁止浏览器缓存当前页面。

## 请求转发与重定向
面试题：resp.sendRedirect();
相同点：页面都会跳转   不同点：转发地址栏不会变化、重定向url会发生变化。转发可以携带请求参数到另一个页面，
重定向不刻意。
转发可以使用两种方式
req.getRequestDispatcher("/index.jsp").forward(req,resp);
this.getServletContext().getRequestDispatcher().forward(req,resp);

## request中parameter与attribute的区别
区别：
来源不同：   
参数（parameter）是从客户端（浏览器）中由用户提供的，若是GET方法是从URL中 提供的，
若是POST方法是从请求体（request body）中提供的；
属性（attribute）是服务器端的组件（JSP或者Servlet）利用requst.setAttribute（）设置的

操作不同：   参数（parameter）的值只能读取不能修改，读取可以使用request.getParameter()读取；
属性（attribute）的值既可以读取亦可以修改，读取可以使用request.setAttribute(), 设置可使用request.getAttribute()

数据类型不同：   参数（parameter）不管前台传来的值语义是什么，`在服务器获取时都以String类型看待`，并且客户端的参数值只能是简单类型的值，不能是复杂类型，比如一个对象。
属性（attribute）的值可以是任意一个Object类型。

共同点
二者的值都被封装在request对象中。

## request的使用获取前端参数和请求的转发
其取值都是依赖于前端标签的name属性，取到对应的value值，不是id。
getParameter()  获取单个前端参数值
getParameterValues()  获得复选框多个值返回数组

## Session和ServletContext的区别
Session        它是一个会话范围，相当于一个局部变量，从Session第一次创建知道关闭，
数据都一直 保存，每一个客户都有一个Session，所以它可以被客户一直访问，只要Session没有关闭和超时即浏览器关闭。<br>
servletContext    它代表了servlet环境的上下文，相当于一个全局变量，即只要某个web应用在启动中，这个对象就一直都有效的存在，
所以它的范围是最大的，存储的数据可以被所有用户使用，只要服务器不关闭，数据就会一直都存在。


##JVM双亲委派机制
java找类会优先找父级目录，层层递进直到找到为止

response.getWriter()返回的是PrintWriter，这是一个打印输出流

response.getWriter().write(）和 response.getWriter().print(）是响应给客户端的东西，如果不用ajax接收将数据放在合适的位置，就会在浏览器上生成一个新的页面来显示内容。

print
response.getWriter().print(),不仅可以打印输出文本格式的（包括html标签），还可以将一个对象以默认的编码方式转换为二进制字节输出

writer
response.getWriter().writer(),只能打印输出文本格式的（包括html标签），不可以打印对象
