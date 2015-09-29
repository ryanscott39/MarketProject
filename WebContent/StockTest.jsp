<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1", import="java.io.BufferedReader, java.io.InputStreamReader, java.net.HttpURLConnection, java.net.URL";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 
while(true){
	String[] stocks = {"AA", "BAC", "VXX", "SUNE", "AAPL","RAD","YHOO","FCX", "QQQ", "XIV" };
	StringBuilder url = 
            new StringBuilder("http://finance.yahoo.com/d/quotes.csv?s=");
    for (String s : stocks)
        url.append(s + "+");
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
   
    while ((inputLine = in.readLine()) != null)
    {
    	String[] fields = inputLine.split(","); 
    	%>
    	
    	<table>
    	<tr>
    	<td>Apple</td><td><% if(fields[0].equals("\"AAPL\"")){
    		out.println(fields[1]);} %></td><td> <% if(fields[0].equals("\"AAPL\"")){
    		out.println(fields[2]);} %></td>
    	</tr>
    	</table>
    	
    	<%     	 
    	}
    } %>

</body>
</html>