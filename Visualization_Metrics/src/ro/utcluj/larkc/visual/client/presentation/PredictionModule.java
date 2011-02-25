package ro.utcluj.larkc.visual.client.presentation;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class PredictionModule {
	
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
	//private PredictionData predData = new PredictionData();
	//private PredictionServiceAsync predServ = GWT.create(PredictionService.class);
		
	
	Label pageName = new Label();
	
	public PredictionModule(){
		
		pageName.setText("Prediction");
		RootPanel.get("pageName").add(pageName);
	}
	
	

	 //Loading the module.
	 
	public void loadModule() {
		
		
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
	    VLayout vLayout = new VLayout();
	    vLayout.addChild(queryVP);
	    vLayout.addChild(queryVP);
	    vLayout.addChild(workflowVP);
	    vLayout.addChild(queryRBVP);
	    vLayout.addChild(queryCbVP);
	    vLayout.addChild(queryCbVP1);
	    vLayout.addChild(buttonsHP);
	    vLayout.setWidth(700);
	    vLayout.setHeight(700);
	    
	    RootPanel.get("predictionBlock").add(vLayout);
		
		//set some elements for the query text area
		queryTextArea.setText("Please input a SPARQL Query that you would like to run on the LarKC platform.");
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
			queryTextArea.setText("Please input a query that you would like to be analyzed by the Prediction module!");
			queryTextArea.selectAll();
			queryTextArea.setFocus(true);
			workflowTextArea.setText("Please input a Workflow description for the LarKC platform.");
			workflowTextArea.selectAll();
			
			}
		});
				
		sendButton.addClickHandler(
		new ClickHandler() {
			public void onClick(ClickEvent event) {
				//predData.setPredictionQuery(queryTextArea.getText());
				//predData.setPredictionWorkflow(workflowTextArea.getText());
				if(queryRB0.getValue()) {
					//predData.setPredictionType(0);
				}
				else if(queryRB1.getValue()) {
					//predData.setPredictionType(1);
				}
				
				else if(queryRB2.getValue()) {
					//predData.setPredictionType(2);
				}
				
				//predData.setUseConstraints(queryCb0.getValue());
				// TO DO set the constraints 
				//int constr=0;
				//predData.setConstraints(constr);	
				// invoke the remote procedure that performs prediction
				invokePrediction();
			}
		});
	}
	
	private void invokePrediction(){ 
		/*if(predServ == null) {
			predServ = GWT.create(PredictionService.class);
			
		}*/
		 // Set up the call-back object.
		
	}
}

