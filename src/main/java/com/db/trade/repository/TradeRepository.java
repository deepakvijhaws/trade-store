package com.db.trade.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.db.trade.model.Trade;

@Repository
public interface TradeRepository extends CrudRepository<Trade, Long> {

}
