package input;

import execution.RequestExecutionQueueHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * internal representation of a single incoming request
 */
@Component
public class Request {

    private String taskName;
    private String code;

    @Autowired
    private RequestExecutionQueueHolder requestExecutionQueue;

    public Request(String taskName) {
        this.taskName = taskName;
        this.code = code;
    }

    public void execute() {
        //TODO jar file
        requestExecutionQueue.addRequest(new Request(taskName));
    }
}
