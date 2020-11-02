package com.company.ftx.client;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

/**
 * @author Dmitry Kokotov
 */
@Component
public class WebSocketInboundMessageHandler implements GenericHandler<String> {
    @Override
    public Object handle(String payload, MessageHeaders headers) {
        StringBuilder stringBuilder = new StringBuilder();
        JsonObject jsonObject = JsonParser.parseString(payload).getAsJsonObject();
        if (jsonObject.isJsonObject() && jsonObject.has("data")) {
            JsonObject data = jsonObject.get("data").getAsJsonObject();
            if (data.has("bid") && data.has("ask")) {
                stringBuilder.append("bid:");
                stringBuilder.append(data.get("bid").getAsString());
                stringBuilder.append(", ask:");
                stringBuilder.append(data.get("ask").getAsString());
                System.out.println(stringBuilder.toString());
            }
        }
        if (stringBuilder.length() == 0) {
            System.out.println(payload);
        }
        return null;
    }
}
