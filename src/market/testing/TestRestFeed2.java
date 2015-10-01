package market.testing;


import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import market.rest.RestFeed;

public class TestRestFeed2 {

	
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
	 * Testing RestFeed2.java
	 * 
	 * @throws IOException 
	 * 
	 */
	@Test
	public void testRestFeedWithUserInput() throws IOException{
		String result;
		result = RF.getData("");
		equals(result);
	}
	
	
}
