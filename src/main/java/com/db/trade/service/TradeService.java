package com.db.trade.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.trade.model.Trade;
import com.db.trade.repository.TradeRepository;
import com.db.trade.service.exception.LowerTradeVersionException;
import com.db.trade.service.exception.PastMaturityTradeException;
import com.db.trade.service.exception.TradeNotFoundException;
import com.db.trade.service.validation.TradeValidator;

@Service
public class TradeService {
	
	@Autowired
	private TradeRepository tradeRepo;
	
	@Autowired
	private TradeValidator validator;
	
	public List<Trade> getTrades() {
		List<Trade> trades = new ArrayList<>();
		tradeRepo.findAll().forEach(t -> trades.add(t));
		return trades;
	}
	
	public Trade getTradeById(long id) throws TradeNotFoundException {
		Optional<Trade> t = tradeRepo.findById(id);
		if (!t.isPresent()) {
			throw new TradeNotFoundException(id);
		}
		return t.get();
	}

	public Trade save(Trade trade) throws LowerTradeVersionException, PastMaturityTradeException {
		this.validator.checkValidTrade(trade);
		return tradeRepo.save(trade);
	}




}
