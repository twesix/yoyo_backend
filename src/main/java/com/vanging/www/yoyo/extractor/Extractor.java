package com.vanging.www.yoyo.extractor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Extractor
{
    public static boolean extract(String cid, String fileAbsolutePath, boolean development) throws IOException
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

        File pptFile = new File(fileAbsolutePath);

        List pictureNames = Image.extract(pptFile,classDirPath);;
        if(pictureNames == null)
        {
            DeleteDirectory.deleteDir(classDir);
            return false;
        }
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
            if( ! line.matches("\\s*"))
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

        jsonText = jsonText.replace("\\", "/");

        FileWriter fw = new FileWriter(jsonFile);
        fw.write(jsonText);
        fw.close();

        return true;
    }
}
class DeleteDirectory
{
    public static boolean deleteDir(File dir)
    {
        if (dir.isDirectory())
        {
            String[] children = dir.list();
            if(children != null)
            {
                //递归删除目录中的子目录下所有文件
                for (int i=0; i<children.length; i++)
                {
                    boolean success = deleteDir(new File(dir, children[i]));
                    if(!success)
                    {
                        return false;
                    }
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
    /**
     *测试
     */
    public static void main(String[] args)
    {
        String newDir2 = "I:\\大创项目\\web\\classes\\ppt";
        boolean success = deleteDir(new File(newDir2));
        if(success)
        {
            System.out.println("Successfully deleted populated directory: " + newDir2);
        }
        else
        {
            System.out.println("Failed to delete populated directory: " + newDir2);
        }
    }
}