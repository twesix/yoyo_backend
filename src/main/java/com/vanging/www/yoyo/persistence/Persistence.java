package com.vanging.www.yoyo.persistence;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.InputStream;

public class Persistence
{
    private static String configFile = "mybatis.config.xml";
    private static SqlSessionFactory sqlSessionFactory;

    static
    {
        try
        {
            developmentConfig();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void developmentConfig() throws Exception
    {
        InputStream inputStream = Resources.getResourceAsStream(configFile);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, "development");
    }

    public static void productionConfig() throws Exception
    {
        InputStream inputStream = Resources.getResourceAsStream(configFile);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, "production");
    }

    public static SqlSession getSqlSession()
    {
        return sqlSessionFactory.openSession();
    }
}