package ru.hogwarts.school.controller;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
public class InfoController {
    private Environment environment;

    public InfoController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/info")
    public String getPort() {
        return "Port is: " + environment.getProperty("server.port");
    }
    @GetMapping("intByStream")
    public Integer intByStream(int limit){
        return Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, Integer::sum);
    }
}
