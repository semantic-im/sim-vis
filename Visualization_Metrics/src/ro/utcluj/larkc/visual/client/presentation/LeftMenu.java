package ro.utcluj.larkc.visual.client.presentation;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.smartgwt.client.widgets.tree.events.NodeClickHandler;

public class LeftMenu {
	
	VerticalPanel mainContaier = null;
	Label pageName = null;
	
	public LeftMenu(){
		
	}

    public void loadModule(VerticalPanel mainContaier, Label pageName) {
    	/* 
    	 * The main container in which the View is displayed
    	 */
    	this.mainContaier = mainContaier;
    	this.pageName = pageName;
    	
        SectionStack sectionStack = new SectionStack();
        sectionStack.setAnimateSections(true);
        sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
        sectionStack.setWidth(180);
        sectionStack.setHeight(350);        
        /*
         * About - Section
         */
        SectionStackSection section1 = new SectionStackSection("About");
        section1.setExpanded(false);
        HTMLFlow htmlFlowAbout = new HTMLFlow();
        htmlFlowAbout.setOverflow(Overflow.AUTO);
        htmlFlowAbout.setPadding(5);
        String contents = "<p>The aim of the EU FP 7 Large-Scale Integrating Project LarKC is to develop" +
        				  "the Large Knowledge Collider (LarKC), a platform for massive distributed" +
        				  "incomplete reasoning that will remove the scalability barriers of currently" +
        				  "existing reasoning systems for the Semantic Web.</p>";
        htmlFlowAbout.setContents(contents);
        section1.addItem(htmlFlowAbout);
        
        //section1.addItem(new Img("pieces/48/pawn_blue.png", 48, 48));
        sectionStack.addSection(section1);
        /***********************************************************************************************
         * Mihai test
         */
        SectionStackSection section_m1 = new SectionStackSection("TEST");
        section_m1.setExpanded(false);
        sectionStack.addSection(section_m1);
           
        /*
         * Instrumentation Section
         */
        SectionStackSection section2 = new SectionStackSection("Instrumentation");
        section2.setExpanded(true);
        //section2.setCanCollapse(true);
        /** 
         * Drawing Tree Greed
         */
        TreeNode instrumentationTreeRoot = new InstrumentationTreeNode("1", "Root", 
                new InstrumentationTreeNode("4", "Platform"), 
                        new InstrumentationTreeNode("188", "Workflow"),
                        //new InstrumentationTreeNode("189", "Plugin"), 
                        //new InstrumentationTreeNode("265", "Query"),
                        new InstrumentationTreeNode("266", "RRD Test")
                        );
        
        Tree instrumentationTree = new Tree();
        instrumentationTree.setModelType(TreeModelType.CHILDREN);
        instrumentationTree.setNameProperty("Name");
        instrumentationTree.setChildrenProperty("directReports");
        instrumentationTree.setRoot(instrumentationTreeRoot);
        
        TreeGrid instrumentationTreeGrid = new TreeGrid();
        
        instrumentationTreeGrid.setShowHeader(true);  
        instrumentationTreeGrid.setLeaveScrollbarGap(true);  
        instrumentationTreeGrid.setAnimateFolders(true);  
        instrumentationTreeGrid.setCanAcceptDroppedRecords(false);  
        instrumentationTreeGrid.setCanReparentNodes(true);  
        instrumentationTreeGrid.setSelectionType(SelectionStyle.SINGLE);  
        
        //employeeTreeGrid.setWidth(500);
        //employeeTreeGrid.setHeight(400);
        instrumentationTreeGrid.setNodeIcon("icons/16/world.png");
        instrumentationTreeGrid.setFolderIcon("icons/16/world.png");
        instrumentationTreeGrid.setShowOpenIcons(false);
        instrumentationTreeGrid.setShowDropIcons(false);
        instrumentationTreeGrid.setClosedIconSuffix("");
        //employeeTreeGrid.setFields(new TreeGridField("Name"));
        instrumentationTreeGrid.setData(instrumentationTree);
        
        instrumentationTreeGrid.getData().openAll();
        
        //add ClickHandler
        instrumentationTreeGrid.addNodeClickHandler(new NodeClickHandler() {
			
			@Override
			public void onNodeClick(NodeClickEvent e) {
				TreeNode node = e.getNode();
				click(node);
			}
		});
        
        //employeeTreeGrid.draw();
        section2.addItem(instrumentationTreeGrid);
        sectionStack.addSection(section2);

        /*
         * Prediction Section
         */
        SectionStackSection section3 = new SectionStackSection("Prediction");
        section3.setExpanded(false);
             
        //section3.addItem(new Img("pieces/48/pawn_green.png", 48, 48));
        sectionStack.addSection(section3);

        /*
         * Help Section
         */
        SectionStackSection section4 = new SectionStackSection("Help");
        section4.setExpanded(false);
        
        HTMLFlow htmlFlowHelp = new HTMLFlow();
        htmlFlowHelp.setOverflow(Overflow.AUTO);
        htmlFlowHelp.setPadding(5);
        String contentsHelp = "<p>Work package 11 deals with the Instrumentation and Monitoring" +
        					  "of the LarKC platform and also provides a Prediction and Relevance" +
        					  "Feedback module. <br> The Parteners for Work package 11 are: <br> </p>" +
        					  "<ul>" +
        					  "<li> <a onclick=\"window.open(this.href);return false;\" href\"http://cv.utcluj.ro/\">Technical University of Cluj-Napoca, Computer Science Department, Image Processing and Patern Recognition Group</a></li>" +
        					  "<li> <a onclick=\"window.open(this.href);return false;\" href=\"http://www.softgress.com/\">SoftGress Company</a></li></ul>";
        htmlFlowHelp.setContents(contentsHelp);
        section4.addItem(htmlFlowHelp);
        
        //section4.addItem(new Img("pieces/48/piece_yellow.png", 48, 48));
        sectionStack.addSection(section4);

       
        HLayout layout = new HLayout();
        layout.setHeight100();
        
        layout.addMember(sectionStack);
        RootPanel.get("menu").add(layout);
               
    }//loadModule
    
    private void click(TreeNode node) {
    	
    	String name = node.getAttribute("Name");
    	mainContaier.clear();
    	if(name.equalsIgnoreCase("workflow")){
    		WorkflowModule workflowContent = new WorkflowModule();
    		workflowContent.loadModule(mainContaier,pageName);
    	}
    	else if(name.equalsIgnoreCase("platform")){
    		PlatformModule platformContent = new PlatformModule();
    		platformContent.loadModule(mainContaier,pageName);
    	}
    	else if(name.equalsIgnoreCase("RRD Test")){
    		Home homeContent = new Home();
    		homeContent.loadContent(mainContaier,pageName);
    	}  	
	}

    public static class InstrumentationTreeNode extends TreeNode {
        public InstrumentationTreeNode(String nodeId, String name) {
            this(nodeId, name, new InstrumentationTreeNode[] {});
        }
        
        public InstrumentationTreeNode(String nodeId, String name, InstrumentationTreeNode... instrumentationTreeNode) {
            setAttribute("EmployeeId", nodeId);
            setAttribute("Name", name);
            setAttribute("directReports", instrumentationTreeNode);
        }
    }
}


