package com.crawler.httpClient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class CrawlerGetParam {
    public static void main(String[] args) throws Exception {
        //打开浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //get请求参数 https://daohang.qq.com/?fr=hmpage
        URIBuilder uriBuilder = new URIBuilder("https://daohang.qq.com/");
        uriBuilder.setParameter("fr","hmpage");
        //输入网址发起get请求
        HttpGet httpGet = new HttpGet(uriBuilder.build());

        //使用httpClient发起请求
        CloseableHttpResponse response = httpClient.execute(httpGet);

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
