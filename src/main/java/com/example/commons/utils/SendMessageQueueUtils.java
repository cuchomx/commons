package com.example.commons.utils;

import software.amazon.awssdk.services.sqs.model.MessageAttributeValue;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.util.HashMap;

import static com.example.commons.constants.AppConstants.CORRELATION_ID;

public class SendMessageQueueUtils {

    private SendMessageQueueUtils() {
    }

    public static SendMessageRequest buildSendMessageRequest(
            String queueUrl,
            String correlationId,
            String message
    ) {
        var attributes = new HashMap<String, MessageAttributeValue>();
        attributes.put(CORRELATION_ID, MessageAttributeValue.builder().dataType("String").stringValue(correlationId).build());

        var builder = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(message)
                .messageAttributes(attributes);

        return builder.build();
    }
}
