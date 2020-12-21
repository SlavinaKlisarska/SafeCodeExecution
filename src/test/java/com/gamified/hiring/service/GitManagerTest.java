package com.gamified.hiring.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest()
@EnableConfigurationProperties()
public class GitManagerTest {

    @Autowired
    private GitManager gitManager;

    @Test
    public void testCreateRepoWithWebhook() throws IOException {
        gitManager.getRepo("stefan.georgiev91@gmail.com");
    }

    @Test
    public void testPushCodeNotify() throws IOException {
        gitManager.getRepo("ganko");
    }
}
