package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.EventObject;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.graphstream.ui.swingViewer.View;
import org.graphstream.ui.swingViewer.Viewer;
import org.graphstream.ui.swingViewer.ViewerListener;
import org.graphstream.ui.swingViewer.ViewerPipe;



public class ControlUI extends JFrame implements ActionListener {

	
	// Logic Variables
	MainLogic MLogic;
	File graph_file = null;
	File anim_file = null;
	
	GraphUIProperty gUIProp;
	
	// UI Components
	
	int frm_width, frm_height;
	int ctrl_width, ctrl_height;
	
	JFileChooser fc;
	JFrame  jfrm;
	
	JLabel lbl_header;
	JLabel lbl_graphPath;
	JButton btn_chooseGraph;
	
	JButton btn_loadGraph;
	
	JPanel ctrl_panel;
	
	JTextField txt_highlight;
	JButton btn_highlight;
	
	View vw=null;
	
	// Set % of span
	double btn_chooseGraph_h = .1;
	
	
	
	public ControlUI ()
	{
		init ();
		startUI();
	
	}
	
	private void init ()
	{
		
		// Init GUI Settings
		Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
		frm_width = screen_dim.width;
		frm_height = screen_dim.height;
		
		ctrl_width = (int)(0.2 * (double)screen_dim.width);
		ctrl_height = screen_dim.height;
		
		//System.out.println(screen_dim.height);
		 
		// Initialize graph UI proerty object to pass to sim
		gUIProp = new GraphUIProperty();
		gUIProp.height = screen_dim.height - 100;
		gUIProp.width = screen_dim.width - ctrl_width;
		gUIProp.posx = 0;
		gUIProp.posy = 0;
		
		MLogic= new MainLogic(gUIProp);
	}
	
	private  void startUI(){
		
		jfrm = new JFrame();
		jfrm.setBounds(0, 0, frm_width, frm_height);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfrm.getContentPane().setLayout(null);
		
		ctrl_panel = new JPanel();
		ctrl_panel.setBounds(frm_width - ctrl_width, 0, ctrl_width, frm_height);
		jfrm.getContentPane().add(ctrl_panel);
		ctrl_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		try{
			ControlUI.class.getResourceAsStream("images/ProPPR.png");
		BufferedImage myPicture = ImageIO.read(ControlUI.class.getResourceAsStream("images/ProPPR.png"));
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		ctrl_panel.add(picLabel);
		}
		catch(Exception e) { e.printStackTrace(); }
		
		lbl_graphPath = new JLabel("<No Graph>");
		ctrl_panel.add(lbl_graphPath);
		
		btn_chooseGraph = new JButton("Choose Graph");
		btn_chooseGraph.setSize(100,100);
		ctrl_panel.add(btn_chooseGraph);

        // File Chooser Component
        // Create a file chooser
        fc = new JFileChooser();
	    btn_chooseGraph.addActionListener(this);

		
	    btn_loadGraph = new JButton("Load Graph");
		ctrl_panel.add(btn_loadGraph);
		btn_loadGraph.addActionListener(this);

		/* Features to add
		// Block Label
		JLabel lbl_div1 = new JLabel("-----------------------------------");
		ctrl_panel.add(lbl_div1);

		

		// Highlight filter  Text Field and button
		txt_highlight = new JTextField();
		ctrl_panel.add(txt_highlight);
		txt_highlight.setColumns(10);
		
		btn_highlight = new JButton("Highlight Filter");
		btn_highlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MLogic.highlightFilter(txt_highlight.getText());
			}
		});
		ctrl_panel.add(btn_highlight);
		*/
		
		jfrm.setVisible(true);
		
		
		
		// We connect back the viewer to the graph,
        // the graph becomes a sink for the viewer.
        // We also install us as a viewer listener to
        // intercept the graphic events.
        //ViewerPipe fromViewer = vwr.newViewerPipe();
        //fromViewer.addViewerListener((ViewerListener) new NodeClickListener());
        //fromViewer.addSink(graph);
        
        
/*
		
		
		// Initialize Frame + set default behaviors
		jfrm=new JFrame("VisualProPPR Control");
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    jfrm.setLayout(new BorderLayout()); 

		
		// Set width height and location
		Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();

		jfrm.setSize(frm_width,frm_height);
		
		jfrm.setLocation(screen_dim.width - ctrl_width, 0);
		
		
	    
		
		
	     
	     // Label
	     lbl_header=new JLabel("Welcome to VisualProPPR");
	     jfrm.add(lbl_header);

	     
	     // Choose graph button
	     
	     //button dims
	     int btn_width = (int)((double)ctrl_width * 0.8);
	     int btn_height = (int)((double)ctrl_height * btn_chooseGraph_h);
	     btn_chooseGraph = new JButton("Graph Button Chooser");
	     btn_chooseGraph.setVerticalTextPosition(AbstractButton.CENTER);
	     btn_chooseGraph.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
	     btn_chooseGraph.addActionListener(this);
	     btn_chooseGraph.setLocation(frm_width-ctrl_width, 0);
	     btn_chooseGraph.setSize(btn_width, btn_height);
	     jfrm.add(btn_chooseGraph,BorderLayout.LINE_END);
	    
	     // Chosen Graph Label
	     lbl_graphPath =new JLabel("");
	     lbl_graphPath.setText("< No Graph Chosen >");
	     // Get location offset
	     //btn_chooseGraph.getLocation() + btn_chooseGraph.getSize();
	     lbl_graphPath.setLocation(frm_width - ctrl_width, 50);
	     
	     jfrm.add(lbl_graphPath, BorderLayout.LINE_END);
	     
	     
	     
	     
	     // File Chooser Component
	     //Create a file chooser
	     fc = new JFileChooser();
	     //In response to a button click:
	     //fc.showOpenDialog(jfrm);
	     
	     
	     // Load graph  button
	     btn_width = (int)((double)ctrl_width * 0.8);
	     btn_height = (int)((double)ctrl_height * btn_chooseGraph_h);
	     btn_loadGraph = new JButton("Load Graph");
	     btn_loadGraph.setVerticalTextPosition(AbstractButton.CENTER);
	     btn_loadGraph.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
	     btn_loadGraph.addActionListener(this);
	     btn_loadGraph.setLocation(frm_width-ctrl_width, 100);
	     //btn_loadGraph.setSize(btn_width, btn_height);
	     btn_loadGraph.setSize(100, 100);
	     jfrm.add(btn_loadGraph,BorderLayout.LINE_END);
	     
	     //int returnVal = fc.showOpenDialog(null);
	     
	     
	     // Add components
	     jfrm.setVisible(true);

	    */ 
	     /* 
	      * 		// Set it so program doesnt abort when close window 
		vwr.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);
		org.graphstream.ui.swingViewer.View vw = vwr.getDefaultView();
		vw.setSize(width,height);
		vw.setLocation(posx, posy);
		
	      */
	}
	
	
	
	
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		System.out.println("Event:" + e);
		System.out.println("Source:" + source);
		
		if (source == btn_chooseGraph)
		{
			int returnVal = fc.showOpenDialog(jfrm);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
	            graph_file = fc.getSelectedFile();
	            lbl_graphPath.setText(graph_file.getName());
	            //This is where a real application would open the file.
	            System.out.println("Opening: " + graph_file.getName() + "." + "\n");
	        } else {
	            System.out.println("Open command cancelled by user." + "\n");
	        }
			return;
		}
		if (source == btn_loadGraph)
		{
			loadGraph();
		}
	}
	
	
	NodeClickListener clisten=null;
	private void loadGraph()
	{
		if (graph_file != null)
		{
			// close event listener for moseu first before removing view 
			// in next step
			if (clisten!=null)
			{
				clisten.viewClosed(null);
			}
			// Remove view if exists
			if (vw!=null)
			{
				jfrm.remove(vw);
			}
			
			
			Viewer vwr = MLogic.simulate_graph(graph_file);
			
			vw = vwr.addDefaultView(false);
			
			
			System.out.println(gUIProp.width);
			System.out.println(gUIProp.height);
			vw.setSize(gUIProp.width,gUIProp.height);
			vw.setLocation(gUIProp.posx, gUIProp.posy);
			
			
			/* Features to add
			// We connect back the viewer to the graph,
	        // the graph becomes a sink for the viewer.
	        // We also install us as a viewer listener to
	        // intercept the graphic events.
	        ViewerPipe fromViewer = vwr.newViewerPipe();
	        clisten = new NodeClickListener(fromViewer, vw, MLogic.getGraph());
	        fromViewer.addViewerListener((ViewerListener) clisten);
			
			*/

			// Add in frame
			jfrm.add(vw,BorderLayout.LINE_START);
						
			
		} 
		else
		{
			JOptionPane.showMessageDialog(jfrm,
				    "Please select a graph!",
				    "No graph selected!",
				    JOptionPane.WARNING_MESSAGE);
		}
	}
	
}
