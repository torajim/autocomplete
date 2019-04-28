package com.torajim.autocomplete.controller;

import com.torajim.autocomplete.service.EsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class EsRestController {
    @Autowired
    EsService esService;

    @Autowired
    ListToResponse listToResponse;

    @PostMapping("/es/post")
    public ResponseEntity<String> postToEs(@RequestParam("word") final String word, @RequestParam("score") final long score) {
        esService.postWord(word, score);
        return new ResponseEntity<>("Posted[word:" + word + ", score:" + score + "]", HttpStatus.OK);
    }

    @GetMapping("/es/get")
    public ResponseEntity<String> GetFromEs(@RequestParam("prefix") final String prefix) {
        List<String> strings = esService.getWordByPrefix(prefix);
        return listToResponse.listToResponse(strings);
    }
}
