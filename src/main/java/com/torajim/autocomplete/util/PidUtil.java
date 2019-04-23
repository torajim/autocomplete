package com.torajim.autocomplete.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;

@Slf4j
@Component
public class PidUtil {
    public void savePid(String pidFileName) {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String pidNumber = name.substring(0, name.indexOf("@"));

        File o = new File("/tmp", pidFileName + ".pid");
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(o));
            bw.write(pidNumber);
            bw.flush();
        } catch (IOException e) {
            log.error("PID save error: \r\n{}", e.toString());
        } finally {
            if (bw != null) try {bw.close(); } catch (IOException e) {}
        }
    }
}
