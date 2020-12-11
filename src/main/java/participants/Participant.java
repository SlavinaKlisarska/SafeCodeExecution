package participants;

import java.util.List;
import java.util.Map;

public class Participant {

    private final String name;
    private final String email;

    private Map<String, Double> solvedTasksWithScore;

    public Participant(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public boolean addTask(){

        return true;
    }
}
