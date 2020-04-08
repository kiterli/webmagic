package com.crawler;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HttpClientJsoup {
    public static void main(String[] args) throws Exception {
        PoolingHttpClientConnectionManager pool = new PoolingHttpClientConnectionManager();
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(pool).build();
        HttpGet httpGet = new HttpGet("http://kan.sogou.com/dianying/----/");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200){
            String s = EntityUtils.toString(response.getEntity(), "utf-8");
            Document d = Jsoup.parse(s);
            Element title = d.select("div.cell > a >img").first();
            System.out.println(title.attr("src"));
        }
        response.close();
    }
}
