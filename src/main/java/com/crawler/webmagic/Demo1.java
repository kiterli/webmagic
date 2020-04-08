package com.crawler.webmagic;

import com.crawler.dao.MovieDao;
import com.crawler.entity.Movie;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Demo1 implements PageProcessor {
    private Site site = Site.me()
            .setCharset("utf-8")    //设置编码
            .setTimeOut(10 * 1000)    //设置超时时间 毫秒
            .setRetrySleepTime(3000)//设置重试时间 毫秒
            .setRetryTimes(3);   //设置重试次数

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        List<Selectable> nodes = html.css("div.tabcont ul.cf a.i").nodes();
        if (nodes.size() == 0) {
            //如果为空表示在详情列表
            String name = html.css("a#video-title", "text").toString();
            List<Selectable> intro = html.css("div.intro ul.lines li").nodes();
            List<Selectable> nodes1 = intro.get(1).css("a[target='_blank']", "text").nodes();
            String starring = "";
            for (Selectable selectable : nodes1) {
                starring += selectable.toString() + " ";
            }
            List<Selectable> types = intro.get(2).css("div.u-fl").nodes().get(0).css("a[target='_blank']", "text").nodes();
            List<Selectable> directors = intro.get(2).css("div.u-fl").nodes().get(1).css("a[target='_blank']", "text").nodes();
            String director = "";
            for (Selectable selectable : directors) {
                director += selectable.toString() + " ";
            }
            String year = intro.get(2).css("div.u-fl").nodes().get(2).css("a[target='_blank']", "text").toString();
            String area = intro.get(2).css("div.u-fl").nodes().get(3).css("a[target='_blank']", "text").toString();
            String synopsis = intro.get(3).css("p", "text").toString();
            String imgPath = html.css("div.hsd a.img-link img.img", "src").toString();
            MovieDao movieDao = MybatisUtils.getMapper(MovieDao.class);
            Movie movie = new Movie();
            movie.setName(name);
            movie.setStarring(starring);
            movie.setDirector(director);
            try {
                movie.setYear(new SimpleDateFormat("yyyy").parse(year));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            movie.setArea(area);
            movie.setSynopsis(synopsis);
            movie.setImgpath(imgPath);
            movieDao.insert(movie);
            for (Selectable selectable : types) {
                String type = selectable.toString();
                if ("爱情".equals(type)) {
                    movieDao.insertMovieType(movie.getId(), 3);
                } else if ("动画".equals(type)) {
                    movieDao.insertMovieType(movie.getId(), 9);
                } else if ("动作".equals(type) || "武侠".equals(type)) {
                    movieDao.insertMovieType(movie.getId(), 1);
                } else if ("纪录片".equals(type) || "文艺".equals(type) || "音乐".equals(type)) {
                    movieDao.insertMovieType(movie.getId(), 8);
                } else if ("恐怖".equals(type) || "惊悚".equals(type) || "悬疑".equals(type)) {
                    movieDao.insertMovieType(movie.getId(), 5);
                } else if ("剧情".equals(type) || "警匪".equals(type) || "伦理".equals(type) || "青春".equals(type) || "微电影".equals(type)) {
                    movieDao.insertMovieType(movie.getId(), 7);
                } else if ("科幻".equals(type)) {
                    movieDao.insertMovieType(movie.getId(), 4);
                } else if ("奇幻".equals(type)) {
                    movieDao.insertMovieType(movie.getId(), 6);
                } else if ("喜剧".equals(type)) {
                    movieDao.insertMovieType(movie.getId(), 2);
                } else if ("战争".equals(type) || "灾难".equals(type)) {
                    movieDao.insertMovieType(movie.getId(), 10);
                }
            }
        } else {
            //如果为不为空，表示在电影列表
            for (Selectable node : nodes) {
                page.addTargetRequest(node.links().toString());
            }
            String nextPage = html.css("div.page_component a.next").links().toString();
            page.addTargetRequest(nextPage);
        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new Demo1())
                .addUrl("http://kan.sogou.com/dianying/----/")
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(10000)))//设置布隆过滤器
                .thread(30)
                .run();
    }
}
