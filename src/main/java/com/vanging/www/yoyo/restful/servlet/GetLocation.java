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

@WebServlet("/getLocation")
public class GetLocation extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Response finalResponse = new Response();

        String user_id = request.getParameter("user_id");

        if(user_id == null)
        {
            finalResponse.setStatus("param_wrong");
        }
        else
        {
            String location = Action.selectLocation(user_id);
            if(location == null)
            {
                finalResponse.setStatus("not_exist");
            }
            else
            {
                finalResponse.setStatus("ok");
                finalResponse.setMessage(location);
            }
        }

        JSON.writeJSONString(response.getWriter(), finalResponse);
    }
}