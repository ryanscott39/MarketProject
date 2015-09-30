package market.dal;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;

public class Feed implements Runnable {
	
	String Symbol;

	public static void getMarket(String s) throws Exception{
		
	    while(true){
			
			StringBuilder url = 
		            new StringBuilder("http://finance.yahoo.com/d/quotes.csv?s=");
	            url.append(s + "+");
	        url.deleteCharAt(url.length()-1);
	        url.append("&f=sba&e=.csv");
	        
	        String theUrl = url.toString();
	        URL obj = new URL(theUrl);
	        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	        con.setRequestMethod("GET");
	        con.setRequestProperty("User-Agent", "Mozilla/5.0");
			@SuppressWarnings("unused")
			int responseCode = con.getResponseCode();
	        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        String inputLine;
	        
	        
	      
	        while ((inputLine = in.readLine()) != null){
	        	
	        	String[] fields = inputLine.split(",");
	        	
	        	System.out.println("Symbol"+fields[0] + ", " + fields[1] + ", " + fields[2]);
	        	
	        }   
		}
	}

	@Override
	public void run() {
		try {
			getMarket(Symbol);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

	



