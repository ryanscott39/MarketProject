package market.pojos;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

@Path("/RestFeed")
public class RestFeed {

	@GET
	@Produces("text/html")
	public String getData(@QueryParam("str") String str) throws IOException{
		StringBuilder url = 
	            new StringBuilder("http://finance.yahoo.com/d/quotes.csv?s=");
            url.append(str + "+");
        url.deleteCharAt(url.length()-1);
        url.append("&f=sab&e=.csv");
        
        String theUrl = url.toString();
        URL obj = new URL(theUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
		@SuppressWarnings("unused")
		int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        String result="";
      
        while ((inputLine = in.readLine()) != null){
        	String[] fields = inputLine.split(",");
        	result += "<table border=1><tr><td>Stock</td><td>Ask price</td><td>Bid price</td></tr>"
        			+ "<tr><td>"+fields[0]+"</td><td>"+fields[1]+"</td><td>"+fields[2]+"</td></tr></table>";
        }  
		return result;
	}
}
