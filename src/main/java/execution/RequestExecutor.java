package execution;

import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import input.Request;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import output.Result;

import java.net.URI;
import java.util.Optional;

@Component
@PropertySource("classpath:docker-java.properties")
public class RequestExecutor {

    private static String DOCKER_HOST;
    private static String DOCKER_CONFIG;
    private static String API_VERSION;
    private static String REGISTRY_USERNAME;
    private static String REGISTRY_PASSWORD;
    private static String REGISTRY_EMAIL;
    private static String REGISTRY_URL;

    @Value("${DOCKER_HOST}")
    public void setDockerHost(String DOCKER_HOST){
        RequestExecutor.DOCKER_HOST = DOCKER_HOST;
    }
    @Value("${DOCKER_CONFIG}")
    public void setDockerConfig(String DOCKER_CONFIG){
        RequestExecutor.DOCKER_CONFIG = DOCKER_CONFIG;
    }

    @Value("${REGISTRY_USERNAME}")
    public void setRegistryUsername(String REGISTRY_USERNAME){
        RequestExecutor.REGISTRY_USERNAME = REGISTRY_USERNAME;
    }

    @Value("${REGISTRY_PASSWORD}")
    public void setRegistryPassword(String REGISTRY_PASSWORD){
        RequestExecutor.REGISTRY_PASSWORD = REGISTRY_PASSWORD;
    }

    @Value("${REGISTRY_EMAIL}")
    public void setRegistryEmail(String REGISTRY_EMAIL){
        RequestExecutor.REGISTRY_EMAIL = REGISTRY_EMAIL;
    }

    @Value("${REGISTRY_URL}")
    public void setRegistryURL(String REGISTRY_URL){
        RequestExecutor.REGISTRY_URL = REGISTRY_URL;
    }

    private static DockerClientConfig getDockerClientConfig() {
        return DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost(DOCKER_HOST)
                .withDockerTlsVerify(false)
                .withRegistryUsername(REGISTRY_USERNAME)
                .withRegistryPassword(REGISTRY_PASSWORD)
                .withRegistryEmail(REGISTRY_EMAIL)
                .withRegistryUrl(REGISTRY_URL)
                .build();
    }

    private static DockerHttpClient getDockerHTTPClient() {
        return new ApacheDockerHttpClient.Builder()
                .dockerHost(URI.create(DOCKER_HOST))
                .sslConfig(null) //todo - need this ??
                .build();
    }

    public static Optional<Result> evaluateRequest(Request request) {
        //todo
        return Optional.empty();
    }

}
