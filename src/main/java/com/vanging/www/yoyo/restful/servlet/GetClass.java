package com.vanging.www.yoyo.restful.servlet;

import com.alibaba.fastjson.JSON;
import com.vanging.www.yoyo.persistence.Action;
import com.vanging.www.yoyo.restful.response.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.vanging.www.yoyo.persistence.entity.Class;

@WebServlet("/getClass")
public class GetClass extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Response finalResponse = new Response();

        String class_id = request.getParameter("class_id");

        if(class_id == null)
        {
            finalResponse.setStatus("param_wrong");
        }
        else
        {
            finalResponse.setMessage(Action.selectClassByCid(class_id));
            finalResponse.setStatus("ok");
        }

        JSON.writeJSONString(response.getWriter(), finalResponse);
    }
}