package com.vanging.www.yoyo.extractor;

import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;
import org.apache.poi.xslf.usermodel.XSLFSlideShow;

import java.io.File;
import java.io.FileInputStream;

public class Text
{
    public static String extract(File f)
    {
        try
        {
            if(f.getName().matches(".*\\.ppt$"))
            {
                return extractPPT(f);
            }
            if(f.getName().matches(".*\\.pptx$"))
            {
                return extractPPTX(f);
            }
            return null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static String extractPPT(File f) throws Exception
    {
        String text = "";

        System.out.println("正在提取文字 ： " + f.getName());
        PowerPointExtractor ppt = new PowerPointExtractor(new FileInputStream(f));
        text = ppt.getText();

        return text;
    }

    public static String extractPPTX(File f) throws Exception
    {
        String text = "";

        System.out.println("正在提取文字 ： " + f.getName());
        XSLFPowerPointExtractor ppt = new XSLFPowerPointExtractor(new XSLFSlideShow(f.getAbsolutePath()));
        text = ppt.getText();

        return text;
    }
}
