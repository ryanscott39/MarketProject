package market.rest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.logging.*;

@Path("/RestFeed2")
public class RestFeed2 {

	@GET
	@Produces("text/html")
	public String getData() {
		
		String result = null;
		
		try {
		String[] stocks = {"IXIC", "MSFT", "CSCO", "AAPL","GOOG","QCOM","ORCL","EMC", "TXN", "YHOO"};
		StringBuilder url = 
	            new StringBuilder("http://finance.yahoo.com/d/quotes.csv?s=");
		for (String s : stocks)
            url.append(s + "+");
        url.deleteCharAt(url.length()-1);
        url.append("&f=sab&e=.csv");
        
        Logger log = Logger.getLogger(this.getClass());
		log.info("Connection to Yahoo Feed Successful");
        
        String theUrl = url.toString();
        URL obj = new URL(theUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
		@SuppressWarnings("unused")
		int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        
        result="<table border=1><tr><td>StockS</td><td>Ask price</td><td>Bid price</td></tr>";
        String[] fields = null;
        while ((inputLine = in.readLine()) != null){
        	fields = inputLine.split(",");
        	result += "<tr><td>"+fields[0]+"</td><td>"+fields[1]+"</td><td>"+fields[2]+"</td></tr>";
        }  
        result += "</table>";
        Arrays.fill(fields, null);
		
		
		} catch (IOException i) {
			Logger log = Logger.getLogger(this.getClass());
			log.error("ERROR: " + i);
		}
		return result;
	}
}

