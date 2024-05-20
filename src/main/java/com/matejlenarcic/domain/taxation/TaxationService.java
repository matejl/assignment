package com.matejlenarcic.domain.taxation;

import com.matejlenarcic.domain.trader.Trader;
import com.matejlenarcic.domain.trader.TraderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigDecimal;

@ApplicationScoped
public class TaxationService {

    @Inject
    TraderRepository traderRepository;

    public TaxationResult calculateGeneral(int traderId, BigDecimal playedAmount, BigDecimal odd) {
        BigDecimal baseAmount = playedAmount.multiply(odd);
        return calculateTaxation(traderId, baseAmount);
    }

    public TaxationResult calculateWinnings(int traderId, BigDecimal playedAmount, BigDecimal odd) {
        BigDecimal baseAmount = playedAmount.multiply(odd).subtract(playedAmount);
        return calculateTaxation(traderId, baseAmount);
    }

    private TaxationResult calculateTaxation(int traderId, BigDecimal baseAmount) {
        Trader trader = traderRepository.getTraderById(traderId);

        BigDecimal taxRate = BigDecimal.ZERO;
        BigDecimal taxAmount = BigDecimal.ZERO;
        BigDecimal possibleReturnAmount;

        switch (trader.taxationType()) {
            case PER_AMOUNT -> {
                taxAmount = trader.taxAmount();
                possibleReturnAmount = baseAmount.subtract(trader.taxAmount());
            }
            case PER_RATE -> {
                taxRate = trader.taxRate();
                possibleReturnAmount = baseAmount.subtract(baseAmount.multiply(trader.taxRate()));
            }
            default -> throw new IllegalStateException("Unexpected taxation type: " + trader.taxationType());
        }

        return new TaxationResult(
            possibleReturnAmount,
            baseAmount,
            possibleReturnAmount,
            taxRate,
            taxAmount
        );
    }
}
