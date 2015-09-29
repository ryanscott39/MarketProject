package market.pojos;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import market.dal.DataAccess;

public class MarketFeed {
		
		public static double getStockData() throws Exception{
			// TODO Auto-generated method stub
			
			Double askPrice = 0.0;
			Double askPriceTemp = 0.0;
			String[] fields = {};
				String[] stocks = {"AAPL"};
				StringBuilder url = 
			            new StringBuilder("http://finance.yahoo.com/d/quotes.csv?s=");
		        for (String s : stocks)
		            url.append(s + ",");
		        url.deleteCharAt(url.length()-1);
		        // Properties is for bid and ask
		        url.append("&f=a");
		        
		        String theUrl = url.toString();
		        URL obj = new URL(theUrl);
		        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		        // This is a GET request
		        con.setRequestMethod("GET");
		        con.setRequestProperty("User-Agent", "Mozilla/5.0");
		        int responseCode = con.getResponseCode();
		        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		        String inputLine;
		        
		        while((inputLine = in.readLine()) != null)
		        {
		        	
		        	
  
		            fields = inputLine.split(",");
		            String[] fields2 = inputLine.split(",");

		            askPrice = Double.parseDouble(fields[0].toString());
		            askPriceTemp = Double.parseDouble(fields2[0].toString());
		            
		      
		            	}
		       
		  return askPrice;
			}
		}