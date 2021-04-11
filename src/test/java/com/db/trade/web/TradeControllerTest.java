package com.db.trade.web;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.db.trade.application.TradeStoreApplication;
import com.db.trade.model.Trade;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TradeStoreApplication.class)
@ActiveProfiles("test")
public class TradeControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    public void given_application_running_when_dummy_api_invoked_then_response_status_200() throws Exception {
    	ResponseEntity<String> response = restTemplate.getForEntity("/trades/dummy", String.class);
    	System.out.println("Response : " + response);
    	assertEquals(HttpStatus.OK, response.getStatusCode());
    	assertEquals("Hello World Trade", response.getBody());
    }
    
    @Test
    public void given_trade_store_when_trades_post_api_invoked_then_new_trade_saved() throws Exception {
    	Trade t1 = new Trade(1, "CP-1", "B1", LocalDate.of(2021, Month.APRIL, 15), LocalDate.of(2012, Month.APRIL, 15), false);
    	ResponseEntity<Trade> response = restTemplate.postForEntity("/trades", t1, Trade.class);
    	assertEquals(HttpStatus.CREATED, response.getStatusCode());
    	verifySavedTradeWithExpectedTrade(t1, response.getBody().getId());
    }
    
	private void verifySavedTradeWithExpectedTrade(Trade expectedTrade, Long savedTradeId) {
		Trade savedTrade = restTemplate.getForEntity("/trades/" + savedTradeId, Trade.class).getBody();
		
		// setting the id in expected trade for equality since it is auto-generated field
		expectedTrade.setId(savedTrade.getId());
		assertEquals(expectedTrade, savedTrade);
	}
    
    @Test
    public void given_existing_trade_when_lower_version_trade_comes_then_exception() throws Exception {
    	Trade t1 = new Trade(2, "CP-1", "B1", LocalDate.of(2021, Month.APRIL, 15), LocalDate.of(2012, Month.APRIL, 15), false);
    	ResponseEntity<Trade> response = restTemplate.postForEntity("/trades", t1, Trade.class);
    	assertEquals(HttpStatus.CREATED, response.getStatusCode());
    	long savedTradeId = response.getBody().getId();

    	Trade lowerVersionTrade = new Trade(1, "CP-1", "B1", LocalDate.of(2021, Month.APRIL, 15), LocalDate.of(2012, Month.APRIL, 15), false);
    	lowerVersionTrade.setId(savedTradeId);
    	ResponseEntity<Trade> badResponse = restTemplate.postForEntity("/trades", lowerVersionTrade, Trade.class);
    	assertEquals(HttpStatus.BAD_REQUEST, badResponse.getStatusCode());
    }
    
    @Test
    public void given_trade_store_when_trade_with_past_maturity_date_comes_then_exception() throws Exception {
    	Trade t1 = new Trade(2, "CP-1", "B1", LocalDate.of(2012, Month.APRIL, 15), LocalDate.of(2012, Month.APRIL, 15), false);
    	ResponseEntity<Trade> response = restTemplate.postForEntity("/trades", t1, Trade.class);
    	assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


}
