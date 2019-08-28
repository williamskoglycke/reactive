package se.tre.reactive.infrastructure;

import io.netty.channel.ChannelOption;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

public class ExampleRemoteService {

    public Mono<String> getName() {

        final HttpClient httpClient =
                        HttpClient.create()
                                .compress(true)
                                .tcpConfiguration(client -> client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000));

        final WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();

        final Mono<ClientResponse> exchange = webClient.get().uri("/reactive/owner")
                .accept(MediaType.APPLICATION_JSON)
                .exchange();

        return exchange.flatMap(clientResponse -> clientResponse.bodyToMono(String.class));

    }
}
