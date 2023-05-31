package com.example.springboot;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

@RestController
@RequestMapping("/testRest")
public class SpringRestController {

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
}
