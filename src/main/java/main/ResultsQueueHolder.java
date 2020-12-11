package main;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import output.Result;

import java.util.LinkedList;
import java.util.Optional;

@Component
@Scope("singleton")
public class ResultsQueueHolder {

    private LinkedList<Result> resultsQueue = new LinkedList<>();

    public void submitResult(Optional<Result> resultOptional) {
        resultOptional.ifPresent(result -> resultsQueue.add(result));
    }

}
