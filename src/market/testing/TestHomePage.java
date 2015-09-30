package market.testing;

import static org.junit.Assert.*;
import market.dal.Feed;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestHomePage {
	private feed feed;
	
	@Before
	public void setUp() throws Exception{
		feed = new Feed();
	}
	
	@After 
	public void tearDown()  throws Exception{
		feed=null;
	}
	
	@Test
	public void testFeed(){
		int result;
		result = feed.mySquare(3);
		assertEquals(9, result);
	}