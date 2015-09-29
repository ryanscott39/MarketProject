package market.pojos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class StockFeedTable
 */
@WebServlet("/StockFeedTable")
public class StockFeedTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockFeedTable() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        
        
        out.println("<h2>Nasdaq Stock Market</h2>");
		out.println("<table style = 'border: 2px solid black'>");
		out.println("<tr><th style = 'border: 1px solid black'>Symbol</th>");
		out.println("<th style = 'border: 1px solid black'>Ask Price</th>");
		out.println("<th style = 'border: 1px solid black'>Bid Price</th>");

		MarketFeed MF = new MarketFeed();
		double askPrice = 0;
		try {
			askPrice = MF.getStockData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		MarketFeed MF2 = new MarketFeed();
		double askPrice2 =0;
		try {
			askPrice2 = MF2.getStockData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		out.println("<tr><th style = 'border: 1px solid black'>IXIC</th>");
		out.println("<th style = 'border: 1px solid black'/th>");
		out.println("<th style = 'border: 1px solid black'/th>");
		
		out.println("<tr><th style = 'border: 1px solid black'>MSFT</th>");
		out.println("<th style = 'border: 1px solid black'/th>");
		out.println("<th style = 'border: 1px solid black'/th>");
	
		out.println("<tr><th style = 'border: 1px solid black'>CSCO</th>");
		out.println("<th style = 'border: 1px solid black'/th>");
		out.println("<th style = 'border: 1px solid black'/th>");
		
		out.println("<tr><th style = 'border: 1px solid black'>AAPL</th>");
		
		
				out.println("<th style = 'border: 1px solid black'>" + askPrice2 + "</th>");
			
		
		out.println("<th style = 'border: 1px solid black'/th>");
		
		out.println("<tr><th style = 'border: 1px solid black'>QCOM</th>");
		out.println("<th style = 'border: 1px solid black'></th>");
		out.println("<th style = 'border: 1px solid black'/th>");
		
		out.println("<tr><th style = 'border: 1px solid black'>ORCL</th>");
		out.println("<th style = 'border: 1px solid black'/th>");
		out.println("<th style = 'border: 1px solid black'/th>");
		
		out.println("<tr><th style = 'border: 1px solid black'>EMC</th>");
		out.println("<th style = 'border: 1px solid black'/th>");
		out.println("<th style = 'border: 1px solid black'/th>");
	
		out.println("<tr><th style = 'border: 1px solid black'>TXN</th>");
		out.println("<th style = 'border: 1px solid black'/th>");
		out.println("<th style = 'border: 1px solid black'/th>");
		
		out.println("<tr><th style = 'border: 1px solid black'>YHOO</th>");
		out.println("<th style = 'border: 1px solid black'/th>");
		out.println("<th style = 'border: 1px solid black'/th>");
	
		out.println("<tr><th style = 'border: 1px solid black'>INTC</th>");
		out.println("<th style = 'border: 1px solid black'/th>");
		out.println("<th style = 'border: 1px solid black'/th>");
		
	
		
        out.flush();
	}
	}
