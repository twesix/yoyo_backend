package com.vanging.yoyo.persistence;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersistenceTest
{
    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void developmentConfig() throws Exception
    {
        Persistence.developmentConfig();
    }

    @Test
    public void productionConfig() throws Exception
    {
        Persistence.productionConfig();
    }

    @Test
    public void getSqlSession() throws Exception
    {
        SqlSession sqlSession = Persistence.getSqlSession();
        Assert.assertNotNull(sqlSession);
        String note_content = sqlSession.selectOne("findNoteById", "000");
        System.out.println(note_content);
    }

}