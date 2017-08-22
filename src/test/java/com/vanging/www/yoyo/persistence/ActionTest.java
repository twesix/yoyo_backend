package com.vanging.www.yoyo.persistence;

import com.vanging.www.yoyo.persistence.entity.Note;
import org.junit.Test;

import static org.junit.Assert.*;

public class ActionTest
{

    @Test
    public void Test() throws Exception
    {
    }

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
        Action.selectClass("class_id_" + Math.random());
    }
}