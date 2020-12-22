package com.gamified.hiring.controller;

import com.gamified.hiring.service.GitManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class RequestReceiver {

    @Autowired
    private GitManager gitManager;

    @GetMapping(value = "/repo")
    public String getRepo(@RequestParam(value = "email") String email) {
        try {
           return gitManager.getRepo(email).toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "NOT_FOUND";
        }
    }
}
