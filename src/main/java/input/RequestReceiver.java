package input;

import execution.RequestExecutionQueueHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestReceiver {

    @Autowired
    private RequestExecutionQueueHolder requestExecutionQueue;

    @Value( "${repo.url}" )
    private String repoUrl;

    @GetMapping(value="/repoUrl")
    @ResponseBody
    public String getRepo() {
        return repoUrl;
    }

    @PostMapping(path = "/acceptTask", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public int acceptCodeTask(@RequestParam(value = "userEmail") String userEmail, @RequestParam(value = "task") String task, @RequestParam(value = "code") String code) {

        requestExecutionQueue.addRequest(new Request(task, code));

        int result = 0; //get the score the user made

        return result;
    }
}
