package execution;

import input.Request;
import org.springframework.stereotype.Component;
import output.Result;

import java.util.Optional;

@Component
public class RequestExecutor {

    public static Optional<Result> evaluateRequest(Request request) {
        //todo
        return Optional.empty();
    }

}
