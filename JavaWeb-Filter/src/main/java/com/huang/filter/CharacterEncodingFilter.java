package com.huang.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author huanghudong
 * @ClassName CharacterEncodingFilter.java
 * @Description 过滤器
 * @createTime 2021年05月07日 15:44:00
 */
public class CharacterEncodingFilter implements Filter {
    //初始化，Web服务器启动时初始化
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //可以在服务器yi
        System.out.println("FilterName = " + filterConfig.getFilterName());
    }

    //类似与servlet中的doget、dopost

    /**
     * 1.过滤器中所有代码，在过滤的特定的请求的时候都会执行
     * 2.必须要过滤器继续通信，转交请求、响应
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType("text/html;charset=UTF-8");
        servletRequest.setCharacterEncoding("gbk");

        System.out.println("过滤器生效。。。。");
        //不放行就卡在这里，不继续下去
        //一个过滤器就是一个链，一个走完交到下一个过滤器。chain.doFilter将请求转发给过滤器链下一个filter , 如果没有filter那就是你请求的资源
        filterChain.doFilter(servletRequest,servletResponse);//让请求继续走，如果不写就停在这里
        System.out.println("过滤器执行完毕。。。。");

    }

    //销毁，Web服务器关闭的时候销毁
    @Override
    public void destroy() {
        System.out.println("过滤器销毁。。。。。");
    }
}
