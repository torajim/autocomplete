package com.torajim.autocomplete;

import com.torajim.autocomplete.util.DicInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppCmdRunner implements CommandLineRunner {
    @Autowired
    DicInsert dicInsert;

    @Value("${autocomplete.init}")
    boolean esinit;

    @Override
    public void run(String... args) throws Exception {
        if(esinit) {
            dicInsert.insertAll();
        }
    }
}