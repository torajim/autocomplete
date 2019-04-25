package com.torajim.autocomplete.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.torajim.autocomplete.service.AutoCompleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AutoCompleteRestController {
    @Autowired
    AutoCompleteService autoCompleteService;

    @GetMapping("/search")
    public ResponseEntity<String> doAutoComplete(@RequestParam("q") final String input) {
        List<String> strings = autoCompleteService.doAutoComplete(input);
        ObjectMapper mapper = new ObjectMapper();
        String resp = "";
        try {
            resp = mapper.writeValueAsString(strings);
        } catch (JsonProcessingException e) {
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "public");
        headers.add("Cache-Control", "max-age=86400");
        headers.add("Content-Length", resp.length() + "");
        return new ResponseEntity<String>(resp, headers, HttpStatus.OK);
    }
}