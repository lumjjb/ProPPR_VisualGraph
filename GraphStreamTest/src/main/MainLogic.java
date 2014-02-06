package main;

import gsim.GraphSim;

import java.io.File;

import org.graphstream.graph.Graph;
import org.graphstream.ui.swingViewer.Viewer;

public class MainLogic {
	
	GraphUIProperty gUIProp;
	GraphSim gs;
	
	public MainLogic(GraphUIProperty gUIProp)
	{
		this.gUIProp = gUIProp;
	}
	public Viewer simulate_graph(File graph_file)
	{
		gs = new GraphSim("PageRankGraph");
		
		// Import vetices/edges to start and init/display graph
		gs.importGraph(graph_file);
		
		gs.initSim();
		
		
		//File exportFile = new File("export.txt");
		//gs.importEvents(exportFile);
		
		
		//Comment out these 2 liens for no animate
		gs.process();
		
		return gs.get_display();

	
	}
	public Graph getGraph()
	{
		return gs.getGraph();
	}
	
	public void highlightFilter(String s)
	{
		gs.unmarkNodes();
		gs.markHighlight(s);
	}
	
}
