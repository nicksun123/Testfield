package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/testRest")
public class SpringRestController {
    @Autowired
    private JwtService jwtService;

    @GetMapping("/")
    public String greeting() {
        return "Hello there!";
    }

    @RequestMapping(value = "/hello/{name}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String welcome(@PathVariable("name") String name) {
        return "Welcome " + name + "!";
    }

    @RequestMapping(value = "/bye/{name}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public String goodbye(@PathVariable("name") String name) {
        return "Goodbye " + name + "!";
    }

    @PostMapping("/userToken")
    private String createToken() {
        try {
            return jwtService.generateJwt("c123", null, "k123", null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
