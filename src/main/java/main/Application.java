package main;

import execution.RequestExecutionQueueHolder;
import execution.RequestExecutor;
import input.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class Application {

    static RequestExecutionQueueHolder executionQueueHolder;
    static ResultsQueueHolder resultsQueueHolder;

    @Autowired
    public void setRequestExecutionQueueHolder(RequestExecutionQueueHolder requestExecutionQueueHolder){
        Application.executionQueueHolder = requestExecutionQueueHolder;
    }
    @Autowired
    public void setResultsQueueHolder(ResultsQueueHolder resultsQueueHolder){
        Application.resultsQueueHolder = resultsQueueHolder;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);


        Runnable consumerRunnable =
                () -> {
                    Optional<Request> requestOptional = executionQueueHolder.pollIfPossible();
                    requestOptional.ifPresent(request -> {
                        resultsQueueHolder.submitResult(RequestExecutor.evaluateRequest(request));
                        executionQueueHolder.finishExecution();
                    });
                };

        Thread consumerThread = new Thread(consumerRunnable);
        consumerThread.run();

    }

}
