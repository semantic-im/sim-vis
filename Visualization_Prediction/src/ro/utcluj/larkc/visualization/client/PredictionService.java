package ro.utcluj.larkc.visualization.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("prediction")
public interface PredictionService extends RemoteService{
	public PredictionResult predict(PredictionData pd);
	
}
