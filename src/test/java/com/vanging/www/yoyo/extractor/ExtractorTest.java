package com.vanging.www.yoyo.extractor;

import org.junit.Test;

import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;

import static org.junit.Assert.*;

public class ExtractorTest
{
    @Test
    public void extract() throws Exception
    {
        URL ppt = ClassLoader.getSystemResource("1.ppt");
        URL pptx = ClassLoader.getSystemResource("1.pptx");
        Extractor.extract("ppt", URLDecoder.decode(ppt.getFile(), "utf-8"), true);
        Extractor.extract("pptx", URLDecoder.decode(pptx.getFile(), "utf-8"), true);
    }

}