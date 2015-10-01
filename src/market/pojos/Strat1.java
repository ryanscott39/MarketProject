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
		
		System.out.println("Strategy 1 Activated");
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/ad3db", "root", "");
		/*PreparedStatement in1 = cn.prepareStatement("drop table Trades");
		PreparedStatement in2 = cn.prepareStatement("create table Trades(id int AUTO_INCREMENT PRIMARY KEY, "
				+ "DateCreated timestamp, CompanyName nvarchar(10), AskPrice double, "
				+ "BidPrice double, Position nvarchar(6), size int, ProfitPercent double);");
		in1.executeUpdate();
        in2.executeUpdate();*/
		
		int count = 0;
		int Transactions = 0;
		int shares = 0;
		double shortTotal = 0;
		double shortAve = 0;
		double longTotal = 0;
		double longAve = 0;
		double InitialTransaction = 0;
		double TrueInitialTransaction = 0;
		double MoneyTotal = 0;
		List<Double> shortList = new ArrayList<Double>();
		List<Double> longList = new ArrayList<Double>();
		List<Double> diffList = new ArrayList<Double>();
	
		outterloop:
		while(true){
			count++;
			StringBuilder url = 
	            new StringBuilder("http://finance.yahoo.com/d/quotes.csv?s=");
            url.append(str + "+");
            url.deleteCharAt(url.length()-1);
            url.append("&f=saba5b6&e=.csv");
        
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
            	if(count > 60){
            		shortList.remove(0);
            		shortTotal = 0;
            		for(Double d: shortList){
					shortTotal += d;
            		}
            		shortAve = shortTotal/60;
            	}
			
            	longList.add(Double.parseDouble(fields[1])); 
            	if(count > 200){
            		longList.remove(0);
            		longTotal = 0;
            		for(Double d: longList){
            			longTotal += d;
            		}
            		longAve = longTotal/200;
            	}
            	
            	if(longAve != 0){
            	diffList.add(longAve-shortAve);
            	}
            	
            	if(diffList.size()==2){
            		
            		if(diffList.get(0)>0
            				&& diffList.get(1)<0){
            			PreparedStatement st = cn.prepareStatement("insert into Trades(CompanyName, AskPrice,"
            					+ " BidPrice, Position, size, ProfitPercent)"
	    						+ "values(?,?,?,?,?,?)");
	    					st.setString(1, fields[0]);
	    					st.setString(2, fields[1]);
	    					st.setString(3, fields[2]);
	    					st.setString(4,"Long");
	    					st.setString(5, fields[4]);
	    					st.setString(6,"0.0");
	    					st.executeUpdate();
	    					Transactions++;
	    					shares += Integer.parseInt(fields[4]);
	    					MoneyTotal -= Double.parseDouble(fields[2])*Integer.parseInt(fields[4]); 
	    					InitialTransaction = -MoneyTotal;
	    					if(Transactions == 1){
	    						TrueInitialTransaction = -MoneyTotal;
	    					}
            			}
            		
            		if(shares != 0){
            			if(diffList.get(0)<0
            					&& diffList.get(1)>0){
            				PreparedStatement st = cn.prepareStatement("insert into Trades(CompanyName, AskPrice, "
            						+ "BidPrice, Position, size, ProfitPercent)"
            						+ "values(?,?,?,?,?,?)");
	    						st.setString(1, fields[0]);
	    						st.setString(2, fields[1]);
	    						st.setString(3, fields[2]);
	    						st.setString(4,"Short");
	    						st.setString(5, fields[3]);
	    						
	    						Transactions++;
	    						
	    						if(shares >= Integer.parseInt(fields[3])){
	    						shares -= Integer.parseInt(fields[3]);
	    						MoneyTotal += Double.parseDouble(fields[1])*Integer.parseInt(fields[3]);
	    						}else{
	    							MoneyTotal += Double.parseDouble(fields[1])*shares;
	    							shares = 0;
	    						}
	    						
	    						st.setString(6, Double.toString(100*(MoneyTotal/TrueInitialTransaction)));
	    						st.executeUpdate();
	    						
	    						if(Transactions == 1){
	    							//InitialTransaction = MoneyTotal;
	    							//TrueInitialTransaction = MoneyTotal;
	    						}
            				}
            			}
            		diffList.remove(0);
            		}
            		if(shares == 0 && InitialTransaction != 0 && Transactions%2 == 0){
            			if( Math.sqrt(MoneyTotal*MoneyTotal)>=(0.01*InitialTransaction)){
            				System.out.println("Strategy 1 exiting");
            				System.out.println("Profit: "+100*(MoneyTotal/TrueInitialTransaction)+"%");
            				System.out.println("Number of transactions: "+Transactions);
            				break outterloop;
            			}
            		}
            	}
        	} 
	}
}


