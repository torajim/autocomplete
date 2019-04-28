package com.torajim.autocomplete.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.torajim.autocomplete.repository.EsRestClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EsService {
    @Autowired
    EsRestClient esRestClient;

    @Value("${autocomplete.index}")
    private String index;

    @Value("${autocomplete.type")
    private String type;

    @Autowired
    ObjectMapper objectMapper;

    public void postWord(String word, long score){
        String url = index + "/" + type;
        AutoCompleteDoc doc = AutoCompleteDoc.builder()
                .word(word)
                .score(score)
                .build();
        try {
            Response response = esRestClient.callEsApi("POST", url, objectMapper.writeValueAsString(doc));
            if(response != null && response.getEntity() != null){
                log.info(response.getEntity().toString());
            }
        } catch (IOException e) {
            log.error(e.toString());
            e.printStackTrace();
        }
    }

    public List<String> getWordByPrefix(String prefix){
        List<String> hitsWords = new ArrayList<>();
        String url = index + "/" + type + "_search";
        String searchJson = "{\"query\":{\"prefix\":{\"word\":\"" + prefix + "\"}}, \"sort\":[{\"score\":\"desc\"}]}";
        try {
            Response response = esRestClient.callEsApi("GET", url, searchJson);
            if(response != null && response.getEntity() != null){
                log.info(response.getEntity().toString());
            }else{
                JsonNode resultNode = objectMapper.readTree(EntityUtils.toString(response.getEntity()));
                JsonNode hitsNode = resultNode.path("hits").path("hits");
                if(hitsNode.isArray()){
                    for(JsonNode node : hitsNode){
                        String hitsWord = node.path("_source").path("word").asText();
                        hitsWords.add(hitsWord);
                        log.debug("query:" + prefix + ", hitsWord:" + hitsWord);
                    }
                }
            }
        } catch (IOException e) {
            log.error(e.toString());
            e.printStackTrace();
        }
        return hitsWords;
    }
}