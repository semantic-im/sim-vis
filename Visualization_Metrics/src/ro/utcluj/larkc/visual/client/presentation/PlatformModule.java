package ro.utcluj.larkc.visual.client.presentation;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.XJSONDataSource;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VLayout;

public class PlatformModule {
	
	VerticalPanel mainContaier = null;
	Label pageName = new Label();
	
	public PlatformModule(){
		
		pageName.setText("Platform Metrics");
		//RootPanel.get("pageName").add(pageName);
	}
	
	public void loadModule(VerticalPanel mainContaier, Label pageName){
		this.mainContaier = mainContaier;
		
		this.pageName = pageName;
		pageName.setText("Platform Metrics");
		
		XJSONDataSource metricsDS = new XJSONDataSource();
		metricsDS.setDataURL("http://larkc.utcluj.ro:8182/test?dbtype=mysql&command=getmetrics");
		metricsDS.setRecordXPath("");
	
		DataSourceField queryContext = new DataSourceField("QueryContext", FieldType.TEXT);
		metricsDS.addField(queryContext);
		
		DataSourceField metricName = new DataSourceField("MetricName", FieldType.TEXT);
		metricsDS.addField(metricName);
		
		DataSourceField metricValue = new DataSourceField("MetricValue", FieldType.TEXT);
		metricsDS.addField(metricValue);
	
		final ListGrid metricsGrid = new ListGrid();
		//grid.setTop(120);
		metricsGrid.setWidth100();
		metricsGrid.setHeight(250);
		metricsGrid.setAutoFitMaxWidth(600);
		metricsGrid.setWrapCells(true);
		metricsGrid.setFixedRecordHeights(false);
		metricsGrid.setShowAllRecords(true);
		metricsGrid.setDataSource(metricsDS);	
		metricsGrid.fetchData();
	
		//grid.setWidth("500px");
		//grid.setWidth("300px");
		
		VLayout vLayout = new VLayout();
		vLayout.setWidth("800px");
		//vLayout.setHeight("300px");
			
		vLayout.addChild(metricsGrid);
		
		
		//RootPanel.get("metricsList").add(vLayout);
		mainContaier.add(vLayout);

	
	}//load Module
	
	
}
