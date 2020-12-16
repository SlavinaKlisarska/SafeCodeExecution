package service;

import org.kohsuke.github.GHEvent;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;

public class GitManager {

    private static final HashMap<String, String> config = new HashMap();

    public static URL getRepo(String userName) throws IOException {
        GitHub github = new GitHubBuilder().withOAuthToken("", "SlavinaKlisarska").build();

        URL repoUrl;
//        GitHub github = GitHub.connect();
        String repoName = "gamified-hiring/" + userName;

        try {
            repoUrl = github.getRepository(repoName).getHtmlUrl();
        } catch (IOException e) {
            repoUrl = createGitRepo(github, repoName);
        }
        return repoUrl;
    }

    private static URL createGitRepo(GitHub github, String repoName) throws IOException {
//        GHRepository repo = github.createRepository(
//                "gamified-hiring-" + userName,"this is my new repository",
//                "https://www.kohsuke.org/",false);

        github.isCredentialValid();
        GHRepository repo1 = github.createRepository(repoName).private_(true).create();

        repo1.createHook("web", getConfig(),
                Collections.singletonList(GHEvent.PUSH), true);

//        repo.addCollaborators(github.getUser("abayer"),github.getUser("rtyler"));

        return repo1.getHtmlUrl();
    }

    private static HashMap<String, String> getConfig() {
        if (config.isEmpty()) {
            config.put("url", "http://192.168.1.6:8080/pushEvent");
            config.put("content_type", "json");
            config.put("insecure_ssl", "0");
        }
        return config;
    }
}
