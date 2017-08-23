package com.vanging.www.yoyo.persistence;

import com.alibaba.fastjson.JSON;
import com.vanging.www.yoyo.persistence.entity.Note;
import com.vanging.www.yoyo.persistence.entity.Class;
import com.vanging.www.yoyo.persistence.entity.Profile;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class ActionTest
{

    @Test
    public void insertOrUpdateNote() throws Exception
    {
        Note note = new Note();
        note.setClass_id("class_id");
        note.setUser_id("user_id");
        note.setNote_content("note_content");

        Action.insertOrUpdateNote(note);

        note.setNote_id(null);
        note.setUser_id("user_id_" + Math.random());
        note.setClass_id("class_id_" + Math.random());
        note.setNote_content("note_content_" + Math.random());

        Action.insertOrUpdateNote(note);
    }

    @Test
    public void deleteNote() throws Exception
    {
        Action.deleteNote("user_id_" + Math.random(), "class_id_" + Math.random());
    }

    @Test
    public void selectClass() throws Exception
    {
        Action.selectClassByCid("class_id_" + Math.random());
    }

    @Test
    public void Test() throws Exception
    {
    }

    @Test
    public void selectLocation() throws Exception
    {
        System.out.println(Action.selectLocation("user_id"));
    }

    @Test
    public void selectNotes() throws Exception
    {
        List notes = Action.selectNotes("user_id");
        String result = JSON.toJSONString(notes);
        System.out.println(result);
    }

    @Test
    public void getStatistics() throws Exception
    {
        HashMap result = Action.getStatistics("class_id");
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void queryClass() throws Exception
    {
        List result = Action.selectClassByKeyWord("class_location");
        System.out.println(JSON.toJSONString(result));
        result = Action.selectClassByKeyWord("class_name");
        System.out.println(JSON.toJSONString(result));
        result = Action.selectClassByKeyWord("class_releaser");
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void insertClass() throws Exception
    {
        boolean success;
        Class _class = new Class();

        _class.setClass_id("class_id_" + Math.random());
        _class.setClass_name("class_name_" + Math.random());
        _class.setClass_releaser("class_releaser_" + Math.random());
        _class.setClass_location("class_location_" + Math.random());

        success = Action.insertClass(_class);
        Assert.assertTrue(success);
    }

    @Test
    public void updateLocation() throws Exception
    {
        Profile profile = new Profile();
        profile.setUser_id("user_id");
        profile.setUser_location("user_location");
        Assert.assertTrue(Action.updateLocation(profile));

        Profile profile1 = new Profile();
        profile.setUser_id("user_id_" + Math.random());
        profile.setUser_location("user_location_" + Math.random());
        Assert.assertTrue(Action.updateLocation(profile));
    }
}