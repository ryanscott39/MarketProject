package market.testing;


import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import market.rest.RestFeed;

public class TestRestFeed {

	
	private RestFeed RF;
	@Before
	public void setUp() throws Exception{
		RF = new RestFeed();
	}
	
	@After 
	public void tearDown()  throws Exception{
		RF=null;
	}
	
	/**
	 * Testing RestFeed.java
	 * inputting no symbol into parameters in order to see if table is output
	 * @throws IOException 
	 * 
	 */
	@Test
	public void testRestFeedWithUserInput() throws IOException{
		String result;
		result = RF.getData("");
		equals("<table border=1><tr><td>Stock</td><td>Ask price</td><td>Bid price</td></tr>");
	}
	
	
}
