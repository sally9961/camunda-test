package ge.psda.camunda.database;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.util.json.JSONArray;
import org.camunda.bpm.engine.impl.util.json.JSONObject;

public class ConnectToHoliDays implements TaskListener{
	public  Connection connObj;
	public  String JDBC_URL = "jdbc:sqlserver://localhost:1433;databaseName=tutorialDb";
    public   String   startDate;
    public   int  period;
    public  String  username="sa";
    public  String  password="Oboba!23";
    public  JSONObject json=new JSONObject();

	public  void getDbConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connObj = DriverManager.getConnection(JDBC_URL,username,password);
			Statement stmtObj = connObj.createStatement();
			ResultSet resObj = stmtObj.executeQuery("SELECT startDate FROM testTable");
			while (resObj.next()) {
				startDate = resObj.getString("startDate");
				  json.put("startDate",startDate);
				  
			}
		} catch(Exception sqlException) {
			sqlException.printStackTrace();
		}
	}


	public void notify(DelegateTask delegateTask) {
		String path = "C:\\Users\\s.tkhilaishvili\\Desktop\\service1\\camunda-test\\src\\main\\webapp\\forms\\data.json";
		try {
			PrintWriter pw = new PrintWriter(path, "UTF-8");
			pw.write(json.toString());
			pw.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
  
}
