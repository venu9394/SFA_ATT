package attendance;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
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

public class readService {
public static void main(String[] args) throws IOException, ParseException, SQLException {
		Connection con = null;
		Statement stmt = null;
		con = conn.getConnection();
		stmt = con.createStatement();
		String out="";
		String Employeeid="";
		  String employees=" SELECT employeeid FROM hclhrm_prod_sfa.sfa_fsmaster s WHERE STATUS='Active' ";
		ResultSet rs1 = null;
		rs1 = stmt.executeQuery(employees);
		
		System.out.print("Enter from and to date formate :: yyyy-mm-dd");
        /*String fromdate = br.readLine();
        String todate = br.readLine();*/
		   /* String fromdate = "2018-10-27";
	        String todate = "2018-11-26";//2017-11-30
*/	        String fromdate = "2019-01-27";
	        String todate = "2019-02-23";
	        int	resp=0;
        try {
	
			out="";
			 BufferedReader in =null;
			 String inputLine = null;
			  		 System.out.println("::::::::::::::::::::::::"+Employeeid);
			  		  URL url = new URL("http://192.168.30.220:8015/webservice1.asmx/get1?Empcode="+Employeeid+"&ReportDate="+fromdate+"&ReportDate1="+todate+"");
		try{ out = new Scanner(url.openStream(), "UTF-8").useDelimiter("\\A").next();
		out=out.trim();
		System.out.println("out.tpstring:::::: "+out.toString());
		 HttpURLConnection http = (HttpURLConnection)url.openConnection();
	        		System.out.println("resoponse::::: "+ http.getResponseCode());
	        		resp=http.getResponseCode();
		}catch(Exception e){
		 System.out.println("Exception");
		 out="exception";
		}
		if(out.equals("exception") || out.equals("Employee Does not exist") ){
			System.out.println("Exception::::"+out);
		}else{
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(out);
		JSONArray jsonObject1= (JSONArray) obj.get("Table");
		String 	Qry=" ";
		 ListIterator iterator = jsonObject1.listIterator();
		 if(resp!=200){
			 System.out.println("response not given");
			 
			 Qry= " INSERT INTO hclhrm_prod_sfa.tbl_sfa_attendance (EMPLOYEEID, TRANSACTIONDATE, FIN, LOUT, NET, WORK_TYPE, STATUS)"
 					+ "  VALUES ('"+rs1.getString(1)+"','0001-01-01','00:00:00','00:00:00','00:00','NA','NA') ON DUPLICATE KEY UPDATE"
 					+ "  EMPLOYEEID='"+rs1.getString(1)+"', TRANSACTIONDATE='0001-01-01', FIN='00:00:00', LOUT='00:00:00',"
 					+ "  NET='00:00', WORK_TYPE='NA', STATUS='NA'"  ;
 			  stmt.addBatch(Qry);
		 }else{
    		while (iterator.hasNext()) {
    			JSONObject obj1 = (JSONObject)iterator.next();
    			Qry= " INSERT INTO hclhrm_prod_sfa.tbl_sfa_attendance (EMPLOYEEID, TRANSACTIONDATE, FIN, LOUT, NET, WORK_TYPE, STATUS)"
    					+ "  VALUES ('"+obj1.get("EmpCode")+"','"+obj1.get("ReportDate")+"','"+obj1.get("Fin")+"','"+obj1.get("Lout")+"','"+obj1.get("Net")+"','"+obj1.get("WorkType")+"','"+obj1.get("Status")+"') ON DUPLICATE KEY UPDATE"
    					+ "  EMPLOYEEID='"+obj1.get("EmpCode")+"', TRANSACTIONDATE='"+obj1.get("ReportDate")+"', FIN='"+obj1.get("Fin")+"', LOUT='"+obj1.get("Lout")+"',"
    					+ "  NET='"+obj1.get("Net")+"', WORK_TYPE='"+obj1.get("WorkType")+"', STATUS='"+obj1.get("Status")+"'"  ;
    			  stmt.addBatch(Qry);
    		}
		 }
		}
		
		try {
			  stmt.executeBatch();
			}catch(Exception e) {
				System.out.println("exceptuioin 1 here in add_t"+ e);
			}
        }catch(Exception e ){
        	System.out.println("exceptuioin 2 here in add_t"+ e);	
        }
		   Date date = new Date();
		     System.out.println("datea::"+ date);
	  }
}
