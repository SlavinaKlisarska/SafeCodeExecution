package main;

import execution.RequestExecutionQueueHolder;
import execution.RequestExecutor;
import input.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"main","execution"})
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

    static Request currentRequest;

    final static Runnable codeExecutionRunnable =
            () -> {
                executionQueueHolder.beginExecution();
                resultsQueueHolder.submitResult(RequestExecutor.evaluateRequest(currentRequest));
                executionQueueHolder.finishExecution();
            };

    final static Logger logger = LoggerFactory.getLogger(Application.class);

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        //todo - start another thread that processes the results queue

        while (true) {
            if (!executionQueueHolder.isQueueEmpty()) {
                if (executionQueueHolder.hasReachedMaxExecutions()) {
                    logger.info("New request cannot be processed at the moment because max_parallel_execution capacity has been reached.");
                } else {
                    logger.info("New request processing has begun. Previous number of active executions was " + executionQueueHolder.getNumberOfActiveExecutions());
                    currentRequest = executionQueueHolder.poll();
                    new Thread(codeExecutionRunnable).start();
                }
            } else {
                logger.info("Request queue is empty.");
            }
        }
    }

}
