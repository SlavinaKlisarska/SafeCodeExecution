package main;

import input.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentLinkedQueue;

@Component
@Scope("singleton")
@PropertySource("classpath:application.properties")
public class RequestExecutionQueue {

    @Value("${max.parallel.executions:5}")
    private byte MAX_PARALLEL_EXECUTIONS;

    private byte numberOfActiveExecutions;
    private ConcurrentLinkedQueue<Request> executionQueue = new ConcurrentLinkedQueue<>();

}
