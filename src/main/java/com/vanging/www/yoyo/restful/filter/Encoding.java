package com.vanging.www.yoyo.restful.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class Encoding implements Filter
{
    public void destroy()
    {

    }

    public void init(FilterConfig config) throws ServletException
    {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        req.setCharacterEncoding("utf-8");

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
