package main.controller;

import input.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.GitManager;

import java.io.IOException;

@RestController
public class RequestReceiver {

    @Value("${repo.url}")
    private String repoUrl;

    @GetMapping(value = "/repoUrl")//+return token
    public String getRepo(@RequestParam(value = "userName") String userName) {
        try {
            GitManager.getRepo(userName);
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }

        return repoUrl;
    }
}
