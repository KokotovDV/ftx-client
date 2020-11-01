package com.company.ftx.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Dmitry Kokotov
 */
@ConfigurationProperties(prefix = "ftx-client.websocket")
@Component
public class WebSocketProperties {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

