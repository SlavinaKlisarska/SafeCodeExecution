package input;

import db.ParticipantEntity;
import execution.RequestExecutionQueueHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import participants.Participant;

@RestController
public class RequestReceiver {

    @Autowired
    private RequestExecutionQueueHolder requestExecutionQueue;
    private ParticipantEntity participantEntity;

    @PostMapping(path = "/acceptTask", consumes = "application/json", produces = "application/json")
    public int acceptCodeTask(@RequestParam(value = "userEmail") String userEmail, @RequestParam(value = "task") String task, @RequestParam(value = "code") String code) {

        Participant participant = participantEntity.findUserByEmail();
        requestExecutionQueue.addRequest(new Request(participant, task, code));

        int result = 0; //get the score the user made

        return result;
    }
}
