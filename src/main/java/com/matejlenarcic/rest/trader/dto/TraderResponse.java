package com.matejlenarcic.rest.trader.dto;

import com.matejlenarcic.domain.trader.Trader;

import java.math.BigDecimal;

public record TraderResponse(long id, String taxationType, BigDecimal taxRate, BigDecimal taxAmount) {
    public static TraderResponse fromDomain(Trader trader) {
        return new TraderResponse(trader.id(),
            trader.taxationType().name(),
            trader.taxRate(),
            trader.taxAmount());
    }
}
