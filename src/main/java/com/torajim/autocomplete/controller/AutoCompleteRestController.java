package com.torajim.autocomplete.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.torajim.autocomplete.service.AutoCompleteService;
import com.torajim.autocomplete.service.EsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Slf4j
@RestController
public class AutoCompleteRestController {
    @Autowired
    AutoCompleteService autoCompleteService;

    @Autowired
    EsService esService;

    @Autowired
    ListToResponse listToResponse;

    @Value("${autocomplete.es.use}")
    private boolean esUse;

    @GetMapping("/search")
    public ResponseEntity<String> doAutoComplete(@RequestParam("q") final String input) {
        List<String> strings = null;
        if(esUse){
            log.info(input);
            strings = esService.getWordByPrefix(input);
        }else{
            strings = autoCompleteService.doAutoComplete(input);
        }

        return listToResponse.listToResponse(strings);
    }
}