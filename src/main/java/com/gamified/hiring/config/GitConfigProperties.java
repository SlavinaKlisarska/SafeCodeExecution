package com.gamified.hiring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "git")
public class GitConfigProperties {

    private int contentType;
    private String insecureSsl;
    @LocalServerPort
    private String port;

    private String user;
    private String tokenCreateRepo;

    public Map<String, String> getConfigCreateHook() throws UnknownHostException {
        Map<String, String> config = new HashMap<>();
        config.put("url", "http://" + InetAddress.getLocalHost() + ":" + port + "/pushEvent");
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

    public String getInsecureSsl() {
        return insecureSsl;
    }

    public void setInsecureSsl(String insecureSsl) {
        this.insecureSsl = insecureSsl;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getTokenCreateRepo() {
        return tokenCreateRepo;
    }

    public void setTokenCreateRepo(String tokenCreateRepo) {
        this.tokenCreateRepo = tokenCreateRepo;
    }
}
