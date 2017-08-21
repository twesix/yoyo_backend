package com.vanging.www.yoyo.extractor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Extractor
{
    public static boolean extract(String cid, String file, boolean development) throws IOException
    {
        String rootDirPath;
        if( ! development)
        {
            rootDirPath = "/web/sites/www.vanging.com/yoyo/classes";
        }
        else
        {
            rootDirPath = "I:\\大创项目\\web\\classes";
        }

        String classDirPath = rootDirPath + File.separator + cid;
        File classDir = new File(classDirPath);
        if(!classDir.exists())
        {
            if(!classDir.mkdir())
            {
                System.out.println("创建目录失败 ： " + classDirPath);
                return false;
            }
        }

        File jsonFile = new File(classDirPath + File.separator + "index.json");

        String jsonText = "[";
        String textType = "\"type\":\"text\"";
        String imgType = "\"type\":\"img\"";

        File pptFile = new File(file);

        List pictureNames = Image.extract(pptFile,classDirPath);
        for(Object picture : pictureNames)
        {
            jsonText += "{";
            jsonText += imgType;
            jsonText += ",\"url\":\"";
            jsonText += picture.toString();
            jsonText += "\"},";
        }

        String text = Text.extract(pptFile);
        for(String line : text.split("(\\r|\\n)+"))
        {
            if(!line.matches("\\s*"))
            {
                jsonText += "{";
                jsonText += textType;
                jsonText += ",\"content\":\"";
                jsonText += line;
                jsonText += "\"},";
            }
        }

        jsonText = jsonText.substring(0,jsonText.length()-1);
        jsonText += "]";

        FileWriter fw = new FileWriter(jsonFile);
        fw.write(jsonText);
        fw.close();

        return true;
    }
}
