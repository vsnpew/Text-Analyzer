package com.vsnpew.BookAnalyzer.Entities;

import lombok.Data;
import org.apache.kafka.common.protocol.types.Field;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Data
@Table(name = "wrong_symbols")
public class WrongSymbol {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Id
    private Integer id;
    private String symbol;
    private Integer count;

    public WrongSymbol(Integer id, String symbol, Integer count) {
        this.id = id;
        this.symbol = symbol;
        this.count = count;
    }
}