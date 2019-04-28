package com.torajim.autocomplete;

import com.torajim.autocomplete.util.PidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Slf4j
@SpringBootApplication
@EnableAutoConfiguration
@PropertySources({
        @PropertySource("file:config/autocomplete-${spring.profiles.active}.properties")
})
public class Application {
    private static PidUtil pidUtil;

    @Autowired
    public Application(PidUtil pidUtil){
        this.pidUtil = pidUtil;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        pidUtil.savePid("autocomplete");
    }
}