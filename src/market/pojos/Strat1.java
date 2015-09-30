package market.pojos;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/Strat1")
public class Strat1 {

	@GET
	@Produces("text/html")
	public void stratagy1(@QueryParam("str") String str) throws IOException, ClassNotFoundException, SQLException{
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/montestdb", "root", "password");
		PreparedStatement in1 = cn.prepareStatement(" drop table Strat1");
		PreparedStatement in2 = cn.prepareStatement("create table Strat1(id int AUTO_INCREMENT PRIMARY KEY, "
				+ "DateCreated timestamp, CompanyName nvarchar(10), AskPrice double, "
				+ "BidPrice double, Position nvarchar(4));");
		in1.executeUpdate();
		in2.executeUpdate();
		
		int count = 0;
		
		double shortTotal = 0;
		double shortAve = 0;
		List<Double> shortList = new ArrayList<Double>();
		
		double longTotal = 0;
		double longAve = 0;
		List<Double> longList = new ArrayList<Double>();
		
		List<Double> diffList = new ArrayList<Double>();
		
		while(true){
			count++;
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
      
            while ((inputLine = in.readLine()) != null){
            	String[] fields = inputLine.split(",");
        	
            	shortList.add(Double.parseDouble(fields[1])); 
            	if(count > 120){
            		shortList.remove(0);
            		shortTotal = 0;
            		for(Double d: shortList){
					shortTotal += d;
            		}
            		shortAve = shortTotal/120;
            	}
			
            	longList.add(Double.parseDouble(fields[1])); 
            	if(count > 300){
            		longList.remove(0);
            		longTotal = 0;
            		for(Double d: shortList){
            			longTotal += d;
            		}
            		longAve = longTotal/300;
            	}
            	
            	diffList.add(longAve-shortAve);
            	for(int i = 0; i < diffList.size(); i++){
            		if(diffList.get(i)>0
            				&& diffList.get(i+1)<0){
            			PreparedStatement st = cn.prepareStatement("insert into Strat1(CompanyName, AskPrice, BidPrice, Position)"
	    						+ "values(?,?,?,?)");
	    					st.setString(1, fields[0]);
	    					st.setString(2, fields[1]);
	    					st.setString(3, fields[2]);
	    					st.setString(4,"Long");
            		}
            		
            		if(diffList.get(i)<0
            				&& diffList.get(i+1)>0){
            			PreparedStatement st = cn.prepareStatement("insert into Strat1(CompanyName, AskPrice, BidPrice, Position)"
	    						+ "values(?,?,?,?)");
	    					st.setString(1, fields[0]);
	    					st.setString(2, fields[1]);
	    					st.setString(3, fields[2]);
	    					st.setString(4,"Short");
            		}
            	}
            	
            	
        	} 
		}
	}
}

