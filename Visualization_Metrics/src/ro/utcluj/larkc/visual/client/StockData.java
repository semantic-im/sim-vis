package ro.utcluj.larkc.visual.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class StockData extends JavaScriptObject {
	// Overlay types always have protected, zero argument constructors.
	protected StockData() {}                                              

	public final native JsArray<MetricEntry> getMetricEntries() /*-{
  	 return this;
	}-*/;

	

}
