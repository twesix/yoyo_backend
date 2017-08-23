package com.vanging.www.yoyo.persistence.mapper;

import com.vanging.www.yoyo.persistence.entity.Note;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoteMapper
{
    public List<Note> selectByCid(@Param("class_id")String class_id);
    public List<Note> selectByUid(@Param("user_id")String user_id);
    public Note selectByCidAndUid(@Param("class_id") String class_id, @Param("user_id") String user_id);
    public void insert(Note note);
    public void delete(Note note);
    public void update(Note note);
}
