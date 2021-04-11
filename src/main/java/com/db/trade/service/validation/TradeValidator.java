package com.db.trade.service.validation;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.db.trade.model.Trade;
import com.db.trade.repository.TradeRepository;
import com.db.trade.service.exception.LowerTradeVersionException;
import com.db.trade.service.exception.PastMaturityTradeException;

@Component
public class TradeValidator {
	
	@Autowired
	private TradeRepository tradeRepo;
	
	public void checkValidTrade(Trade trade) throws LowerTradeVersionException, PastMaturityTradeException {
		if (tradeVersionLowerThenExisting(trade)) {
			throw new LowerTradeVersionException(trade.getId());
		}
		else if (tradeMaturityDateIsPast(trade)) {
			throw new PastMaturityTradeException(trade.getId());
		}
	}
	
	private boolean tradeVersionLowerThenExisting(Trade trade) throws LowerTradeVersionException {
		Optional<Trade> t = tradeRepo.findById(trade.getId());
		if (t.isPresent()) {
			Trade existingTrade = t.get();
			return trade.getVersion() < existingTrade.getVersion();
		}
		return false;
	}
	
	private boolean tradeMaturityDateIsPast(Trade trade) {
		return trade.getMaturityDate().isBefore(LocalDate.now());
	}


}
