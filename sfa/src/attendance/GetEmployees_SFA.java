package attendance;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ListIterator;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.hhcl.mail.util.conn;

public class GetEmployees_SFA {
	public static void main(String[] args) throws IOException, ParseException, SQLException {
		Connection con = null;
		Statement stmt = null;
		con = conn.getConnection();
		stmt = con.createStatement();
	
		String out="";
		
		//  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	       

		System.out.print("Enter from and to date formate :: yyyy-mm-dd");
        /*String fromdate = br.readLine();
        String todate = br.readLine();//2017-11-30
        */
        String fromdate = "2019-09-22";
        String todate = "2019-10-21";//2017-11-30
        
			  		 
		try{ out = new Scanner(new URL("http://192.168.30.220:8029/WebService1.asmx/getactive?d_date_to="+fromdate+"&d_date_to2="+todate+"").openStream(), "UTF-8").useDelimiter("\\A").next();
		out=out.trim();

		}catch(Exception e){
		 System.out.println("Exception");
		 out="exception";  
		}
		if(out.equals("exception") || out.equals("Employee Does not exist") ){
			System.out.println("Exception::::"+out);
		}else{
		JSONParser parser = new JSONParser();
		JSONArray obj = (JSONArray)parser.parse(out);
		String 	Qry=" ";
	 ListIterator iterator = (obj).listIterator();
    		while (iterator.hasNext()) {
    			JSONObject obj1 = (JSONObject)iterator.next();
    		System.out.println(obj1.get("EmpID"));
    		Qry=" INSERT INTO hclhrm_prod_sfa.sfa_fsmaster (EMPLOYEEID, FSNAME, RESIGNED_DATE, STATUS)"
    				+ "  VALUES ('"+obj1.get("EmpID")+"','"+obj1.get("FSname")+"','"+obj1.get("Dates")+"','"+obj1.get("Status")+"')"
+ " ON DUPLICATE KEY UPDATE  EMPLOYEEID='"+obj1.get("EmpID")+"', FSNAME='"+obj1.get("FSname")+"', RESIGNED_DATE='"+obj1.get("Dates")+"', status='"+obj1.get("Status")+"';"
    				+ "";
    		 stmt.addBatch(Qry);
    	}
			try {
				System.out.println("Execute Batch");
				  stmt.executeBatch();
				  
				  System.out.println("Execute Batch END");
				  
				}catch(Exception e) {
					System.out.println("exceptuioin 1 here in add_t"+ e);
				}
		}
		
		   Date date = new Date();
		     System.out.println("datea::"+ date);
		
	  }
}
