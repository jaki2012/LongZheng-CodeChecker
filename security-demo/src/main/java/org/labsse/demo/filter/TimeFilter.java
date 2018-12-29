package org.labsse.demo.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author lijiechu
 * @create on 2018/12/25
 * @description
 */
//@Component
public class TimeFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("timer filter start");
        long beginTime = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("time filter 耗时" + (System.currentTimeMillis() - beginTime));
        System.out.println("timer filter finish");
    }

    @Override
    public void destroy() {
        System.out.println("timer filter destroy");
    }
}
