package com.matejlenarcic.rest.taxation.dto;

import com.matejlenarcic.domain.taxation.TaxationResult;

import java.math.BigDecimal;

public record TaxationResponse(BigDecimal possibleReturnAmount, BigDecimal possibleReturnAmountBeforeTax,
                               BigDecimal possibleReturnAmountAfterTax, BigDecimal taxRate, BigDecimal taxAmount) {
    public static TaxationResponse fromDomain(TaxationResult taxationResult) {
        return new TaxationResponse(taxationResult.possibleReturnAmount(),
            taxationResult.possibleReturnAmountBeforeTax(),
            taxationResult.possibleReturnAmountAfterTax(),
            taxationResult.taxRate(),
            taxationResult.taxAmount());
    }
}
