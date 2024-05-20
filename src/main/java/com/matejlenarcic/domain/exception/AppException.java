package com.matejlenarcic.domain.exception;

public class AppException extends RuntimeException {
    private final StatusCode code;

    private AppException(String message, StatusCode code) {
        super(message);
        this.code = code;
    }

    public StatusCode getCode() {
        return code;
    }

    public static AppException missingTaxAmount() {
        return new AppException("tax amount is missing", StatusCode.BAD_REQUEST);
    }

    public static AppException missingTaxRate() {
        return new AppException("tax rate is missing", StatusCode.BAD_REQUEST);
    }

    public static AppException invalidParamValue(String paramName) {
        return new AppException("invalid param value of " + paramName, StatusCode.BAD_REQUEST);
    }
}
