package com.crawler.httpClient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientPool {
    public static void main(String[] args) throws Exception {
        //创建连接池管理器
        PoolingHttpClientConnectionManager poolHttp = new PoolingHttpClientConnectionManager();

        //使用连接池管理器发起请求
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(poolHttp).build();
        HttpGet httpGet = new HttpGet("https://daohang.qq.com/?fr=hmpage");

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
