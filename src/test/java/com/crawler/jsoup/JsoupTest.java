package com.crawler.jsoup;

import com.crawler.dao.MovieDao;
import com.crawler.entity.Movie;
import com.crawler.webmagic.MybatisUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class JsoupTest {
    @Test
    public void test() throws Exception {
        //解析url地址，第一个参数是url，第二个参数是访问时超时时间
        Document document = Jsoup.parse(new URL("https://daohang.qq.com/?fr=hmpage"),10000);
        Element title = document.select("title").first();
        System.out.println(title.text());
        System.out.println(title.html());

    }

    @Test
    public void testMybatis() throws Exception {
        MovieDao mapper = MybatisUtils.getMapper(MovieDao.class);
        List<Movie> movies = mapper.queryAllByLimit(1, 3);
        System.out.println(movies);

    }
}
