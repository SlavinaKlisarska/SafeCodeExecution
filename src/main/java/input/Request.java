package input;

import org.springframework.stereotype.Component;

/**
 * internal representation of a single incoming request
 */
@Component
public class Request {

    private String taskName;
    private String code;

    public Request(String taskName, String code) {
        this.taskName = taskName;
        this.code = code;
    }
}
