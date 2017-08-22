package com.vanging.www.yoyo.restful.servlet;

import com.alibaba.fastjson.JSON;
import com.vanging.www.yoyo.extractor.Extractor;
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
        boolean development = true;
        Response finalResponse = new Response();

        String name = request.getParameter("name");
        String releaser = request.getParameter("releaser");
        String location = request.getParameter("location");

        Part file = request.getPart("file");

        if(name == null || location == null || releaser == null || file == null)
        {
            finalResponse.setStatus("param_wrong");
        }
        else
        {
            String header = file.getHeader("content-disposition");
            String filename = header.substring(header.indexOf("filename=") + 10, header.length() - 1);

            System.out.println(filename);

            if( ! filename.matches(".*\\.ppt$") &&  ! filename.matches(".*\\.pptx$"))
            {
                finalResponse.setStatus("not_a_ppt(x)_file");
            }
            else
            {
                InputStream is = file.getInputStream();
                String cid = UUID.randomUUID().toString().toLowerCase();

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
                File uploadFile = new File(uploadFilePath);

                InputStream inputStream = file.getInputStream();
                FileOutputStream outputStream = new FileOutputStream(new File(uploadFilePath));
                int len = -1;
                byte[] bytes = new byte[1024];
                while ((len = inputStream.read(bytes)) != -1)
                {
                    outputStream.write(bytes, 0, len);
                }

                outputStream.close();
                inputStream.close();
                file.delete();

                boolean success = Extractor.extract(cid, uploadFilePath, development);
                if(success)
                {
                    finalResponse.setStatus("ok");
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

class UploadUtils
{
    /**
     * 根据文件的路径获取文件真实名称
     *
     * @param path
     *            文件的路径
     * @return 文件名称
     */
    public static String getRealName(String path)
    {
        int index = path.lastIndexOf("\\");

        if (index == -1)
        {
            index = path.lastIndexOf("/");
        }

        return path.substring(index + 1);
    }

    /**
     * 根据文件名返回一个目录
     *
     * @param name
     *            文件名称
     * @return 目录
     */
    public static String getDir(String name)
    {
        int i = name.hashCode();
        String hex = Integer.toHexString(i);
        int j = hex.length();

        for (int k = 0; k < 8 - j; k++)
        {
            hex = "0" + hex;
        }

        return "/" + hex.charAt(0) + "/" + hex.charAt(1);
    }
}