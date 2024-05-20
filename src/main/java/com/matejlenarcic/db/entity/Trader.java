package com.matejlenarcic.db.entity;

import com.matejlenarcic.domain.taxation.TaxationType;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "trader")
public class Trader extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trader_generator")
    @SequenceGenerator(name = "trader_generator", sequenceName = "trader_seq", allocationSize = 1)
    public Long id;

    @Column(name = "taxation_type")
    public TaxationType taxationType;

    @Column(name = "tax_rate")
    public BigDecimal taxRate;

    @Column(name = "tax_amount")
    public BigDecimal taxAmount;
}
