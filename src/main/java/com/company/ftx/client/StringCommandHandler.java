package com.company.ftx.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

/**
 * @author Dmitry Kokotov
 */
@Component
public class StringCommandHandler implements GenericHandler<String> {

    @Override
    public String handle(String command, MessageHeaders headers) {

        String[] parts = command.split(" ", 3);
        String operation = parts.length > 0 ? parts[0] : "";
        String channel = parts.length > 1 ? parts[1] : "";
        String market = parts.length > 2 ? parts[2] : "";

        StringBuilder stringBuilder = new StringBuilder();
        if ("subscribe".equals(operation)) {
            if (!channel.isEmpty() && !market.isEmpty()){
                stringBuilder.append(createJsonFtxMessage(operation, channel, market));
            }
        } else if ("test".equals(operation)) {
            stringBuilder.append(createJsonFtxMessage("subscribe", "ticker", "BTC/USD"));
        }
        return stringBuilder.toString();
    }

    private String createJsonFtxMessage(String operation, String channel, String market) {
        String result = "";
        FtxRequest ftxRequest = new FtxRequest(operation, channel, market);
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.writeValueAsString(ftxRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

}


