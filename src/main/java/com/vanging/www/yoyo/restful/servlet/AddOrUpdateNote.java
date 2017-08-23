package com.vanging.www.yoyo.restful.servlet;

import com.alibaba.fastjson.JSON;
import com.vanging.www.yoyo.persistence.Action;
import com.vanging.www.yoyo.persistence.entity.Note;
import com.vanging.www.yoyo.restful.response.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addOrUpdateNote")
public class AddOrUpdateNote extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Response finalResponse = new Response();

        String user_id = request.getParameter("user_id");
        String class_id = request.getParameter("class_id");
        String note_content = request.getParameter("note_content");

        if(user_id == null || class_id == null || note_content == null)
        {
            finalResponse.setStatus("param_wrong");
        }
        else
        {
            Note note = new Note();
            note.setClass_id(class_id);
            note.setUser_id(user_id);
            note.setNote_content(note_content);
            if(Action.insertOrUpdateNote(note))
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