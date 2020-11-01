package com.company.ftx.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.stream.CharacterStreamReadingMessageSource;
import org.springframework.integration.websocket.ClientWebSocketContainer;
import org.springframework.integration.websocket.IntegrationWebSocketContainer;
import org.springframework.integration.websocket.inbound.WebSocketInboundChannelAdapter;
import org.springframework.integration.websocket.outbound.WebSocketOutboundMessageHandler;
import org.springframework.messaging.MessageHandler;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

/**
 * @author Dmitry Kokotov
 */
@Configuration
@EnableIntegration
public class WebSocketIntegrationConfig {

    private final WebSocketProperties webSocketProperties;
    private final StringCommandHandler stringCommandHandler;
    private final WebSocketInboundMessageHandler webSocketInboundMessageHandler;

    public WebSocketIntegrationConfig(WebSocketProperties webSocketProperties,
                                      StringCommandHandler stringCommandHandler,
                                      WebSocketInboundMessageHandler webSocketInboundMessageHandler) {
        this.webSocketProperties = webSocketProperties;
        this.stringCommandHandler = stringCommandHandler;
        this.webSocketInboundMessageHandler = webSocketInboundMessageHandler;
    }

    @Bean
    public IntegrationWebSocketContainer clientWebSocketContainer() {
        return new ClientWebSocketContainer(new StandardWebSocketClient(), webSocketProperties.getUrl());
    }

    @Bean
    public MessageHandler webSocketOutboundMessageHandler() {
        return new WebSocketOutboundMessageHandler(clientWebSocketContainer());
    }

    @Bean
    public IntegrationFlow webSocketOutFlow() {
        return IntegrationFlows
                .from(CharacterStreamReadingMessageSource.stdin(),
                        e -> e.poller(Pollers.fixedDelay(1000)))
                .handle(stringCommandHandler)
                .filter((String payload) -> !payload.isEmpty())
                .handle(webSocketOutboundMessageHandler())
                .get();
    }

    @Bean
    public MessageProducerSupport webSocketInboundChannelAdapter() {
        return new WebSocketInboundChannelAdapter(clientWebSocketContainer());
    }

    @Bean
    public IntegrationFlow webSocketInFlow() {
        return IntegrationFlows
                .from(webSocketInboundChannelAdapter())
                .handle(webSocketInboundMessageHandler)
                .get();
    }

}
