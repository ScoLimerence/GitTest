package com.huang.servlet; /**
 * @ClassName ${NAME}.java
 * @author huanghudong
 * @Description TODO
 * @createTime 2021年05月24日 23:21:00
 */

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import sun.applet.resources.MsgAppletViewer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "fileUploadServlet", value = "/upload.do")
public class FileUploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //必须是post方法，并且判断提交的表单是否包含文件
        if (!ServletFileUpload.isMultipartContent(request)) {
            return;//普通表单，中止运行，直接返回
        }
        //创建文件上传路径，在WEB-INF下，安全，用户无法直接访问
        String uploadPath = this.getServletContext().getRealPath("/WEB-INF/upload");
        File uploadFile = new File(uploadPath);
        if (!uploadFile.exists()) {
            uploadFile.mkdir();//文件不存在则创建，第一次上传时触发
        }

        //当文件超过规定大小，缓存到临时文件目录，定期删除
        String tmpFiel = this.getServletContext().getRealPath("/WEB-INF/tmp");
        File tempFile = new File(tmpFiel);
        if (!tempFile.exists()) {
            tempFile.mkdir();//创建临时目录
        }

/*            //处理上传的文件，一般都需要通过流来获取，我们可以使用request.getInputStream(),原生态的文件上传流获取，需要熟知网络协议，十分麻烦
            //但是我们都建议使用 Apache的文件上传组件来实现，common-fileupload，它需要依赖于 commons-io组件；

            //处理文件上传路径和大小限制
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //通过工厂设置缓冲区大小，超过大小将其放入临时目录
            factory.setSizeThreshold(1024 * 1024);//1M，文件大小
            factory.setRepository(tempFile);//设置临时目录

            //获取ServletFileUpload
            ServletFileUpload upload = new ServletFileUpload(factory);
            //监听文件上传速度，进度条
            upload.setProgressListener(new ProgressListener() {
                */
        /**
         * @param l : 已读取文件大小
         * @param l1 ： 文件总大小
         * @param i
         */
            /*
                @Override
                public void update(long l, long l1, int i) {
                    System.out.println("总大小：" + l1 + "----------已上传：" + l);
                }
            });
            //处理上传文件乱码
            upload.setHeaderEncoding("UTF-8");
            //设置单个文件最大值
            upload.setFileSizeMax(1024 * 1024 * 10);
            //设置总上传文件大小
            upload.setSizeMax(1024 * 1024 * 10);

            //准备工作做好后，开始处理文件;
            //解析请求，将表单中的每一个输入项封装成一个FileItem对象
            List<FileItem> fileItems = upload.parseRequest(request);
            //遍历表单中每一项，区分是普通输入还是文件上传
            for (FileItem fileItem : fileItems) {
                if (fileItem.isFormField()) {//普通输入项
                    String name = fileItem.getFieldName();//getFieldName指的是前端表单控件的name;
                    String value = fileItem.getString("UTF-8");//处理乱码
                    System.out.println("name=" + name + "--------value=" + value);
                } else {
                    //处理上传文件
                    // 拿到文件名
                    String uploadFileName = fileItem.getName();
                    System.out.println("上传文件命" + uploadFileName);
                    //判断文件名是否合法
                    if (uploadFileName == null || "".equals(uploadFileName.trim())) {
                        continue;//跳过当前循环
                    }
                    //获得上传文件名
                    String fileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
                    //获得文件后缀名
                    String fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);*/

        /*
         * 如果文件后缀名fileExtName不是我们所需要的 就直按return.不处理,告诉用户文件类型不对。
         */

/*                    System.out.println("文件信息[件名: " + fileName + " ---文件类型" + fileExtName + "]");

                    // 可以使用UID（唯一识别的通用码),保证文件名唯
                    // UUID. randomUUID(),随机生一个唯一识别的通用码;
                    String uuName = UUID.randomUUID().toString().replace("-", "");
                    //文件存放路径复用上述路径uploadPath

                    //获得文件上传流
                    InputStream is = fileItem.getInputStream();
                    //创建文件输出流
                    FileOutputStream out = new FileOutputStream(uploadPath + "/" + uuName + "." + fileExtName);
                    //创建缓冲区
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = is.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }
                    //关流
                    out.close();
                    is.close();

                    String msg = "文件上传成功";
                    fileItem.delete();//清除临时数据
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }*/
        try {
            // 创建 DiskFileItemFactory对象，处理文件路径或者大小限制
            DiskFileItemFactory factory = getDiskFileItemFactory(tempFile);
            /*
             * //通过这个工厂设置一个缓冲区,当上传的文件大于这个缓冲区的时候,将他放到临时文件 factory.setSizeThreshold(1024 *
             * 1024); //缓存区大小为1M factory.setRepository (file);//临时目录的保存目录,需要一个File
             */

            // 2、获取 ServletFileUpload
            ServletFileUpload upload = getServletFileUpload(factory);

            // 3、处理上传文件
            // 把前端请求解析，封装成FileItem对象，需要从ServletFileUpload对象中获取
            String msg = uploadParseRequest(upload, request, uploadPath);

            // Servlet请求转发消息
            System.out.println(msg);
            if(msg == "文件上传成功!") {
                // Servlet请求转发消息
                request.setAttribute("msg",msg);
                request.getRequestDispatcher("info.jsp").forward(request, response);
            }else {
                msg ="请上传文件";
                request.setAttribute("msg",msg);
                request.getRequestDispatcher("info.jsp").forward(request, response);
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        }

    }


    public static DiskFileItemFactory getDiskFileItemFactory(File file) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 通过这个工厂设置一个缓冲区,当上传的文件大于这个缓冲区的时候,将他放到临时文件中;
        factory.setSizeThreshold(1024 * 1024);// 缓冲区大小为1M
        factory.setRepository(file);// 临时目录的保存目录,需要一个file
        return factory;
    }

    public static ServletFileUpload getServletFileUpload(DiskFileItemFactory factory) {
        //获取ServletFileUpload
        ServletFileUpload upload = new ServletFileUpload(factory);
        //监听文件上传速度，进度条
        upload.setProgressListener(new ProgressListener() {
            /**
             * @param l : 已读取文件大小
             * @param l1 ： 文件总大小
             * @param i
             */
            @Override
            public void update(long l, long l1, int i) {
                System.out.println("总大小：" + l1 + "----------已上传：" + l);
            }
        });
        //处理上传文件乱码
        upload.setHeaderEncoding("UTF-8");
        //设置单个文件最大值
        upload.setFileSizeMax(1024 * 1024 * 10);
        //设置总上传文件大小
        upload.setSizeMax(1024 * 1024 * 10);

        return upload;
    }

    public static String uploadParseRequest(ServletFileUpload upload, HttpServletRequest request, String uploadPath)
            throws FileUploadException, IOException {
        String msg = "";
        //准备工作做好后，开始处理文件;
        //解析请求，将表单中的每一个输入项封装成一个FileItem对象
        List<FileItem> fileItems = upload.parseRequest(request);
        //遍历表单中每一项，区分是普通输入还是文件上传
        for (FileItem fileItem : fileItems) {
            if (fileItem.isFormField()) {//普通输入项
                String name = fileItem.getFieldName();//getFieldName指的是前端表单控件的name;
                String value = fileItem.getString("UTF-8");//处理乱码
                System.out.println("name=" + name + "--------value=" + value);
            } else {
                //处理上传文件
                // 拿到文件名
                String uploadFileName = fileItem.getName();
                System.out.println("上传文件命" + uploadFileName);
                //判断文件名是否合法
                if (uploadFileName == null || "".equals(uploadFileName.trim())) {
                    continue;//跳过当前循环
                }
                //获得上传文件名
                String fileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
                //获得文件后缀名
                String fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);

                /*
                 * 如果文件后缀名fileExtName不是我们所需要的 就直按return.不处理,告诉用户文件类型不对。
                 */

                System.out.println("文件信息[件名: " + fileName + " ---文件类型" + fileExtName + "]");

                // 可以使用UID（唯一识别的通用码),保证文件名唯
                // UUID. randomUUID(),随机生一个唯一识别的通用码;
                String uuName = UUID.randomUUID().toString().replace("-", "");
                //文件存放路径复用上述路径uploadPath

                //获得文件上传流
                InputStream is = fileItem.getInputStream();
                //创建文件输出流
                FileOutputStream out = new FileOutputStream(uploadPath + "/" + uuName + "." + fileExtName);
                //创建缓冲区
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
                //关流
                out.close();
                is.close();


                msg = "文件上传成功!";
                fileItem.delete();//清除临时数据
            }
        }
        return msg;
    }

}
