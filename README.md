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