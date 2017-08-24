package com.vanging.www.yoyo.restful.servlet;

import com.alibaba.fastjson.JSON;
import com.vanging.www.yoyo.extractor.Extractor;
import com.vanging.www.yoyo.persistence.Action;
import com.vanging.www.yoyo.persistence.entity.Class;
import com.vanging.www.yoyo.restful.response.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@MultipartConfig
@WebServlet("/releaseClass")
public class ReleaseClass extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        boolean development = false;
        Response finalResponse = new Response();

        String class_name = request.getParameter("class_name");
        String class_releaser = request.getParameter("class_releaser");
        String class_location = request.getParameter("class_location");

        Part class_file = request.getPart("class_file");

        if(class_name == null || class_location == null || class_releaser == null || class_file == null)
        {
            finalResponse.setStatus("param_wrong");
        }
        else
        {
            String header = class_file.getHeader("content-disposition");
            String filename = header.substring(header.indexOf("filename=") + 10, header.length() - 1);

            if( ! filename.matches(".*\\.ppt$") &&  ! filename.matches(".*\\.pptx$"))
            {
                finalResponse.setStatus("not_a_ppt(x)_file");
            }
            else
            {
                String class_id = UUID.randomUUID().toString().toLowerCase();

                String rootDirPath;
                if( ! development)
                {
                    rootDirPath = "/web/sites/www.vanging.com/yoyo/upload";
                }
                else
                {
                    rootDirPath = "I:\\大创项目\\web\\upload";
                }

                String uploadFilePath = rootDirPath + File.separator + filename;

                System.out.println(uploadFilePath);
                File uploadFile = new File(uploadFilePath);

                InputStream inputStream = class_file.getInputStream();
                FileOutputStream outputStream = new FileOutputStream(uploadFile);
                int len = -1;
                byte[] bytes = new byte[1024];
                while ((len = inputStream.read(bytes)) != -1)
                {
                    outputStream.write(bytes, 0, len);
                }

                outputStream.close();
                inputStream.close();
                class_file.delete();

                boolean success;
                try
                {
                    success = Extractor.extract(class_id, uploadFilePath, development);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    success = false;
                }
                if(success)
                {
                    Class _class = new Class();
                    _class.setClass_id(class_id);
                    _class.setClass_location(class_location);
                    _class.setClass_name(class_name);
                    _class.setClass_releaser(class_releaser);
                    if(Action.insertClass(_class))
                    {
                        finalResponse.setStatus("ok");
                    }
                    else
                    {
                        finalResponse.setStatus("db_error");
                    }
                }
                else
                {
                    finalResponse.setStatus("extractor_error");
                }

            }

        }

        JSON.writeJSONString(response.getWriter(), finalResponse);
    }
}