package input;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.GitManager;

import java.io.IOException;

@RestController
public class GitEventReceiver {

    @PostMapping(path = "/pushEvent",  consumes = "application/json")
    @ResponseBody
    public boolean acceptNewTaskSubmition(@RequestParam(value = "userName") String userName, @RequestParam(value = "taskName") String task) {

        try {
            GitManager.getRepo(userName);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        Request request = new Request(task);
        request.execute();


        return true;
    }
}
