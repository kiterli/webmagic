package com.crawler.httpClient;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class CrawlerGet {
    public static void main(String[] args) throws Exception {
        //打开浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建get请求对象，设置url访问地址
        HttpGet httpGet = new HttpGet("https://daohang.qq.com/?fr=hmpage");

        //配置请求信息
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(1000)            //设置创建连接最长时间，单位毫秒
                .setConnectionRequestTimeout(1000)  //设置获取连接最长时间
                .setSocketTimeout(10 * 1000)        //设置数据传输最长时间
                .build();

        //给请求设置请求信息
        httpGet.setConfig(requestConfig);

        //使用httpClient发起请求
        CloseableHttpResponse response = httpClient.execute(httpGet);

        //解析数据，获取数据
        //判断状态码
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            String s = EntityUtils.toString(entity, "utf-8");
            System.out.println(s);

        }
        response.close();

    }
}
