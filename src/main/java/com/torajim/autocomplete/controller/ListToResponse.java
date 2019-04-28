package com.torajim.autocomplete.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Component
@Slf4j
public class ListToResponse {
    @Autowired
    ObjectMapper objectMapper;

    public ResponseEntity<String> listToResponse(List<String> stringList){
        ObjectMapper mapper = new ObjectMapper();
        String resp = "";
        try {
            resp = mapper.writeValueAsString(stringList);
        } catch (JsonProcessingException e) {
            log.error(e.toString());
        }

        HttpHeaders headers = new HttpHeaders();

        if(stringList != null && stringList.size() > 0){
            try {
                headers.add("Cache-Control", "public");
                headers.add("Cache-Control", "max-age=3600");
                headers.add("Content-Length", resp.getBytes("UTF-8").length + "");
            } catch (UnsupportedEncodingException e) {
                log.error(e.toString());
            }
            return new ResponseEntity<String>(resp, headers, HttpStatus.OK);
        }else{
            return new ResponseEntity<String>(resp, headers, HttpStatus.NO_CONTENT);
        }
    }
}
