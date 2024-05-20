package com.matejlenarcic.rest.trader;

import com.matejlenarcic.domain.trader.TraderService;
import com.matejlenarcic.rest.trader.dto.TraderRequest;
import com.matejlenarcic.rest.trader.dto.TraderResponse;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.ResponseStatus;

@Path("/traders")
public class TraderResource {
    @Inject
    TraderService traderService;

    @POST
    @ResponseStatus(201)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TraderResponse create(@Valid TraderRequest traderRequest) {
        return TraderResponse.fromDomain(traderService.create(traderRequest.toDomain()));
    }
}
