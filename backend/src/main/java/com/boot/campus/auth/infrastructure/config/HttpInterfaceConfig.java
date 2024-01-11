package com.boot.campus.auth.infrastructure.config;

import com.boot.campus.auth.infrastructure.github.GitHubApiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class HttpInterfaceConfig {
    
    @Bean
    public GitHubApiClient gitHubApiClient() {
        return createFactory(GitHubApiClient.class);
    }
    
    private <T> T createFactory(final Class<T> clazz) {
        final RestClient restClient = RestClient.create();
        final RestClientAdapter adapter = RestClientAdapter.create(restClient);
        final HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter)
                                                                       .build();
        return factory.createClient(clazz);
    }
}
