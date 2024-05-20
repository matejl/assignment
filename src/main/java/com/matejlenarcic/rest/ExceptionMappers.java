package com.matejlenarcic.rest;

import com.matejlenarcic.domain.exception.AppException;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class ExceptionMappers {
    public record ErrorResult(String errorMessage) {
    }
    @ServerExceptionMapper
    public RestResponse<ErrorResult> mapException(AppException appException) {
        return RestResponse.status(Response.Status.fromStatusCode(appException.getCode().getCode()),
            new ErrorResult(appException.getMessage()));
    }
}
