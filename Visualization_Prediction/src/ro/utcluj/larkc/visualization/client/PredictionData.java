package ro.utcluj.larkc.visualization.client;

import java.io.Serializable;

public class PredictionData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// the query that is used for prediction
	String predictionQuery;
	// the work-flow description used for prediction
	String predictionWorkflow;
	// the prediction Type: 
	int predictionType;
	// use constraints: true if constraints are used, false otherwise
	boolean useConstraints;
	//Minimum Memory, Minimum Execution Time, Minimum CPU Usage, Nb of Nodes < 10
	int constraints;
	
	public String getPredictionQuery() {
		return predictionQuery;
	}
	public void setPredictionQuery(String predictionQuery) {
		this.predictionQuery = predictionQuery;
	}
	public String getPredictionWorkflow() {
		return predictionWorkflow;
	}
	public void setPredictionWorkflow(String predictionWorkflow) {
		this.predictionWorkflow = predictionWorkflow;
	}
	public int getPredictionType() {
		return predictionType;
	}
	public void setPredictionType(int predictionType) {
		this.predictionType = predictionType;
	}
	public boolean isUseConstraints() {
		return useConstraints;
	}
	public void setUseConstraints(boolean useConstraints) {
		this.useConstraints = useConstraints;
	}
	public int getConstraints() {
		return constraints;
	}
	public void setConstraints(int constraints) {
		this.constraints = constraints;
	}
	
	
}
