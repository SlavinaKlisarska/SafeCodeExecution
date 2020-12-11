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

    static Runnable evaluationRunnable =
            () -> {
                Optional<Request> requestOptional = executionQueueHolder.pollIfPossible();
                requestOptional.ifPresent(request -> {
                    resultsQueueHolder.submitResult(RequestExecutor.evaluateRequest(request));
                    executionQueueHolder.finishExecution();
                });
            };
    static Thread evaluationThread = new Thread(evaluationRunnable);

    private static volatile boolean isEvaluationThreadActive;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        runEvaluationThread();
    }

    private static void runEvaluationThread() {
        isEvaluationThreadActive = true;
        while(executionQueueHolder.hasActiveExecutions()) {
            evaluationThread.start();
        }
        isEvaluationThreadActive = false;
    }


    public static void pokeEvaluationThread() {
        if (!isEvaluationThreadActive) {
            runEvaluationThread();
        }
    }

}
