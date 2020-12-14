package execution;

import input.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentLinkedQueue;

@Component
@Scope("singleton")
@PropertySource("classpath:application.properties")
public class RequestExecutionQueueHolder {

    @Value("${max.parallel.executions:5}")
    private byte maxParallelExecutions;

    private byte numberOfActiveExecutions;
    private final ConcurrentLinkedQueue<Request> executionQueue = new ConcurrentLinkedQueue<>();

    public byte getNumberOfActiveExecutions() {
        return numberOfActiveExecutions;
    }

    public boolean hasReachedMaxExecutions() {
        return maxParallelExecutions == numberOfActiveExecutions;
    }

    public boolean isQueueEmpty() {
        return executionQueue.isEmpty();
    }

    public Request poll() {
        return executionQueue.poll();
    }

    public void beginExecution() {
        ++numberOfActiveExecutions;
    }

    public void finishExecution() {
        --numberOfActiveExecutions;
    }

    public void addRequest(Request request) {
        executionQueue.add(request);
    }

}
