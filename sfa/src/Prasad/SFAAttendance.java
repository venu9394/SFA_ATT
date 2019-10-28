package Prasad;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SFAAttendance {
	 /**
	  * @param args
	  */
	 public static void main(String[] args) {
	  // TODO Auto-generated method stub
	  List<Date> dates = new ArrayList<Date>();
	  String str_date ="2018-10-27";
	  String end_date ="2018-11-26";
	  DateFormat formatter ; 
	  formatter = new SimpleDateFormat("yyyy-MM-dd");
	  Date startDate = null;
	  try {
	   startDate = (Date)formatter.parse(str_date);
	  } catch (ParseException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  } 
	  Date endDate = null;
	  try {
	   endDate = (Date)formatter.parse(end_date);
	  } catch (ParseException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	  long interval = 24*1000 * 60 * 60; // 1 hour in millis
	  long endTime =endDate.getTime() ; // create your endtime here, possibly using Calendar or Date
	  long curTime = startDate.getTime();
	  while (curTime <= endTime) {
	      dates.add(new Date(curTime));
	      curTime += interval;
	  }
	  StringBuffer Sb=new StringBuffer();
	 // Sb.append(" select k.employeesequenceno AS ID ,k.callname AS NAME,DATE_FORMAT(HH.DATEOFJOIN,'%d-%m-%Y') AS DOJ, BU.name AS BU , DEP.CODE AS DEPT,G.NAME AS COST_CENTER, ");
	  Sb.append(" select a.EMPLOYEEID as ID,k.callname AS NAME,DATE_FORMAT(HH.DATEOFJOIN,'%d-%m-%Y') AS DOJ, BU.name AS BU , DEP.CODE AS DEPT,G.NAME AS COSTCENTER, ");
	  for(int i=0;i<dates.size();i++){
	      Date lDate =(Date)dates.get(i);
	      String ds = formatter.format(lDate);    
	      System.out.println(" Date is ..." + ds);
	      //Sb.append(" IFNULL(max(if(a.dateon='"+ds+"' ,if(a.dateon=b.leaveon, b.leave_type ,concat(a.att_in,' || ',a.att_out))  ,'--' )) ,'A') AS '"+ds+"' " );
	      Sb.append(" IFNULL(MAX(CASE WHEN a.transactiondate='"+ds+"' THEN a.STATUS END), '--') '"+ds+"'");
	      if(i!=dates.size()-1)
	      {
	    	  Sb.append(",");
	      }
	  }
	  
	  Sb.append("  FROM hclhrm_prod_sfa.tbl_sfa_attendance a  ");
	  Sb.append("  left join hclhrm_prod.tbl_employee_primary K on k.employeesequenceno=a.employeeid ");
	  Sb.append("  LEFT JOIN HCLADM_PROD.TBL_BUSINESSUNIT BU ON k.COMPANYID=BU.BUSINESSUNITID ");
	  Sb.append("  LEFT JOIN hclhrm_prod.tbl_employee_professional_details DD ON K.EMPLOYEEID=DD.EMPLOYEEID ");
	  Sb.append("  LEFT JOIN HCLADM_PROD.TBL_DEPARTMENT DEP ON DD.DEPARTMENTID=DEP.DEPARTMENTID ");
	  Sb.append("  LEFT JOIN HCLADM_PROD.TBL_COSTCENTER G ON K.COSTCENTERID=G.COSTCENTERID ");
	  Sb.append("  LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFILE HH ON K.EMPLOYEEID=HH.EMPLOYEEID ");
	  Sb.append("  where a.transactiondate between '"+str_date+"' and '"+end_date+"' GROUP BY a.EMPLOYEEID ");
	  
	  System.out.println(Sb.toString());
	 }
}
