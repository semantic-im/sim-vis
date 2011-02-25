package ro.utcluj.larkc.visual.client.smartgwt_ui;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.XJSONDataSource;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VLayout;

public class JsonMetrics {

	public JsonMetrics() {      
    }
    
    public void jsonRun(VerticalPanel mainContaier){
    	
    	XJSONDataSource yahooDS = new XJSONDataSource();
        metricsDS.setDataURL("http://larkc.utcluj.ro:8182/test?dbtype=rrd&rrd=TotalSystemUsedMemory&start=1291807946100&end=1291807950000&resolution=1");
        metricsDS.setRecordXPath("");
              
        DataSourceField metricName = new DataSourceField("ts", FieldType.TEXT);
        DataSourceField metricValue = new DataSourceField("value", FieldType.TEXT);
            
        metricsDS.addField(metricName);
        metricsDS.addField(metricValue);
        
        final ListGrid grid = new ListGrid();
        grid.setWidth100();
        grid.setHeight(300);
        grid.setWrapCells(true);
        grid.setFixedRecordHeights(false);
        grid.setShowAllRecords(true);
        grid.setDataSource(metricsDS);

        grid.fetchData();
          
        grid.setWidth("98%");
        grid.setAutoFitMaxWidth(600);
        VLayout vLayout = new VLayout();
       
        vLayout.addChild(grid);
        vLayout.setWidth("700px");
        vLayout.setMargin(5);
        mainContaier.add(vLayout);
    }

}
