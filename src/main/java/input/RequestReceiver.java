package input;

import execution.RequestExecutionQueueHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class RequestReceiver {

    @Value( "${repo.url}" )
    private String repoUrl;

    @GetMapping(value="/repoUrl")
    @ResponseBody
    public String getRepo() {
        return repoUrl;
    }

    @PostMapping(path = "/acceptTask")
    @ResponseBody
    public boolean acceptNewTaskSubmition(@RequestParam(value = "userName") String userName, @RequestParam(value = "taskName") String task) {

        try {
            GitListener.getRepo(userName);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        Request request = new Request(task);
        request.execute();


        return true;
    }
}
