package com.matejlenarcic.rest.trader.dto;

import com.matejlenarcic.domain.exception.AppException;
import com.matejlenarcic.domain.taxation.TaxationType;
import com.matejlenarcic.domain.trader.Trader;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record TraderRequest(@NotBlank String taxationType, BigDecimal taxRate, BigDecimal taxAmount) {
    public Trader toDomain() {
        TaxationType domainTaxationType;
        try {
            domainTaxationType = TaxationType.valueOf(taxationType());
        } catch (IllegalArgumentException e) {
            throw AppException.invalidParamValue("taxationType");
        }

        return new Trader(null,
            domainTaxationType,
            taxRate(),
            taxAmount());
    }
}
