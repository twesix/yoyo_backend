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

@WebServlet("/deleteNote")
public class DeleteNote extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Response finalResponse = new Response();

        String user_id = request.getParameter("user_id");
        String class_id = request.getParameter("class_id");

        if(user_id == null || class_id == null)
        {
            finalResponse.setStatus("param_wrong");
        }
        else
        {
            if(Action.deleteNote(user_id, class_id))
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