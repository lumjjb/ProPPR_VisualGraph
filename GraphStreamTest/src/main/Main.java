package main;

import style.*;
import gsim.GraphSim;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;




public class Main {

	public static void main(String[] args) {
	     
		ControlUI mainUI = new ControlUI();
		//startUI();
	        
		
		//simulate_simple();
		
		//misc();
	}
	
	
	
	/*
	public void actionPerformed(ActionEvent e) {
	    //Handle open button action.
	    if (e.getSource() == openButton) {
	        int returnVal = fc.showOpenDialog(jfrm);

	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	            //This is where a real application would open the file.
	            System.out.println("Opening: " + file.getName() + "." + "\n");
	        } else {
	        	System.out.println("Open command cancelled by user." + "\n");
	        }
	   }
	}
	*/

	/**
	 * @return nothing 
	 * 
	 */
	public static void simulate_simple()
	{
		GraphSim gs = new GraphSim("PageRankGraph");
		
		// Import vetices/edges to start and init/display graph
		gs.importGraph(new File("/Users/brandon/Desktop/graph2.txt"));
		
		gs.initSim();
		
		
		//File exportFile = new File("export.txt");
		//gs.importEvents(exportFile);
		
		
		//Comment out these 2 liens for no animate
		gs.process();
		//gs.display(500,500,0,0);

		
		// Dynamic actions to simujlate PPR
		
//		
//		gs.addVertex("v1");
//		gs.addVertex("v2");
//		gs.addVertex("v3");
//		
//		gs.addEdge( "v1->v2", "v1", "v2" ,true);
//		gs.addEdge( "v2->v3", "v2", "v3" ,true);
//		gs.addEdge( "v3->v1", "v3", "v1" ,true);
//		
//		gs.ppr_teleport("v1");
//		gs.ppr_go("v2");
//		gs.ppr_go("v3");
//		
//		gs.ppr_teleport("v1");
//		gs.ppr_go("v2");
//		
//		gs.ppr_teleport("v1");
//		
//		gs.ppr_teleport("v1");
////		
//		File exportFile = new File("export.txt");
//		gs.exportEvents(exportFile);
		/*
		 * 
		File exportFile = new File("export.txt");
		gs.importEvents(exportFile);
		
		*/
	}
	
	
	
	public static void misc()
	{
		// TODO Auto-generated method stub
		
		
		Graph graph = new SingleGraph("Tutorial 1");

		System.out.println(graph.isAutoCreationEnabled());
		
		graph.setAutoCreate(true);
		System.out.println(graph.isAutoCreationEnabled());
		graph.addNode("A");
		
		graph.addEdge("AB",1, 2 ,true);
		//graph.addEdge( "BC", "B", "C" );
		//graph.addEdge( "CA", "C", "A" );
		System.exit(0);
		
		
		
		/*
				Graph graph = new SingleGraph("Tutorial 1");
				graph.addNode("A" );
				graph.addNode("B" );
				graph.addNode("C" );
				

				graph.addEdge( "AB", "A", "B" );
				graph.addEdge( "BC", "B", "C" );
				graph.addEdge( "CA", "C", "A" );

				
				  
			        graph.setAutoCreate(true);
			        graph.setStrict(false);
			        graph.display();

			        graph.addEdge("AB", "A", "B");
			        graph.addEdge("BC", "B", "C");
			        graph.addEdge("CA", "C", "A");
			        graph.addEdge("AD", "A", "D");
			        graph.addEdge("DE", "D", "E");
			        graph.addEdge("DF", "D", "F");
			        graph.addEdge("EF", "E", "F");

				
				//graph.display();
				
				*/
				/* Node Properties */
				Node A = graph.getNode("A");
				A.setAttribute("weight",5);

				
				System.out.println(A.getAttribute("weight"));
				
				A = graph.getNode("A");		
				System.out.println(A.getAttribute("weight"));

				
				Edge AB = graph.getEdge("AB");
				
				
				System.out.println("Degree of A: " + A.getDegree());
				
				
				
				//A.setAttribute("ui.class", "marked");
				
				/* Node Iteration */
				Iterator<Node> nodes_iter = graph.getNodeIterator();
						
				while(nodes_iter.hasNext())
				{
					
					Node n = nodes_iter.next();
					System.out.println(n);
					n.addAttribute("ui.label", "LL_"+ n.toString());
				}
				Collection<Node> nodes = graph.getNodeSet();
				
				
				/* Styling */		
		        String style = StyleImporter.getStyle("graph_style.css");
				graph.setAttribute("ui.stylesheet", style);
				//System.out.println("Graph Style: " + graph.getAttribute("ui.stylesheet"));
				
				
				
				// Graph display and explore
				graph.display();

	}
	
	public static void explore(Node source) {
	    Iterator<? extends Node> k = source.getBreadthFirstIterator();

	    while (k.hasNext()) {
	        Node next = k.next();
	        next.setAttribute("ui.class", "marked");
	        try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}

}
