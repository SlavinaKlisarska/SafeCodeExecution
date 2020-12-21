package com.gamified.hiring.service;

import com.gamified.hiring.config.GitConfigProperties;
import org.kohsuke.github.GHEvent;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;

@Service
public class GitManager {

    public static final String WEB_HOOK_PREFIX = "web";

    @Autowired
    private GitConfigProperties gitConfig;

    private GitHub gitHub;


    public URL getRepo(String email) throws IOException {

        URL repoUrl;
        String repoName = gitHub + email;

        try {
            repoUrl = gitHub.getRepository(repoName).getHtmlUrl();
        } catch (IOException e) {
            repoUrl = createGitRepo(gitHub, repoName, email);
        }

        return repoUrl;
    }

    private URL createGitRepo(GitHub github, String repoName, String email) throws IOException {
        GHRepository repo = github.createRepository(repoName).private_(true).create();

        repo.createHook(WEB_HOOK_PREFIX, gitConfig.getConfigCreateHook(),
                Collections.singletonList(GHEvent.PUSH), true);

        repo.addCollaborators(github.getUser(email));

        return repo.getHtmlUrl();
    }

    @PostConstruct
    private void createGitHubInstance() {
        if (gitHub == null) {
            try {
                gitHub = new GitHubBuilder().withOAuthToken(gitConfig.getRepoToken(), gitConfig.getUser()).build();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
