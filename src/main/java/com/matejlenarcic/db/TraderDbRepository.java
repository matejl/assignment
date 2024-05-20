package com.matejlenarcic.db;

import com.matejlenarcic.db.mapper.TraderMapper;
import com.matejlenarcic.domain.trader.Trader;
import com.matejlenarcic.domain.trader.TraderRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TraderDbRepository implements TraderRepository {
    @Override
    public Trader getTraderById(long id) {
        return TraderMapper.toDomain(com.matejlenarcic.db.entity.Trader.findById(id));
    }

    @Override
    public Trader createNewTrader(Trader trader) {
        com.matejlenarcic.db.entity.Trader traderEntity = new com.matejlenarcic.db.entity.Trader();
        traderEntity.taxRate = trader.taxRate();
        traderEntity.taxAmount = trader.taxAmount();
        traderEntity.taxationType = trader.taxationType();
        traderEntity.persistAndFlush();

        return TraderMapper.toDomain(traderEntity);
    }
}
