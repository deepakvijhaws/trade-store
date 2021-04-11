package com.db.trade.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.db.trade.model.Trade;
import com.db.trade.service.TradeService;
import com.db.trade.service.exception.LowerTradeVersionException;
import com.db.trade.service.exception.PastMaturityTradeException;
import com.db.trade.service.exception.TradeNotFoundException;

@RestController
@RequestMapping("/trades")
public class TradeController {
	
	@Autowired
	private TradeService tradeService;
	
	@GetMapping
	public List<Trade> trades() {
		return tradeService.getTrades();
	}
	
	@GetMapping("/{id}")
	public Trade tradeById(@PathVariable("id") long id) throws TradeNotFoundException {
		return tradeService.getTradeById(id);
	}
	
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
	public Trade create(@RequestBody Trade trade) throws LowerTradeVersionException, PastMaturityTradeException {
    	Trade savedTrade = tradeService.save(trade);
    	return savedTrade;
	}
    
	@GetMapping("/dummy")
	public String dummy() throws TradeNotFoundException {
		return "Hello World Trade";
	}
}
