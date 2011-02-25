package ro.utcluj.larkc.visual.client;

import com.google.gwt.core.client.JavaScriptObject;


public class MetricEntry extends JavaScriptObject {
	
	protected MetricEntry() {}

	// JSNI methods to get stock data.

	public final native String getTimeStamp() /*-{ return this.ts + ''}-*/; 
	public final native double getValue() /*-{ return this.value; }-*/;
	//public final native double getChange() /*-{ return this.value; }-*/;

	// Non-JSNI method to return change percentage.                       
	//public final double getChangePercent() {
	//	return 100.0;
	//}



}
