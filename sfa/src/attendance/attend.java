package attendance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.Date;

import org.json.simple.parser.ParseException;


public class attend {
	public static void main(String[] args) throws IOException, ParseException, SQLException {
		// BufferedReader reader =null; String inputLine = null;
		 BufferedReader in =null; String inputLine = null;
		  	try{
		  URL oracle = new URL("http://192.168.30.220:8015/webservice1.asmx/get1?Empcode=101179&ReportDate=2017-06-01&ReportDate1=2017-06-20");
	        URLConnection yc = oracle.openConnection();
	         in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		}catch(Exception e ){
			inputLine="exception";
		}
	     if(inputLine!= "exception"){
	        while ((inputLine = in.readLine()) != null) 
	            System.out.println(inputLine);
	        in.close();
	     }else{
	    	 System.out.println("exception");
	     }
	     
	     Date date = new Date();
	     System.out.println("datea::"+ date);
	/*	HttpURLConnection conn = null;
		
		try{
			try{
            //Declare the connection to weather api url
            URL url = new URL("http://192.168.30.220:8015/webseffrvice1.asmx/get1?Empcode=101179&ReportDate=2017-06-01&ReportDate1=2017-06-04");  
            conn = (HttpURLConnection)url.openConnection();  
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
      //      conn.setRequestProperty("apikey",apiKey);
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                              + conn.getResponseCode());
            }
            
		}catch(Exception e ){
			inputLine="exception";
		}
           
            //Read the content from the defined connection
            //Using IO Stream with Buffer raise highly the efficiency of IO
                 reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String output = null;  
            
            if(inputLine!="exception"){
            
            while ((output = reader.readLine()) != null)  
            	System.out.println(output);
            
            }else{
            	System.out.println(output);
            }
              //  strBuf.append(output);  
        }catch(MalformedURLException e) {  
            e.printStackTrace();   
        }catch(IOException e){  
            e.printStackTrace();   
        }

		*/
	  }
}
