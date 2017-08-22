package com.vanging.www.yoyo.extractor;

import org.apache.poi.hslf.usermodel.*;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureData;

import java.io.*;
import java.util.*;

public class Image
{
    public static ArrayList extract(File f , String dirPath)
    {
        try
        {
            if(f.getName().matches(".*\\.ppt$"))
            {
                return extractPPT(f, dirPath);
            }
            if(f.getName().matches(".*\\.pptx$"))
            {
                return extractPPTX(f, dirPath);
            }
            return null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public static ArrayList extractPPT(File f , String dirPath) throws Exception
    {
        ArrayList<String> pictureNames = new ArrayList<String>();

        HSLFSlideShow ppt = new HSLFSlideShow(new FileInputStream(f));

        int num = 0;
        for(HSLFSlide slide : ppt.getSlides())
        {
            for(HSLFShape shape : slide.getShapes())
            {
                if(shape instanceof HSLFPictureShape)
                {
                    HSLFPictureData picture= ((HSLFPictureShape)shape).getPictureData();
                    String pictureName = num + picture.getType().extension;
                    pictureNames.add(pictureName);
                    String filePath = dirPath + File.separator + pictureName;
                    OutputStream os = new FileOutputStream(filePath);
                    os.write(picture.getData());
                    num++;
                }
            }
        }
        return pictureNames;
    }
    public static ArrayList extractPPTX(File f , String dirPath) throws Exception
    {
        ArrayList<String> pictureNames = new ArrayList<String>();

        XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(f));

        int num = 0;
        for(XSLFPictureData picture : ppt.getPictureData())
        {
            String name = picture.getFileName();
            String type = picture.getContentType().split("/")[1];
            InputStream is = picture.getInputStream();
            byte buf[] = new byte[1024*1024*20]; // 20M
            int len = is.read(buf);
            String pictureName = num + "." + type;
            pictureNames.add(pictureName);
            String filePath = dirPath + File.separator + pictureName;
            OutputStream os = new FileOutputStream(filePath);
            os.write(buf,0,len);
            num++;
        }
        return pictureNames;
    }
}
