package com.gamified.hiring.service;

import org.kohsuke.github.GHEvent;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;

@Service
public class GitManager {

    public static final String WEB_HOOK_PREFIX = "web";
    private GitHub github;

    @Autowired
    private GitConfigProperties gitConfig;

    public GitManager() {
        try {
            github = new GitHubBuilder().withOAuthToken(gitConfig.getRepoToken(), gitConfig.getUser()).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public URL getRepo(String email) throws IOException {

        URL repoUrl;
        String repoName = github + email;

        try {
            repoUrl = github.getRepository(repoName).getHtmlUrl();
        } catch (IOException e) {
            repoUrl = createGitRepo(github, repoName, email);
        }

        return repoUrl;
    }

    private URL createGitRepo(GitHub github, String repoName, String email) throws IOException {
        GHRepository repo = github.createRepository(repoName).private_(true).create();

        repo.createHook(WEB_HOOK_PREFIX, gitConfig.getConfigCreateRepo(),
                Collections.singletonList(GHEvent.PUSH), true);

        repo.addCollaborators(github.getUser(email));

        return repo.getHtmlUrl();
    }

}
