package com.matejlenarcic.domain.trader;

import com.matejlenarcic.domain.exception.AppException;
import com.matejlenarcic.domain.taxation.TaxationType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TraderService {
    @Inject
    TraderRepository traderRepository;

    @Transactional
    public Trader create(Trader trader) {
        if (trader.taxationType() == TaxationType.PER_AMOUNT && trader.taxAmount() == null) {
            throw AppException.missingTaxAmount();
        }
        if (trader.taxationType() == TaxationType.PER_RATE && trader.taxRate() == null) {
            throw AppException.missingTaxRate();
        }
        return traderRepository.createNewTrader(trader);
    }
}
