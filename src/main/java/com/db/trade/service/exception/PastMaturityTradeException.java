package com.db.trade.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Trade with past maturity date provided")
public class PastMaturityTradeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5882752928377322797L;


	public PastMaturityTradeException(long id) {
		super("Trade with past maturity date could not be saved: " + id);
	}
	
	public PastMaturityTradeException(long id, Throwable err) {
		super("Trade with past maturity date could not be saved: " + id, err);
	}
}
