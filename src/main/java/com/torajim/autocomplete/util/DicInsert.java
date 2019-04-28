package com.torajim.autocomplete.util;

import com.torajim.autocomplete.service.EsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

@Component
@Slf4j
public class DicInsert {
    @Autowired
    EsService esService;

    public void insertAll() throws IOException {
        Random seed = new Random(System.currentTimeMillis());

        String[] files = {"고유명사.csv", "브랜드.csv", "인물명(한글).csv", "장소.csv", "제품명.csv"};

        int cnt = 1;
        for(String fn : files) {
            log.info("File:" + fn);
            BufferedReader br = new BufferedReader(new InputStreamReader(new ClassPathResource("dic/" + fn).getInputStream(), "UTF-8"));
            String buf = "";
            while ((buf = br.readLine()) != null) {
                String[] dims = buf.split(",");
                esService.postWord(dims[0], seed.nextInt(10));
                log.info("[" + cnt + "]" + dims[0]);
                cnt++;
            }
        }
    }
}