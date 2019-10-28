package Prasad;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hhcl.mail.util.conn;

public class attendance {

public static void main(String args[]) {
	
	Connection con = null;
	Statement stmt = null;
	con = conn.getConnection();
	ResultSet Res = null;
	StringBuffer employees=new StringBuffer();
	
	employees.append(" SELECT employeeid,date_format(transactiontime,'%H:%i')time,date_format(transactiontime,'%Y-%m-%d') Date ");
	employees.append(" FROM unit_local_db.tbl_reader_log ");
	employees.append(" where employeeid=20314 and date_format(transactiontime,'%Y-%m-%d')='2018-12-24' ");
	
	try {
		
		stmt = con.createStatement();
		Res = stmt.executeQuery(employees.toString());
		
		while(Res.next()){
			
			
		}
		
	/*	long currentTimestamp = System.currentTimeMillis();
		long searchTimestamp = getTheTimestampFromURL();// this also gives me back timestamp in 13 digit (1425506040493)
		long difference = Math.abs(currentTimestamp - searchTimestamp);
		System.out.println(difference);
		if (difference > 10 * 60 * 1000) {
		    System.out.println("timestamp is greater than 5 minutes old");
		}
		
	*/
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
	}
}
