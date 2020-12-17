package main.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.GitManager;

import java.io.IOException;
import java.util.Map;

@RestController
public class GitEventReceiver {

    @PostMapping(path = "/pushEvent")
    public boolean acceptNewTaskSubmition(@RequestBody Map<String, Object> payload) {

        System.out.println("received push event" + payload);
//        String repo="";
//        try {
//            GitManager.pullCodeChanges(repo);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //reponame
        //getcode

//        Request request = new Request(task);
//        request.execute();


        return true;
    }
}
