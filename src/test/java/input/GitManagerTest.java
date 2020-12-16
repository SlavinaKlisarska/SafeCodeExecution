package input;

import org.junit.Test;
import service.GitManager;

import java.io.IOException;

public class GitManagerTest {

    @Test
    public void testCreateRepoWithWebhook() throws IOException {
        GitManager.getRepo("ganko");
    }

    @Test
    public void testPushCodeNotify() throws IOException {
    }
}
