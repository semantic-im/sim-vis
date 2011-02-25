package ro.utcluj.larkc.visualization.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PredictionServiceAsync {

	void predict(PredictionData pd, AsyncCallback<PredictionResult> callback);

}
