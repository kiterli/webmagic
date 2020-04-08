package com.crawler.webmagic;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {
    private static SqlSession sqlSession;
    static {
        //读取配置文件
        InputStream resource = null;
        try {
            resource = Resources.getResourceAsStream("SqlMapConfig.xml");
            //创建SqlSessionFactory工厂
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(resource);
            //使用工厂生产SqlSession对象
            sqlSession = factory.openSession(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static <T> T getMapper(Class<T> obj){
        return sqlSession.getMapper(obj);
    }

}
