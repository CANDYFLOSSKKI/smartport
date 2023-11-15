package com.demo.smartport.Util.Handler;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class HttpRequestHandler {

    private static final CloseableHttpClient httpClient = HttpClients.custom().build();

    public String postJson(String url, String json) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        try(CloseableHttpResponse response = httpClient.execute(httpPost)) {
            return EntityUtils.toString( response.getEntity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String postForm(String url, Map<String, String> kvMap) {
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<>();
        kvMap.forEach((key, value) -> nvps.add(new BasicNameValuePair(key, value)));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
        try(CloseableHttpResponse response = httpClient.execute(httpPost)) {
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getString(String url) {
        HttpGet httpget = new HttpGet(url);
        try(CloseableHttpResponse response = httpClient.execute(httpget)) {
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getBytes(String url) {
        HttpGet httpget = new HttpGet(url);
        try(CloseableHttpResponse response = httpClient.execute(httpget)) {
            return EntityUtils.toByteArray(response.getEntity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String putJson(String url, String json,Map<String, String> headerMap) {
        HttpPut httpPut = new HttpPut(url);
        headerMap.entrySet().forEach(entry->httpPut.addHeader(entry.getKey(), entry.getValue()));
        httpPut.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        try(CloseableHttpResponse response = httpClient.execute(httpPut)) {
            return EntityUtils.toString( response.getEntity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
