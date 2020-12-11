package main;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
@Scope("singleton")
public class ResultsQueue {

    private LinkedList resultsQueue = new LinkedList<>();
}
