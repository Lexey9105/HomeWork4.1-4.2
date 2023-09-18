package ru.hogwarts.school.controller;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
