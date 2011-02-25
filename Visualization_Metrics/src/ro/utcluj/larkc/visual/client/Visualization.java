package ro.utcluj.larkc.visual.client;
import ro.utcluj.larkc.visual.client.presentation.LeftMenu;
import ro.utcluj.larkc.visual.client.presentation.PlatformModule;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Visualization implements EntryPoint {

	/* Declaring and Instantiating all Layout Modules */
	//public static Home homeContent = new Home();
	
	public VerticalPanel mainContaier = new VerticalPanel();
	public PlatformModule platformContent = new PlatformModule();
	public LeftMenu leftMenuBlock = new LeftMenu();
	public Label pageName = new Label();
	
	/**
	 * Entry point method.
	 */
	public void onModuleLoad() {
		
		leftMenuBlock.loadModule(mainContaier,pageName);
		//platformContent.loadModule(mainContaier,pageName);
				
		//rrd.loadContent(mainContaier, pageName);
		//mainContaier.add(pieSp);
		
		RootPanel.get("metricsList").add(mainContaier);
		RootPanel.get("pageName").add(pageName);
		
		
	}//onModuleLoad()
	
	
}//Class StockWatcher