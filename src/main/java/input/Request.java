package input;

import org.springframework.stereotype.Component;
import participants.Participant;

/**
 * internal representation of a single incoming request
 */
@Component
public class Request {

    private Participant user;
    private String taskName;
    private String code;

    public Request(Participant user, String taskName, String code) {
        this.user = user;
        this.taskName = taskName;
        this.code = code;
    }
}
