package com.db.trade.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.db.trade.model.Trade;
import com.db.trade.repository.TradeRepository;

@Component
public class TradeExpiryJob {
	
	@Autowired
	private TradeRepository tradeRepo;

	/**
	 * This job will run at mid-night everyday
	 */
	@Scheduled(cron = "0 0 0 * * *")
	public void run() {
		System.out.println("Running Trade Expiry Job");
		tradeRepo.findAll().forEach(t -> updateExpiryFlag(t));
	}

	private void updateExpiryFlag(Trade t) {
		System.out.println("Checking trade for expiry: " + t.getId());
		if (t.getMaturityDate().isBefore(LocalDate.now())) {
			t.setExpired(true);
			tradeRepo.save(t);
			System.out.println("Setting trade as expired: " + t.getId());
		}
	}
}
