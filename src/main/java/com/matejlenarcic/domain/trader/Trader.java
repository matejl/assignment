package com.matejlenarcic.domain.trader;

import com.matejlenarcic.domain.taxation.TaxationType;

import java.math.BigDecimal;

public record Trader(Long id, TaxationType taxationType, BigDecimal taxRate, BigDecimal taxAmount) {
}