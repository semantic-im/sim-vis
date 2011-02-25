import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import jdbc.DBConnector;
import org.rrd4j.core.FetchData;
import org.rrd4j.core.FetchRequest;
import org.json.*;
import org.rrd4j.ConsolFun;
import org.rrd4j.core.RrdDb;
public class Rrd4jJSONServer extends ServerResource {

	private HashMap<String, RrdDb> rrdDbs = new HashMap<String, RrdDb>();
	private static String baseFolder; 
	
	public static DBConnector dbConnector;
	
	
	
	//run with java Rrd4jJSONServer <folder_where_rrds_are_located>
	public static void main(String[] args) throws Exception {  
		baseFolder = args[0];
		//Create the HTTP server and listen on port 8182
		try{
			Server jsonServer = new Server( Protocol.HTTP, 8182, Rrd4jJSONServer.class);
			jsonServer.start();
			System.out.println("JSON Server started on address:" + jsonServer.getAddress());
			
		}
		catch(Exception e){
			System.out.println("There is a problem to start Rrd4jJSONServer");
			System.err.println("Error: There is a problem to start Rrd4jJSONServer");
		}
		
		dbConnector = new DBConnector();
		
		
		
	}
	
	private RrdDb openRrdDb(String name) throws IOException{
		RrdDb ret = rrdDbs.get(name);
		if(ret==null) {
			ret = new RrdDb(baseFolder+File.separatorChar+name+".rrd");
		}
		return ret;
	}
	
	/*
	 * Get Workflows and Associated Plugins 
	 */
	public String getWorkflows(){
		System.out.println("GetJSONForMySQL");
		ResultSet rsWorkflows = null;
		Statement stmtWorkflows = null;
		
		
		
		String query = "select * from workflows;";
		
		try {
			
			stmtWorkflows = dbConnector.conn.createStatement();
			rsWorkflows = stmtWorkflows.executeQuery(query);
			
			JSONArray jsonArray = new JSONArray();
			while (rsWorkflows.next()) 
			{
				JSONObject row = new JSONObject();
				String Name = rsWorkflows.getString("Name");
				String id = rsWorkflows.getString("idWorkflow");
				
				try {
					row.put("Name", Name);
					row.put("ID", id);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println("Name " + Name);
				jsonArray.put(row);
			}
			return jsonArray.toString();

		} catch (SQLException e ) {
			return "null";
		} finally {
			try {
				stmtWorkflows.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
				
		
	}
	
	public String getPlugins(String idWorkflow){
		
		ResultSet rsPlugins = null;
		Statement stmtPlugins = null;
		
		
		
		String query = "select * from plugins where idPlugin in " + 
				"(select Plugins_idPlugin from workflows_plugins where " + 
				"Workflows_idWorkflow = " + idWorkflow + ");";
		
		try {
			stmtPlugins = dbConnector.conn.createStatement();
			rsPlugins = stmtPlugins.executeQuery(query);
			JSONArray jsonArray = new JSONArray();
			while (rsPlugins.next()) 
			{
				JSONObject row = new JSONObject();
				String Name = rsPlugins.getString("Name");
				
				
				try {
					row.put("PluginName", Name);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println("Name " + Name);
				jsonArray.put(row);
			}
			return jsonArray.toString();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			
		}
		
		
		//return null;
	}

	private String getMetrics() {
		ResultSet rs = null;
		Statement stmt = null;
		
		
		
		String query = "select * from queries, queries_metrics, metrics " +
					   "where queries.idQuery=queries_metrics.Queries_idQuery and "+
					   "metrics.idMetric=queries_metrics.Metrics_idMetric;";
		
		try {
			stmt = dbConnector.conn.createStatement();
			rs = stmt.executeQuery(query);
			JSONArray jsonArray = new JSONArray();
			while (rs.next()) 
			{
				JSONObject row = new JSONObject();
				String queryContext = rs.getString("Context");
				String metricName = rs.getString("Name");
				String metricValue = rs.getString("Value");
				//String queryContext = rs.getString("Name");
				
				
				try {
					row.put("QueryContext", queryContext);
					row.put("MetricName", metricName);
					row.put("MetricValue", metricValue);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println("Name " + Name);
				jsonArray.put(row);
			}
			return jsonArray.toString();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			
		}
		
		
		//return null;
	}

	
	//example: http://localhost:8182/test?rrd=TotalSystemUsedMemory&start=1291807946100&end=1291807950000&resolution=100
	//start, end, and resolution are in milliseconds since the epoch

	@Get  
	public String getJSONForMySql() throws SQLException {
		
		/**
		 * Chose between RRD or MySQL
		 */
		String databaseType = "mysql";
		String command = "select";
		@SuppressWarnings("unused")
		String tableName = "metrics";
		String callbackFunction = null;


		try {
			databaseType = getQuery().getValues("dbtype");
			command = getQuery().getValues("command");
			tableName = getQuery().getValues("tablename");
			callbackFunction = getQuery().getValues("callback");			

		} catch(Exception e){
			System.err.println (e.getMessage());
			e.printStackTrace();
		}

		if(databaseType.equalsIgnoreCase("mysql")){

			if(command.equalsIgnoreCase("getworkflows"))
				return callbackFunction + "(" + getWorkflows() + ");";
			else if(command.equalsIgnoreCase("getplugins"))
			{
				String idWorkflow = tableName = getQuery().getValues("idworkflow");
				return callbackFunction + "(" + getPlugins(idWorkflow) + ");";
			}	
			else if(command.equalsIgnoreCase("getmetrics"))
			{
				
				return callbackFunction + "(" + getMetrics() + ");";
			}
			

		}//MySQL parsing
		else if(databaseType.equalsIgnoreCase("rrd")){


			System.out.println("GetJSONForRRD");
			long startTime = 0;
			long endTime = 0;
			long resolution = 0;
			String rrdName = null;

			try {
				startTime = Long.parseLong(getQuery().getValues("start"));
				endTime = Long.parseLong(getQuery().getValues("end"));
				resolution = Long.parseLong(getQuery().getValues("resolution"));
				rrdName = getQuery().getValues("rrd");

			} catch (Exception e) {
				return null;
			}

			RrdDb rrdDb = null;
			try {
				rrdDb = openRrdDb(rrdName);
			} catch(Exception e) {
				return "null";
			}

			FetchRequest fetchRequest = rrdDb.createFetchRequest(ConsolFun.AVERAGE, startTime, endTime, resolution);
			FetchData fetchData = null;
			try {
				fetchData = fetchRequest.fetchData();
			} catch(IOException e) {
				return null;
			}

			String[] columns = fetchData.getDsNames();
			int rowCount = fetchData.getRowCount();
			long[] timeStamps = fetchData.getTimestamps();
			double[][] values = fetchData.getValues();
			try {
				JSONArray series = new JSONArray();
				for(int i = 0;i<rowCount;i++) {
					JSONObject row = new JSONObject();
					row.put("ts", timeStamps[i]);
					for(int j=0;j<columns.length;j++) {
						row.put(columns[j], values[j][i]);
					}
					series.put(row);
				}

				return callbackFunction + "(" + series.toString() + ");";
				//return series.toString();

			} catch (JSONException e) {
				return "null";
			}

		}//RRD parsing
		
		return "null";

	}

	
}
