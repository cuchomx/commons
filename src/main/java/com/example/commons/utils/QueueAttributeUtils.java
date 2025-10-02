package com.example.commons.utils;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.MessageAttributeValue;

import java.util.Map;

import static com.example.commons.constants.AppConstants.CORRELATION_ID;

@Slf4j
public final class QueueAttributeUtils {

    private QueueAttributeUtils() {
    }

    public static String extractCorrelationId(Message m) {
        Map<String, MessageAttributeValue> attributes = m.messageAttributes();
        if( attributes == null ) {
            log.warn("QueueAttributeUtils::extractCorrelationId - received null for attributes");
            return null;
        }
        if( !attributes.containsKey(CORRELATION_ID) ) {
            log.warn("QueueAttributeUtils::extractCorrelationId - attributes does not contain correlationId");
            return null;
        }
        if( attributes.get(CORRELATION_ID) == null ) {
            log.warn("QueueAttributeUtils::extractCorrelationId - received null correlationId value");
            if( !attributes.isEmpty()) {
                log.warn("QueueAttributeUtils::extractCorrelationId - attributes contains other values");
                attributes
                        .forEach((k, v) -> log.warn("QueueAttributeUtils::extractCorrelationId - {}: {}", k, v.stringValue()));
            }
            return null;
        }

        log.info("QueueAttributeUtils::extractCorrelationId - correlationId={}", attributes.get(CORRELATION_ID).stringValue());
        return attributes.get(CORRELATION_ID).stringValue();
    }

    public static void logMessageSummary(Message m) {
        log.info("QueueAttributeUtils.logMessageSummary - Received messageId={}", m.messageId());

        if( m.attributes() == null ) {
            log.info("QueueAttributeUtils.logMessageSummary - No attributes - null");
        }
        if( m.attributes() != null  && m.attributes().isEmpty()) {
            log.info("QueueAttributeUtils.logMessageSummary - No attributes - empty");
        }

        if( m.body() == null ) {
            log.info("QueueAttributeUtils.logMessageSummary - No body - null");
        }

        int bodyLength = m.body() != null ? m.body().length() : 0;
        log.info("QueueAttributeUtils.logMessageSummary - Received messageId={}, bodyLength={}", m.messageId(), bodyLength);

        log.info("QueueAttributeUtils.logMessageSummary - Received attributes={}", m.attributes());
        if( m.messageAttributes() != null  && !m.messageAttributes().isEmpty()) {
            m.messageAttributes()
                    .forEach((k, v) -> log.info("QueueAttributeUtils.logMessageSummary - {}: {}", k, v.stringValue()));
        }

        log.info("QueueAttributeUtils.logMessageSummary - Received body={}", m.body());

        log.info("QueueAttributeUtils.logMessageSummary - Received receiptHandle={}", m.receiptHandle());

        log.info("QueueAttributeUtils.logMessageSummary - End of message");
    }
}
