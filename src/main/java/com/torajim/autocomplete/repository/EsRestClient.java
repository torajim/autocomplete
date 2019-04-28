package com.torajim.autocomplete.repository;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EsRestClient {

    @Value("${elasticsearch.host}")
    private String host;

    @Value("${elasticsearch.port}")
    private int port;

    private RestClient restClient;

    private RestClient getRestClient(){
        if(restClient == null){
            restClient = RestClient.builder(new HttpHost(host, port, "http")).build();
        }

        return restClient;
    }

    public Response callEsApi(String httpMethod, String url, String jsonString) throws IOException {

        Response response = null;
        Request request = new Request(httpMethod, url);
        request.addParameter("pretty", "true");

        if("GET".equals(httpMethod) || "DELETE".equals(httpMethod)){
            response = getRestClient().performRequest(request);
        }else{
            HttpEntity entity = new NStringEntity(jsonString, ContentType.APPLICATION_JSON);
            request.setEntity(entity);
            response = getRestClient().performRequest(request);
        }

        return response;
    }
}