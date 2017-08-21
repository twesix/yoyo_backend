package com.vanging.www.yoyo.restful.servlet;

import com.alibaba.fastjson.JSON;
import com.vanging.www.yoyo.restful.response.Response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QueryClass extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Response finalResponse = new Response();

        String param = request.getParameter("param");
        String param1 = request.getParameter("param1");

        if(param == null || param1 == null)
        {
            finalResponse.setStatus("param_wrong");
        }
        else
        {

        }

        JSON.writeJSONString(response.getWriter(), finalResponse);
    }
}