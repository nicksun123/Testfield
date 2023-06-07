package com.example.springboot;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.Jwts;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;


@RestController
@RequestMapping("/testRest")
public class SpringRestController {
    @Autowired
    private JwtService jwtService;

    @GetMapping("/")
    public String greeting() {
        return "Hello there!";
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.OK)
//    public String welcome(@RequestBody name) {
//        return "Welcome " + name + "!";
//    }

    @RequestMapping(value = "/bye/{name}", method = RequestMethod.DELETE)
//    @DeleteMapping(value = "/bye/{name}")
    @ResponseStatus(HttpStatus.OK)
    public String goodbye(@PathVariable("name") String name) {
        return "Goodbye " + name + "!";
    }

    @PostMapping("/userToken")
    private String createToken() {
        String jws = null;
        try {
            jws = jwtService.generateJwt("c123", null, "k123", null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jws;
    }
}
