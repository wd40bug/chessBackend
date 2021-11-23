package com.github.wd40bug.chessBackend;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

//https://reqbin.com/req/v0crmky0/rest-api-post-example

@RestController
public class controller {
    String name = "kalem";

    @CrossOrigin
    @GetMapping("/initialize")
    public String initialize(){
        return "Hello, I hate "+name+".\n";
    }

    @CrossOrigin
    @PostMapping("/whoihate")
    public String customizeHate(@RequestBody String name){
        this.name = name;
        return "Successful";
    }
}
