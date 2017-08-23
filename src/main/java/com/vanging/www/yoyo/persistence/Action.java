package com.vanging.www.yoyo.persistence;

import com.vanging.www.yoyo.extractor.Extractor;
import com.vanging.www.yoyo.persistence.entity.Class;
import com.vanging.www.yoyo.persistence.entity.Note;
import com.vanging.www.yoyo.persistence.entity.Profile;
import com.vanging.www.yoyo.persistence.mapper.ClassMapper;
import com.vanging.www.yoyo.persistence.mapper.NoteMapper;
import com.vanging.www.yoyo.persistence.mapper.ProfileMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
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
            return NoteAction.insert(note);
        }
        else
        {
            return NoteAction.update(note);
        }
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
    public static Class selectClassByCid(String class_id)
    {
        return ClassAction.selectById(class_id);
    }

    public static String selectLocation(String user_id)
    {
        return ProfileAction.selectLocation(user_id);
    }

    public static boolean updateLocation(Profile profile)
    {
        if(ProfileAction.selectLocation(profile.getUser_id()) == null)
        {
            return ProfileAction.insertLocation(profile);
        }
        else
        {
            return ProfileAction.updateLocation(profile);
        }
    }

    public static List selectNotes(String user_id)
    {
        return NoteAction.selectByUid(user_id);
    }

    public static HashMap getStatistics(String class_id)
    {
        return NoteAction.getStatistics(class_id);
    }

    public static List selectClassByKeyWord(String key_word)
    {
        return ClassAction.selectByKeyWord(key_word);
    }

    public static boolean insertClass(Class _class)
    {
        return ClassAction.insert(_class);
    }
}

class NoteAction
{
    public static HashMap getStatistics(String class_id)
    {
        SqlSession sqlSession = Persistence.getSqlSession();
        NoteMapper mapper = sqlSession.getMapper(NoteMapper.class);

        List notes = mapper.selectByCid(class_id);

        sqlSession.close();

        HashMap result = new HashMap();

        for(Object note : notes)
        {
            String content[] = ((Note) note).getNote_content().split(",");
            for(String s : content)
            {
                if(result.get(s) == null)
                {
                    result.put(s, 1);
                }
                else
                {
                    result.put(s, (int)result.get(s) + 1);
                }
            }
        }

        return result;
    }

    public static List selectByUid(String user_id)
    {
        SqlSession sqlSession = Persistence.getSqlSession();
        NoteMapper mapper = sqlSession.getMapper(NoteMapper.class);

        List notes = mapper.selectByUid(user_id);

        sqlSession.close();

        return notes;
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

    public static List selectByKeyWord(String key_word)
    {
        SqlSession sqlSession = Persistence.getSqlSession();
        ClassMapper mapper = sqlSession.getMapper(ClassMapper.class);

        List classes = mapper.selectByKeyWord(key_word);

        sqlSession.close();
        return classes;
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

class ProfileAction
{
    public static String selectLocation(String user_id)
    {
        SqlSession sqlSession = Persistence.getSqlSession();
        ProfileMapper mapper = sqlSession.getMapper(ProfileMapper.class);

        String location = mapper.selectLocation(user_id);

        sqlSession.close();
        return location;
    }

    public static boolean insertLocation(Profile profile)
    {
        SqlSession sqlSession = Persistence.getSqlSession();
        ProfileMapper mapper = sqlSession.getMapper(ProfileMapper.class);

        try
        {
            mapper.insertLocation(profile);
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

    public static boolean updateLocation(Profile profile)
    {
        SqlSession sqlSession = Persistence.getSqlSession();
        ProfileMapper mapper = sqlSession.getMapper(ProfileMapper.class);

        try
        {
            mapper.updateLocation(profile);
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