package com.huang.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author huanghudong
 * @ClassName OnlineCountListener.java
 * @Description 统计网站在线人数 ： 统计session
 * @createTime 2021年05月07日 23:43:00
 */
//监听器的作用就是监听程序运行时的某种动作，做出相应的处理，一般用处不大，很少与前端交互
public class OnlineCountListener implements HttpSessionListener {
//    该监听器的触发条件是每次session创建的时候

    //创建session监听： 看你的一举一动
    //一旦创建Session就会触发一次这个事件！
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
        //创建不同的session对应不同的id
        System.out.println(se.getSession().getId());
        Integer count = (Integer) context.getAttribute("OnlineCount");
        if (count == null) {//第一次创建session
            count = new Integer(1);
        } else {
            count = new Integer(count.intValue() + 1);
        }
        context.setAttribute("OnlineCount", count);
    }

    //销毁session监听
    //一旦销毁Session就会触发一次这个事件！
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext ctx = se.getSession().getServletContext();

        Integer onlineCount = (Integer) ctx.getAttribute("OnlineCount");

        if (onlineCount == null) {
            onlineCount = new Integer(0);
        } else {
            int count = onlineCount.intValue();
            onlineCount = new Integer(count - 1);
        }

        ctx.setAttribute("OnlineCount", onlineCount);
    }

     /*
     常识：在tomcat启动时，会创建多次session，只有最后一次生效，其他的说创建失败产生的
    Session销毁：
    1. 手动销毁  getSession().invalidate();
    2. 自动销毁
     */
}
