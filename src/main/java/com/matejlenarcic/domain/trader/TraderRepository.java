package com.matejlenarcic.domain.trader;

public interface TraderRepository {
    Trader getTraderById(long id);
    Trader createNewTrader(Trader trader);
}
