package com.vanging.www.yoyo.persistence.mappers;

import com.vanging.www.yoyo.persistence.Persistence;
import com.vanging.www.yoyo.persistence.models.Note;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NoteMapperTest {
    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void findById() throws Exception
    {
        SqlSession sqlSession = Persistence.getSqlSession();
        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);
        Note note = noteMapper.findById("001");
        System.out.println(note.getNote_content());
    }

}