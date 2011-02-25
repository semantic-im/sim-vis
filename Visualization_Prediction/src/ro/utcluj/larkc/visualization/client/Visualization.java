package ro.utcluj.larkc.visualization.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.rednels.ofcgwt.client.model.ChartData;
import com.rednels.ofcgwt.client.model.elements.PieChart;
import com.rednels.ofcgwt.client.model.elements.PieChart.PieBounceAnimation;
import com.rednels.ofcgwt.client.ChartWidget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Visualization implements EntryPoint {
	
	//query text area
	private TextArea queryTextArea = new TextArea();
	private VerticalPanel queryVP = new VerticalPanel();
	//work flow test area
	private TextArea workflowTextArea = new TextArea();
	private VerticalPanel workflowVP = new VerticalPanel();
	
	//query radio buttons
	private RadioButton queryRB0 = new RadioButton("QueryRadioButtons","Scalability");
	private RadioButton queryRB1 = new RadioButton("QueryRadioButtons","Bottleneck");
	private RadioButton queryRB2 = new RadioButton("QueryRadioButtons","Workflow");
	private VerticalPanel queryRBVP = new VerticalPanel();
	//check boxes
	private CheckBox queryCb0 = new CheckBox("User Constraints");
	private CheckBox queryCb1 = new CheckBox("Minimum Memory");
	private CheckBox queryCb2 = new CheckBox("Minimum Execution Time");
	private CheckBox queryCb3 = new CheckBox("Minimum CPU Usage");
	private CheckBox queryCb4 = new CheckBox("Number of Nodes < 10");
	
	private VerticalPanel queryCbVP = new VerticalPanel(); // vertical panel for the first check box
	private VerticalPanel queryCbVP1 = new VerticalPanel(); // vertical panel for the other check boxes - shifted to the left
		
	private Button sendButton = new Button("Submit");
	private Button clearButton = new Button("Clear");
	private HorizontalPanel buttonsHP = new HorizontalPanel();
	
	//data gathered from the interface will be encapsulated into one object
	private PredictionData predData = new PredictionData();
	private PredictionServiceAsync predServ = GWT.create(PredictionService.class);
		
	/**
	 *	OFC GWT pie chart test 
	 */
	
	private SimplePanel smp = new SimplePanel();
	private ChartWidget cw = new ChartWidget();
	private ChartData pcd = new ChartData("Metrics Pie Chart Test", "font-size: 14px; font-family: Verdana; text-align: center;");
	private PieChart pie = new PieChart();
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
				
		pie.setAlpha(0.5f);
		pie.setNoLabels(true);
		pie.setTooltip("#label# #val#<br>#percent#");
		pie.setAnimateOnShow(true);
		pie.setGradientFill(true);
		pie.setNoLabels(true);
		pie.setColours("#ff0000","#00ff00","#0000ff","#ff9900","#ff00ff");
		PieBounceAnimation pba = new PieBounceAnimation(10);
		pie.setAnimation(pba);
		
		for(int i = 0; i<= 3; i++) {
			pie.addSlice(25, "test "+i);
		}
		
		pcd.addElements(pie);
		pcd.setBackgroundColour("#FFFFFF");
		cw.setSize("400", "400");
		cw.setChartData(pcd);
		cw.setTitle("Pie caca Chart");
		smp.add(cw);
				
		// set the text area for the query
		queryTextArea.setCharacterWidth(60);
		queryTextArea.setVisibleLines(10);
		queryTextArea.setTitle("Please input a SPARQL Query for the LarKC platform");
		// set the text area for the work flow
		workflowTextArea.setCharacterWidth(60);
		workflowTextArea.setVisibleLines(10);
		workflowTextArea.setTitle("Please input a Workflow description for the LarKC platform");
		
		//Add data to the vertical panels 
	    //text area for query and work-flow
		queryVP.add(queryTextArea);
		workflowVP.add(workflowTextArea);
				
		//radio buttons
	    queryRBVP.add(queryRB0);
	    queryRBVP.add(queryRB1);
	    queryRBVP.add(queryRB2);
	    queryRB0.setValue(true);
	    // check boxes
	    queryCbVP.add(queryCb0);
	    queryCb0.setEnabled(true);
	    queryCb0.setValue(false);
	    queryCb1.setEnabled(false);
    	queryCb2.setEnabled(false);
    	queryCb3.setEnabled(false);
    	queryCb4.setEnabled(false);
	    
	    queryCbVP1.add(queryCb1);
	    queryCbVP1.add(queryCb2);
	    queryCbVP1.add(queryCb3);
	    queryCbVP1.add(queryCb4);
	    
	    //Buttons
	    buttonsHP.add(sendButton);
	    buttonsHP.add(clearButton);
	    sendButton.setStyleName("button");
	    clearButton.setStyleName("button");
	    
	    // Add elements to the RootPanel 
		RootPanel.get("query_prediction").add(queryVP);
		RootPanel.get("workflow_prediction").add(workflowVP);
		RootPanel.get("prediction_rbs").add(queryRBVP);
		RootPanel.get("prediction_cbs").add(queryCbVP);
		RootPanel.get("prediction_check").add(queryCbVP1);
		RootPanel.get("prediction_buttons").add(buttonsHP);
		RootPanel.get("pie_chart").add(smp);
		
		//set some elements for the query text area
		queryTextArea.setText("PREFIX foaf: <http://xmlns.com/foaf/0.1/> SELECT ?name1 WHERE { ?person1 foaf:knows ?person2 . ?person1 foaf:name  ?name1 . ?person2 foaf:name  \"Ionel Giosan\" .   } ORDER BY ?name1");
		queryTextArea.selectAll();
		queryTextArea.setFocus(true);
		//set some elements for the work flow text area
		workflowTextArea.setText("Please input a Workflow description for the LarKC platform.");
		workflowTextArea.selectAll();
		
		
		//handler for the main check box: queryCb0
		//it activates or de-activates the other check boxes
		queryCb0.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		        boolean checked = ((CheckBox) event.getSource()).getValue();
		        if(checked) {
		        	queryCb1.setEnabled(true);
		        	queryCb2.setEnabled(true);
		        	queryCb3.setEnabled(true);
		        	queryCb4.setEnabled(true);
		        }
		        else {
		        	queryCb1.setEnabled(false);
		        	queryCb2.setEnabled(false);
		        	queryCb3.setEnabled(false);
		        	queryCb4.setEnabled(false);
		        }
		      }
		    });

		//handler for the clear button
		clearButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
			queryTextArea.setText("PREFIX foaf: <http://xmlns.com/foaf/0.1/> SELECT ?name1 WHERE { ?person1 foaf:knows ?person2 . ?person1 foaf:name  ?name1 . ?person2 foaf:name  \"Ionel Giosan\" .   } ORDER BY ?name1");
			queryTextArea.selectAll();
			queryTextArea.setFocus(true);
			workflowTextArea.setText("Please input a Workflow description for the LarKC platform.");
			workflowTextArea.selectAll();
			RootPanel.get("prediction_response").clear();
			
			}
		});
				
		sendButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				predData.setPredictionQuery(queryTextArea.getText());
				predData.setPredictionWorkflow(workflowTextArea.getText());
				if(queryRB0.getValue()) {
					predData.setPredictionType(0);
				}
				else if(queryRB1.getValue()) {
					predData.setPredictionType(1);
				}
				
				else if(queryRB2.getValue()) {
					predData.setPredictionType(2);
				}
				
				predData.setUseConstraints(queryCb0.getValue());
				// TO DO set the constraints 
				int constr=0;
				predData.setConstraints(constr);	
				// invoke the remote procedure that performs prediction
				invokePrediction();
			}
		});
	}
	private void invokePrediction() {
		if(predServ == null) {
			predServ = GWT.create(PredictionService.class);
			
		}
		 // Set up the call-back object.
	    AsyncCallback<PredictionResult> callback = new AsyncCallback<PredictionResult>() {
	      public void onFailure(Throwable caught) {
	        // TODO: Do something with errors.
	      }

	      public void onSuccess(PredictionResult result) {
	    	RootPanel.get("prediction_response").clear();
			
	    	TextArea validQuery = new  TextArea();
	    	validQuery.setCharacterWidth(65);
	    	validQuery.setVisibleLines(10);
	    	validQuery.setReadOnly(true);
			
			if (result.isValidQuery()) {
				validQuery.setText(result.getPredRes());
				
			}
			else {
				validQuery.setText("The query is not valid for LarKC");
			}
			
			RootPanel.get("prediction_response").add(validQuery);
			
	      }
	    };

	    //Invoke the prediction service
	    predServ.predict(predData, callback);

		
	}
}
