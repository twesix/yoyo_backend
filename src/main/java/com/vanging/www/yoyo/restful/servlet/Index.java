package com.vanging.www.yoyo.restful.servlet;

import com.alibaba.fastjson.JSON;
import com.vanging.www.yoyo.restful.response.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class Index extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Response finalResponse = new Response();

        finalResponse.setStatus("ok");
        finalResponse.setMessage("it works");

        JSON.writeJSONString(response.getWriter(), finalResponse);
    }
}