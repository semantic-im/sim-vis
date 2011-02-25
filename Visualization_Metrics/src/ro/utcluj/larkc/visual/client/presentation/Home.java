package ro.utcluj.larkc.visual.client.presentation;

import java.util.ArrayList;
import ro.utcluj.larkc.visual.client.MetricEntry;
import ro.utcluj.larkc.visual.client.OfcgwtCharts;
import ro.utcluj.larkc.visual.client.StockData;
import ro.utcluj.larkc.visual.client.smartgwt_ui.JsonMetrics;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.http.client.URL;
import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Home {
	
	private static final int REFRESH_INTERVAL = 150000; // ms
	private ArrayList<String> stocks = new ArrayList<String>();
	//private StockPriceServiceAsync stockPriceSvc = GWT.create(StockPriceService.class);
	private Label errorMsgLabel = new Label();
	
	//private static final String JSON_URL = "http://localhost/test.php";
	private static final String JSON_URL = "http://larkc.utcluj.ro:8182/test?dbtype=rrd&rrd=TotalSystemUsedMemory&start=1291807946100&end=1291807950000&resolution=1";
	private int jsonRequestId = 0;
	
	OfcgwtCharts ofcgwtChart;
	
	public Home() {
	}
	
	public void loadContent(VerticalPanel mainContaier, Label pageName) {
		pageName.setText("RRD Test Mihai");
		/*
		// Setup timer to refresh list automatically.
		Timer refreshTimer = new Timer() {
			@Override
			public void run() {
				refresh();
			}
		};
		refreshTimer.scheduleRepeating(REFRESH_INTERVAL);
		*/
		
		JsonMetrics yJsonService = new JsonMetrics();
		yJsonService.jsonRun(mainContaier);
		ofcgwtChart = new OfcgwtCharts(mainContaier);
		refresh();
	}

	private void refresh() {
		
		String url = JSON_URL;
	    url = URL.encode(url) ;
	    
	    // Send request to server by replacing RequestBuilder code with a call to a JSNI method.
	    //getJson(jsonRequestId++, url, this);
	    // Send request to server and catch any errors.
	    JsonpRequestBuilder jsonp = new JsonpRequestBuilder();

	    jsonp.requestObject(url, new AsyncCallback<StockData>() {
	    	public void onFailure(Throwable throwable) {
	    		displayError("Error: " + throwable);
	    	}

	    	public void onSuccess(StockData metrics) {
	    		JsArray<MetricEntry> entries = metrics.getMetricEntries();
	    		//load Open Flash Charts charts
	    		ofcgwtChart.loadOFCGWT(entries);
	    	}
	    });
	}

	private final native JsArray<StockData> asArrayOfStockData(String json) /*-{
    	return eval(json);
  	}-*/;
	
	/**
	 * Cast JavaScriptObject as JsArray of StockData.
	 */
	private final native JsArray<StockData> asArrayOfStockData(JavaScriptObject jso) /*-{
	    return jso;
	  }-*/;

	private void displayError(String string) {
		errorMsgLabel.setText("Error: " + string);
	    errorMsgLabel.setVisible(true);
		
	}
		
	/**
	 * Handle the response to the request for stock data from a remote server.
	 */
	public void handleJsonResponse(JavaScriptObject jso) {
		if (jso == null) {
			displayError("Couldn't retrieve JSON. Darn!");
			return;
		}
	}
}
