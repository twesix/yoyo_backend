package com.vanging.yoyo.persistence;

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
    public static void config()
    {
        try
        {
            InputStream inputStream = Resources.getResourceAsStream(configFile);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

//class MyConfiguration
//{
//    static DataSource dataSource = BlogDataSourceFactory.getBlogDataSource();
//    static TransactionFactory transactionFactory = new JdbcTransactionFactory();
//    static Environment environment = new Environment("development", transactionFactory, dataSource);
//    static Configuration configuration = new Configuration(environment);
//    static SqlSessionFactory sqlSessionFactory;
//
//    public static void config()
//    {
//        configuration.addMapper(BlogMapper.class);
//        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
//    }
//
//    public static SqlSession getSqlSession()
//    {
//        return sqlSessionFactory.openSession();
//    }
//}
