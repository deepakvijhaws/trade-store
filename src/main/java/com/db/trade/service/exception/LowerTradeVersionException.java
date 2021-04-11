package com.db.trade.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Trade with lower version provided")
public class LowerTradeVersionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5944883121170768098L;
	

	public LowerTradeVersionException(long id) {
		super("Trade with lower version could not be saved: " + id);
	}
	
	public LowerTradeVersionException(long id, Throwable err) {
		super("Trade with lower version could not be saved: " + id, err);
	}

}
