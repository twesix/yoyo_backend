package com.vanging.www.yoyo.persistence;

import com.vanging.www.yoyo.extractor.Extractor;
import com.vanging.www.yoyo.persistence.entity.Class;
import com.vanging.www.yoyo.persistence.entity.Note;
import com.vanging.www.yoyo.persistence.mapper.ClassMapper;
import com.vanging.www.yoyo.persistence.mapper.NoteMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.UUID;

public class Action
{
    static
    {
        try
        {

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    // addOrUpdateNote
    public static boolean insertOrUpdateNote(Note note)
    {
        if(NoteAction.selectByCidAndUid(note.getClass_id(), note.getUser_id()) == null)
        {
            NoteAction.insert(note);
        }
        else
        {
            NoteAction.update(note);
        }
        return true;
    }

    // deleteNote
    public static boolean deleteNote(String user_id, String class_id)
    {
        Note note = new Note();
        note.setUser_id(user_id);
        note.setClass_id(class_id);

        NoteAction.delete(note);
        return true;
    }

    // getClass
    public static Class selectClass(String class_id)
    {
        return ClassAction.selectById(class_id);
    }

}

class NoteAction
{
    public static Note selectByNid(String note_id)
    {
        SqlSession sqlSession = Persistence.getSqlSession();
        NoteMapper mapper = sqlSession.getMapper(NoteMapper.class);

        Note note = mapper.selectByNid(note_id);

        sqlSession.close();

        return note;
    }

    public static Note selectByCidAndUid(String note_id, String user_id)
    {
        SqlSession sqlSession = Persistence.getSqlSession();
        NoteMapper mapper = sqlSession.getMapper(NoteMapper.class);

        Note note = mapper.selectByCidAndUid(note_id, user_id);

        sqlSession.close();

        return note;
    }

    public static boolean update(Note note)
    {
        SqlSession sqlSession = Persistence.getSqlSession();
        NoteMapper mapper = sqlSession.getMapper(NoteMapper.class);

        try
        {
            mapper.update(note);
            sqlSession.commit();
            sqlSession.close();
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            sqlSession.rollback();
            sqlSession.close();
            return false;
        }
//        finally
//        {
//            // TODO :　code can reach here ? ?
//            sqlSession.close();
//        }
    }

    public static boolean delete(Note note)
    {
        SqlSession sqlSession = Persistence.getSqlSession();
        NoteMapper mapper = sqlSession.getMapper(NoteMapper.class);

        try
        {
            mapper.delete(note);
            sqlSession.commit();
            sqlSession.close();
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            sqlSession.rollback();
            sqlSession.close();
            return false;
        }
    }

    public static boolean insert(Note note)
    {
        SqlSession sqlSession = Persistence.getSqlSession();
        NoteMapper mapper = sqlSession.getMapper(NoteMapper.class);

        if(note.getNote_id() == null)
        {
            note.setNote_id(UUID.randomUUID().toString().toLowerCase());
        }

        try
        {
            mapper.insert(note);
            sqlSession.commit();
            sqlSession.close();
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            sqlSession.rollback();
            sqlSession.close();
            return false;
        }
    }
}

class ClassAction
{
    public static Class selectById(String class_id)
    {
        SqlSession sqlSession = Persistence.getSqlSession();
        ClassMapper mapper = sqlSession.getMapper(ClassMapper.class);

        Class _class = mapper.selectById(class_id);

        sqlSession.close();
        return _class;
    }

    public static boolean insert(Class _class)
    {
        SqlSession sqlSession = Persistence.getSqlSession();
        ClassMapper mapper = sqlSession.getMapper(ClassMapper.class);

        try
        {
            mapper.insert(_class);
            sqlSession.commit();
            sqlSession.close();
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            sqlSession.rollback();
            sqlSession.close();
            return false;
        }
    }
}