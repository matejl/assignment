package com.matejlenarcic.domain.taxation;

import java.math.BigDecimal;

public record TaxationResult(BigDecimal possibleReturnAmount, BigDecimal possibleReturnAmountBeforeTax,
                             BigDecimal possibleReturnAmountAfterTax, BigDecimal taxRate, BigDecimal taxAmount) {
}
