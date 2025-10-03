package com.example.commons.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public final class ParameterValidationUtils {

    private ParameterValidationUtils() {
    }

    public static boolean isValidCorrelationIdValue(String correlationId) {
        if (correlationId == null ) {
            log.error("ParameterValidationUtils::isValidCorrelationIdValue - Correlation ID is null or blank");
           return false;
        }
        if (correlationId.isBlank()) {
            log.error("ParameterValidationUtils::isValidCorrelationIdValue - Correlation ID is blank");
            return false;
        }
        try {
            UUID.fromString(correlationId);
        } catch (IllegalArgumentException e) {
            log.error("ParameterValidationUtils::isValidCorrelationIdValue - Correlation ID is not a valid UUID");
            return false;
        }

        return true;
    }

    public static boolean isNotValidCorrelationId(String correlationId) {
        return !isValidCorrelationIdValue(correlationId);
    }

    public static boolean isValidParameterRequest(String correlationId, Object request) {
        if (!isValidCorrelationIdValue(correlationId)) {
            log.warn("ParameterValidationUtils::isValidParameterRequest - createProduct - received null correlationId");
            return false;
        }
        if (request == null) {
            log.warn("ParameterValidationUtils::isValidParameterRequest - createProduct - received null request");
            return false;
        }
        return true;
    }

}
