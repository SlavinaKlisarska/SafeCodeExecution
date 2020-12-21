package com.gamified.hiring.service;

import com.gamified.hiring.config.GitConfigProperties;
import org.kohsuke.github.GHEvent;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

@Service
public class GitManager {

    public static final String WEB_HOOK_PREFIX = "web";

    @Autowired
    private GitConfigProperties gitConfig;

    private GitHub gitHub;


    public URL getRepo(String email) throws IOException {

        URL repoUrl;
        GHUser ghUser = getGhUser(email);
        String repoName = "gamified-hiring/" + ghUser.getLogin();

        try {
            repoUrl = gitHub.getRepository(repoName).getHtmlUrl();
        } catch (IOException e) {
            repoUrl = createGitRepo(gitHub, repoName, ghUser);
        }

        return repoUrl;
    }

    private GHUser getGhUser(String email) throws IOException {
        List<GHUser> users = gitHub.searchUsers().q(email).list().toList();
        return users.get(0);
    }

    private URL createGitRepo(GitHub github, String repoName, GHUser user) throws IOException {
        GHRepository repo = github.createRepository(repoName).private_(true).create();

        repo.createHook(WEB_HOOK_PREFIX, gitConfig.getConfigCreateHook(),
                Collections.singletonList(GHEvent.PUSH), true);

        repo.addCollaborators(user);

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
