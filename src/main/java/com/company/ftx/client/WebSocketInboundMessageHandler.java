package com.company.ftx.client;

import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Dmitry Kokotov
 */
@Component
public class WebSocketInboundMessageHandler implements GenericHandler<String> {
    @Override
    public Object handle(String payload, MessageHeaders headers) {
        Pattern pattern = Pattern.compile("(?=\"bid\": )(.*)(?=, \"ask\":)|(?= \"ask\": )(.*)(?=, \"bidSize\":)");
        Matcher matcher = pattern.matcher(payload);
        StringBuilder stringBuilder = new StringBuilder();
        while(matcher.find()) {
            stringBuilder.append(payload, matcher.start(), matcher.end());
        }
        if (stringBuilder.length() != 0) {
            System.out.println(stringBuilder.toString()
                    .replace("\"","")
                    .replace(": ", ":"));
        } else {
            System.out.println(payload);
        }
        return null;
    }
}
