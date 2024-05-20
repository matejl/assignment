package com.matejlenarcic.db.mapper;

import com.matejlenarcic.db.entity.Trader;

public class TraderMapper {
    public static com.matejlenarcic.domain.trader.Trader toDomain(Trader trader) {
        return new com.matejlenarcic.domain.trader.Trader(
            trader.id,
            trader.taxationType,
            trader.taxRate,
            trader.taxAmount);
    }
}
