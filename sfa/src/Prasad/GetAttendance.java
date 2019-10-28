package Prasad;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetAttendance {
	


 

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
	  
	   
	  Sb.append(" select k.employeesequenceno AS ID ,k.callname AS NAME,DATE_FORMAT(HH.DATEOFJOIN,'%d-%m-%Y') AS DOJ, BU.name AS BU , DEP.CODE AS DEPT,G.NAME AS COST_CENTER, ");
	 
	  
	  
	  
	  for(int i=0;i<dates.size();i++){
	      Date lDate =(Date)dates.get(i);
	      String ds = formatter.format(lDate);    
	      System.out.println(" Date is ..." + ds);
	      
	      //Sb.append(" IFNULL(max(if(a.dateon='"+ds+"' ,if(a.dateon=b.leaveon, b.leave_type ,concat(a.att_in,' || ',a.att_out))  ,'--' )) ,'A') AS '"+ds+"' " );
	      //Hyd All
	      Sb.append(" IFNULL(MAX(CASE WHEN a.DATEON='"+ds+"' THEN IF(a.DAY_STATUS='P' , concat(a.ATT_IN,'||',a.ATT_OUT) , if(a.DAY_STATUS='A' AND a.DAYTYPE!='HL',if(dayname(a.DATEON)='Sunday','WOFF',concat(b.leave_type,' / ',b.MANAGER_STATUS)), if(a.DAYTYPE='HL',a.DAYTYPE ,a.DAY_STATUS))) END),'A') AS '"+ds+"' ");
	   //below Assam open for assam
	      
	      //  Sb.append(" IFNULL(MAX(CASE WHEN a.DATEON='"+ds+"' THEN IF(a.DAY_STATUS='P' , concat(a.ATT_IN,'||',a.ATT_OUT,'||',if(xc.req_type='AR','AR',ifnull(b.leave_type,'--'))) , if(a.DAY_STATUS='A' AND a.DAYTYPE!='HL',if(dayname(a.DATEON)='Sunday','WOFF',concat(b.leave_type,' / ',b.MANAGER_STATUS)), if(a.DAYTYPE='HL',a.DAYTYPE ,a.DAY_STATUS))) END),'A') AS '"+ds+"' ");

	       
	      
	     //b.leave_type 
	      if(i!=dates.size()-1)
	      {
	    	  Sb.append(",");
	      }
	      
	  }
	  
	  
	  Sb.append("  from hclhrm_prod.tbl_employee_primary K ");
	  Sb.append("  left join test_mum.tbl_att_leave_in_out_status_report a on a.employeeid=k.employeesequenceno ");
	  // Assam
	  /*Sb.append(" left join hclhrm_prod_others.tbl_emp_attn_req xc on xc.employeeid=k.employeesequenceno ");
	  Sb.append(" and a.dateon=xc.REQ_DATE and xc.req_type='AR' ");*/
	  //Assam
	  Sb.append("  left join hclhrm_prod_others.tbl_emp_leave_report B ON b.employeeid=a.employeeid AND a.dateon=b.leaveon and b.MANAGER_STATUS in ('A','P') ");
	  Sb.append("  LEFT JOIN HCLADM_PROD.TBL_BUSINESSUNIT BU ON k.COMPANYID=BU.BUSINESSUNITID ");
	  Sb.append("  LEFT JOIN hclhrm_prod.tbl_employee_professional_details DD ON K.EMPLOYEEID=DD.EMPLOYEEID ");
	  Sb.append("  LEFT JOIN HCLADM_PROD.TBL_DEPARTMENT DEP ON DD.DEPARTMENTID=DEP.DEPARTMENTID ");
	  Sb.append("  LEFT JOIN HCLADM_PROD.TBL_COSTCENTER G ON K.COSTCENTERID=G.COSTCENTERID ");
	  Sb.append("  LEFT JOIN HCLHRM_PROD.TBL_EMPLOYEE_PROFILE HH ON K.EMPLOYEEID=HH.EMPLOYEEID ");
	  Sb.append("  where K.companyid in(10,11,14,17,18,19,20,21,22,24,25,26,27,28,29) ");
	  Sb.append("  and a.dateon between '"+str_date+"' and '"+end_date+"' ");
	  Sb.append("  and k.status in(1001,1092)  group by a.employeeid  ");
	  
	  System.out.println(Sb.toString());

	 }

	
}
