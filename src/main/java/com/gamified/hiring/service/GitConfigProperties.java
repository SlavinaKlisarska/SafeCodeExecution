package com.gamified.hiring.service;

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
//
//    private String pushEventUrl;
//    private int contentType;
//    private String insecureSsl;
//    @LocalServerPort
//    private String port;

    private String user;
//    private String tokenCreateRepo;

    public Map<String, String> getConfigCreateRepo() throws UnknownHostException {
        Map<String, String> config = new HashMap<>();
        config.put("url", "http://" + InetAddress.getLocalHost() + ":" + "8080" + "/pushEvent");
        config.put("content_type", "json");
        config.put("insecure_ssl", "0");

        return config;
    }

    public String getRepoToken() {
        return "";
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
//
//    public String getPushEventUrl() {
//        return "";
//    }
//
//    public void setPushEventUrl(String pushEventUrl) {
//        this.pushEventUrl = pushEventUrl;
//    }
//
//    public int getContentType() {
//        return contentType;
//    }
//
//    public void setContentType(int contentType) {
//        this.contentType = contentType;
//    }
//
//    public String getInsecureSsl() {
//        return insecureSsl;
//    }
//
//    public void setInsecureSsl(String insecureSsl) {
//        this.insecureSsl = insecureSsl;
//    }
//
//    public String getPort() {
//        return port;
//    }
//
//    public void setPort(String port) {
//        this.port = port;
//    }
//
//    public String getTokenCreateRepo() {
//        return tokenCreateRepo;
//    }
//
//    public void setTokenCreateRepo(String tokenCreateRepo) {
//        this.tokenCreateRepo = tokenCreateRepo;
//    }
}
