package com.vanging.www.yoyo.restful.servlet;

import com.alibaba.fastjson.JSON;
import com.vanging.www.yoyo.persistence.Action;
import com.vanging.www.yoyo.persistence.entity.Profile;
import com.vanging.www.yoyo.restful.response.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/setLocation")
public class SetLocation extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Response finalResponse = new Response();

        String user_id = request.getParameter("user_id");
        String user_location = request.getParameter("user_location");

        if(user_id == null || user_location == null)
        {
            finalResponse.setStatus("param_wrong");
        }
        else
        {
            Profile profile = new Profile();
            profile.setUser_id(user_id);
            profile.setUser_location(user_location);
            if(Action.updateLocation(profile))
            {
                finalResponse.setStatus("ok");
            }
            else
            {
                finalResponse.setStatus("db_error");
            }
        }

        JSON.writeJSONString(response.getWriter(), finalResponse);
    }
}