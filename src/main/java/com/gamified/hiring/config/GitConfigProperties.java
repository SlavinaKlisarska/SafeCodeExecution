package com.gamified.hiring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "git")
public class GitConfigProperties {

    private int contentType;
    private String webhookUrl;
    private String user;
    private String tokenCreateRepo;

    public Map<String, String> getConfigCreateHook() {
        Map<String, String> config = new HashMap<>();
        config.put("url", webhookUrl);
        config.put("content_type", "json");
        config.put("insecure_ssl", "0");

        return config;
    }

    public String getRepoToken() {
        return tokenCreateRepo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public String getTokenCreateRepo() {
        return tokenCreateRepo;
    }

    public void setTokenCreateRepo(String tokenCreateRepo) {
        this.tokenCreateRepo = tokenCreateRepo;
    }

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

}
