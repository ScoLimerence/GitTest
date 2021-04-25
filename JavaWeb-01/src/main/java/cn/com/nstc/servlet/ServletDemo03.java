package cn.com.nstc.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author huanghudong
 * @ClassName ServletDemo03.java
 * @Description TODO
 * @createTime 2021年04月25日 20:57:00
 */
public class ServletDemo03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取下载的文件路径
        String realPath = this.getServletContext().getRealPath("/hhd.jpeg");
        System.out.println(realPath);
        //2.获取下载文件名
        String fileName = realPath.substring(realPath.lastIndexOf("\\"));
        //3.设置响应头让浏览器下载
        resp.setHeader("content-disposition", "attachment;fileName=" + fileName);
        //4.获取下载文件的输入流
        FileInputStream is = new FileInputStream(realPath);
        //5.创建缓冲区
        byte[] buffer = new byte[1024];
        int len=0;
        //6.获取输出流
        ServletOutputStream out = resp.getOutputStream();
        //7.写入缓冲区
        while ((len=is.read(buffer))>0){
            //8.使用outputStream输出到客户端
            out.write(buffer,0,len);
        }
        is.close();
        out.close();
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
