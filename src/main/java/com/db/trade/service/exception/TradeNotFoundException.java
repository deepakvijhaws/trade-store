package com.db.trade.service.exception;

public class TradeNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5705408262175962412L;
	
    public TradeNotFoundException() {
        super();
    }

	public TradeNotFoundException(long id) {
		super("Trade with Id not found in store: " + id);
	}
	
	public TradeNotFoundException(long id, Throwable err) {
		super("Trade with Id not found in store: " + id, err);
	}


}
