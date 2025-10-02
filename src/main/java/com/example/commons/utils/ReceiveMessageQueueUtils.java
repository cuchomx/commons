package com.example.commons.utils;

import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import static com.example.commons.constants.AppConstants.CORRELATION_ID;

public final class ReceiveMessageQueueUtils {

    private static final int MAX_NUMBER_OF_MESSAGES = 10;
    private static final int WAIT_TIME_SECONDS = 20;

    private ReceiveMessageQueueUtils() {
    }

    public static ReceiveMessageRequest buildReceiveRequest( String queueUrl, String attributeNames ) {
        return ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .maxNumberOfMessages(MAX_NUMBER_OF_MESSAGES)
                .waitTimeSeconds(WAIT_TIME_SECONDS)
                .messageAttributeNames(attributeNames== null? "All": attributeNames)
                .build();
    }

    public static ReceiveMessageRequest buildReceiveRequest( String queueUrl ) {
        return ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .maxNumberOfMessages(MAX_NUMBER_OF_MESSAGES)
                .waitTimeSeconds(WAIT_TIME_SECONDS)
                .messageAttributeNames(CORRELATION_ID)
                .build();
    }

    public static ReceiveMessageRequest buildReceiveRequest(
            String queueUrl,
            int maxNumberOfMessages,
            int waitTimeSecond
    ) {
        return ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .maxNumberOfMessages(maxNumberOfMessages)
                .waitTimeSeconds(waitTimeSecond)
                .messageAttributeNames(CORRELATION_ID)
                .build();
    }

}
