package com.matejlenarcic.rest.taxation;

import com.matejlenarcic.domain.taxation.TaxationService;
import com.matejlenarcic.rest.taxation.dto.TaxationResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestQuery;

import java.math.BigDecimal;

@Path("/taxations")
public class TaxationResource {
    @Inject
    TaxationService taxationService;

    @Path("general")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TaxationResponse getGeneral(@RestQuery int traderId,
                                       @RestQuery BigDecimal playedAmount,
                                       @RestQuery BigDecimal odd) {
        return TaxationResponse.fromDomain(taxationService.calculateGeneral(traderId, playedAmount, odd));
    }

    @Path("winnings")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TaxationResponse getWinnings(@RestQuery int traderId,
                                        @RestQuery BigDecimal playedAmount,
                                        @RestQuery BigDecimal odd) {
        return TaxationResponse.fromDomain(taxationService.calculateWinnings(traderId, playedAmount, odd));
    }
}
