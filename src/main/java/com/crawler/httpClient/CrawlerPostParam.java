package com.crawler.httpClient;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class CrawlerPostParam {
    public static void main(String[] args) throws Exception {
        //打开浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //输入网址发起post请求
        HttpPost httpPost = new HttpPost("https://daohang.qq.com/");

        //声明List集合，封装表单中的数据
        List<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair("fr","hmpage"));

        //创建表单对象
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(pairs,"utf-8");

        //将表单设置到post请求中
        httpPost.setEntity(formEntity);

        //使用httpClient发起请求
        CloseableHttpResponse response = httpClient.execute(httpPost);

        //解析数据，获取数据
        //判断状态码
        if (response.getStatusLine().getStatusCode() == 200){
            HttpEntity entity = response.getEntity();
            String s = EntityUtils.toString(entity, "utf-8");
            System.out.println(s.length());

        }
        response.close();

    }
}
