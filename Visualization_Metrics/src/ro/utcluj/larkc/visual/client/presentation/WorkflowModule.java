package ro.utcluj.larkc.visual.client.presentation;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.XJSONDataSource;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;


public class WorkflowModule {
	
	
	Label pageName = new Label();
	VerticalPanel mainContaier = null;
	public WorkflowModule(){
		pageName.setText("Workflows");
		//RootPanel.get("pageName").add(pageName);
	}
	
	public void loadModule(VerticalPanel mainContaier, Label pageName){
		this.mainContaier = mainContaier;
		
		this.pageName = pageName;
		pageName.setText("Workflows");
		
		
		XJSONDataSource workflowsDS = new XJSONDataSource();
		workflowsDS.setDataURL("http://larkc.utcluj.ro:8182/test?dbtype=mysql&command=getworkflows");
		workflowsDS.setRecordXPath("");
	
		DataSourceField workflowID = new DataSourceField("ID", FieldType.TEXT);
		workflowsDS.addField(workflowID);
		
		DataSourceField workflowName = new DataSourceField("Name", FieldType.TEXT);
		workflowsDS.addField(workflowName);
		
		
		
		XJSONDataSource pluginsDS = new XJSONDataSource();
		pluginsDS.setDataURL("http://larkc.utcluj.ro:8182/test?dbtype=mysql&command=getplugins&tablename=plugins");
		pluginsDS.setRecordXPath("");
		
		DataSourceField pluginName = new DataSourceField("PluginName", FieldType.TEXT);
		pluginsDS.addField(pluginName);
		
		final ListGrid pluginGrid = new ListGrid();
		//grid.setTop(120);
		pluginGrid.setWidth100();
		pluginGrid.setHeight("200px");
		pluginGrid.setTop(160);
		pluginGrid.setAutoFitMaxWidth(600);
		pluginGrid.setWrapCells(true);
		pluginGrid.setFixedRecordHeights(false);
		pluginGrid.setShowAllRecords(true);
		pluginGrid.setDataSource(pluginsDS);
		
		final ListGrid workflowGrid = new ListGrid();
		//grid.setTop(120);
		workflowGrid.setWidth100();
		workflowGrid.setHeight(150);
		workflowGrid.setAutoFitMaxWidth(600);
		workflowGrid.setWrapCells(true);
		workflowGrid.setFixedRecordHeights(false);
		workflowGrid.setShowAllRecords(true);
		workflowGrid.setDataSource(workflowsDS);
		
		workflowGrid.addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
					
				Record record = event.getRecord();
				pluginGrid.fetchData(new Criteria("idworkflow",record.getAttribute("ID"))); 
			}
		});
		
		workflowGrid.fetchData();
		
		//grid.setWidth("500px");
		//grid.setWidth("300px");
		
		VLayout vLayout = new VLayout();
		vLayout.setWidth("800px");
		//vLayout.setHeight("300px");

		vLayout.addChild(workflowGrid);
		vLayout.addChild(pluginGrid);
		
		//RootPanel.get("metricsList").add(vLayout);
		mainContaier.add(vLayout);
	
	}//load Module	
}
