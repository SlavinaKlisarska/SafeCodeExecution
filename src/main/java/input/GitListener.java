package input;

import org.kohsuke.github.GHCreateRepositoryBuilder;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;

public class GitListener {


    public static String getRepo(String userName) throws IOException {

        GitHub github = GitHub.connect();
        try {
            github.getRepository("");
        } catch (IOException e) {
            createGitRepo(github, userName);
        }


        return null;
    }

    private static void createGitRepo(GitHub github, String userName) throws IOException {
//        GHRepository repo = github.createRepository(
//                "gamified-hiring-" + userName,"this is my new repository",
//                "https://www.kohsuke.org/",false);

        GHRepository repo1 = github.createRepository(
                "gamified-hiring-" + userName).private_(true).create();

//        repo.addCollaborators(github.getUser("abayer"),github.getUser("rtyler"));

    }
}
