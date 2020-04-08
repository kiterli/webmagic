package com.crawler.httpClient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class CrawlerPost {
    public static void main(String[] args) throws Exception {
        //打开浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("https://daohang.qq.com/?fr=hmpage");

        //使用httpClient发起请求
        CloseableHttpResponse response = httpClient.execute(httpPost);

        //解析数据，获取数据
        //判断状态码
        if (response.getStatusLine().getStatusCode() == 200){
            HttpEntity entity = response.getEntity();
            String s = EntityUtils.toString(entity, "utf-8");
            System.out.println(s);

        }
        response.close();

    }
}
