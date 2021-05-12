package com.huang.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author huanghudong
 * @ClassName CharsetEncodingFilter1.java
 * @Description TODO
 * @createTime 2021年05月12日 21:31:00
 */
public class CharsetEncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
