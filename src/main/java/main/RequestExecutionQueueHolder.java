package main;

import input.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
@Scope("singleton")
@PropertySource("classpath:application.properties")
public class RequestExecutionQueueHolder {

    @Value("${max.parallel.executions:5}")
    private byte maxParallelExecutions;

    private byte numberOfActiveExecutions;
    private ConcurrentLinkedQueue<Request> executionQueue = new ConcurrentLinkedQueue<>();

    public boolean hasActiveExecutions() {
        return numberOfActiveExecutions > 0;
    }

    private boolean hasReachedMaxExecutions() {
        return maxParallelExecutions == numberOfActiveExecutions;
    }

    @Nullable
    public Optional<Request> pollIfPossible() {
        if (hasReachedMaxExecutions()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(executionQueue.poll());
        }
    }

}
