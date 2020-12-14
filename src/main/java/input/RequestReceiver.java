package input;

import execution.RequestExecutionQueueHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestReceiver {

    @Autowired
    private RequestExecutionQueueHolder requestExecutionQueue;

    @PostMapping(path = "/acceptTask", consumes = "application/json", produces = "application/json")
    public int acceptCodeTask(@RequestParam(value = "userEmail") String userEmail, @RequestParam(value = "task") String task, @RequestParam(value = "code") String code) {

        requestExecutionQueue.addRequest(new Request(task, code));

        int result = 0; //get the score the user made

        return result;
    }
}
