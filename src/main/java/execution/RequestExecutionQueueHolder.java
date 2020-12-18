package execution;

import input.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Scope("singleton")
@PropertySource("classpath:application.properties")
public class RequestExecutionQueueHolder {

    @Value("${max.parallel.executions:1}")
    private byte maxParallelExecutions;

    private AtomicInteger numberOfActiveExecutions;
    private final ConcurrentLinkedQueue<Request> executionQueue = new ConcurrentLinkedQueue<>();

    public int getNumberOfActiveExecutions() {
        return numberOfActiveExecutions.get();
    }

    public boolean hasReachedMaxExecutions() {
        return maxParallelExecutions == numberOfActiveExecutions.get();
    }

    public boolean isQueueEmpty() {
        return executionQueue.isEmpty();
    }

    public Request poll() {
        return executionQueue.poll();
    }

    public void beginExecution() {
        numberOfActiveExecutions.incrementAndGet();
    }

    public void finishExecution() {
        numberOfActiveExecutions.decrementAndGet();
    }

    public void addRequest(Request request) {
        executionQueue.add(request);
    }

}
