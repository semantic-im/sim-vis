package ro.utcluj.larkc.visualization.client;

import java.io.Serializable;

public class PredictionResult implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	boolean validQuery;
	int scalabilityPercent;
	boolean validWorkflow;
	int minMemory;
	int minExecTime;
	int minCpuUsage;
	int numNodes;
	//temporary result from Ionel
	String predRes;
	
	public String getPredRes() {
		return predRes;
	}
	public void setPredRes(String predRes) {
		this.predRes = predRes;
	}
	public boolean isValidQuery() {
		return validQuery;
	}
	public void setValidQuery(boolean validQuery) {
		this.validQuery = validQuery;
	}
	public int getScalabilityPercent() {
		return scalabilityPercent;
	}
	public void setScalabilityPercent(int scalabilityPercent) {
		this.scalabilityPercent = scalabilityPercent;
	}
	public boolean isValidWorkflow() {
		return validWorkflow;
	}
	public void setValidWorkflow(boolean validWorkflow) {
		this.validWorkflow = validWorkflow;
	}
	public int getMinMemory() {
		return minMemory;
	}
	public void setMinMemory(int minMemory) {
		this.minMemory = minMemory;
	}
	public int getMinExecTime() {
		return minExecTime;
	}
	public void setMinExecTime(int minExecTime) {
		this.minExecTime = minExecTime;
	}
	public int getMinCpuUsage() {
		return minCpuUsage;
	}
	public void setMinCpuUsage(int minCpuUsage) {
		this.minCpuUsage = minCpuUsage;
	}
	public int getNumNodes() {
		return numNodes;
	}
	public void setNumNodes(int numNodes) {
		this.numNodes = numNodes;
	}
	
}
