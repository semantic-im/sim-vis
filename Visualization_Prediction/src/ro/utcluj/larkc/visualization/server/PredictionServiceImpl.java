package ro.utcluj.larkc.visualization.server;

import ro.utcluj.larkc.visualization.client.PredictionData;
import ro.utcluj.larkc.visualization.client.PredictionResult;
import ro.utcluj.larkc.visualization.client.PredictionService;
import ro.utcluj.larkc.visualization.server.rf.RelevanceFeedback;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class PredictionServiceImpl extends RemoteServiceServlet implements PredictionService {

	private static final long serialVersionUID = 1L;

	public PredictionResult predict(PredictionData pd) {
		
		// invoke the prediction form Ralu & Ionel
		RelevanceFeedback rf = new RelevanceFeedback();
		rf.initClusteringPrediction();
		String x = rf.predictAttrForQuery(pd.getPredictionQuery());
		
		//return data
		PredictionResult predRes = new PredictionResult();
		predRes.setValidQuery(true);
		predRes.setPredRes(x);
		
		return predRes; 
	}

}
