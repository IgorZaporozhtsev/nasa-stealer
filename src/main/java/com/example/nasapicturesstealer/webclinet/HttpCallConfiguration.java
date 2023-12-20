package com.example.nasapicturesstealer.webclinet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class HttpCallConfiguration {

    private static final String PATH = "https://api.nasa.gov/mars-photos/api/v1";

    @Bean
    public NasaClient kitchenClient() {
        final int size = 16 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();

        WebClient client = WebClient.builder()
                .exchangeStrategies(strategies)
                .baseUrl(PATH).build();
        WebClientAdapter exchangeAdapter = WebClientAdapter.create(client);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(exchangeAdapter).build();
        return factory.createClient(NasaClient.class);
    }
}
