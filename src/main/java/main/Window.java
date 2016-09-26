package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import algorithms.Algorithms;
//TODO Javadoc
@SuppressWarnings("serial")
public class Window extends JFrame{
		private Panel panel;
		private JMenu file;
		private JMenu edit;
		private int n,algorithm,speed,preset;
		
		public Window(){
		    panel = new Panel();
		    startNewSort();
		    
			JMenuBar JMB = new JMenuBar();
			setJMenuBar(JMB);
			
			file = new JMenu("File");
			edit = new JMenu("Edit");
			
			JMB.add(file);
			JMB.add(edit);
			
			file.add(New);
			file.add(new AbstractAction("Exit") {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
					
				}
			});
			edit.add(pause);
			setName("Sort Viewer");
			add(panel);
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setResizable(false);
			pack();
			
			
		}
		
		private void startNewSort(){
			int returned = selectionScreen();
			if(returned == 0){
				panel.New(n,algorithm,speed,preset);
			}
			else if(returned == 1)
				panel.sorting_algo.resume();
		}
		
		private int selectionScreen(){
			
			JTextField element_field = new JTextField(3);
		    JPanel myPanel = new JPanel();
		    JComboBox<String> algoBox = new JComboBox<>(Algorithms.NAMES);
		    JComboBox<String> speedBox = new JComboBox<>(Algorithms.SPEEDS);
		    JComboBox<String> presetBox = new JComboBox<>(Algorithms.PRESETS);
		    myPanel.setLayout(new GridBagLayout());
		    GridBagConstraints c = new GridBagConstraints();
		    c.fill = GridBagConstraints.HORIZONTAL;
		    
		    c.gridx = 0;
		    c.gridy = 0;
		    myPanel.add(new JLabel("Number of elements to sort:"),c);
		    c.gridx = 1;
		    c.gridy = 0;
		    myPanel.add(element_field,c);
		    c.gridx = 2;
		    c.gridy = 0;
		    myPanel.add(Box.createHorizontalStrut(15),c); // a spacer
		    c.gridx = 3;
		    c.gridy = 0;
		    myPanel.add(new JLabel("Please make a selection:"));
		    c.gridx = 4;
		    c.gridy = 0;
		    myPanel.add(algoBox,c);
		    c.gridx = 0;
		    c.gridy = 1;
		    myPanel.add(new JLabel("Speed :"),c);
		    c.gridx = 1;
		    c.gridy = 1;
		    myPanel.add(speedBox,c);
		    c.gridx = 2;
		    c.gridy = 1;
		    myPanel.add(Box.createHorizontalStrut(15),c); // a spacer
		    c.gridx = 3;
		    c.gridy = 1;
		    myPanel.add(new JLabel("Choose the array preset:"),c);
		    c.gridx = 4;
		    c.gridy = 1;
		    myPanel.add(presetBox,c);
		    
		    int result = JOptionPane.showConfirmDialog(null, myPanel, "Sorting"
		    		, JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
		      
		    if(result != JOptionPane.OK_OPTION){//if user didn't click on OK
		    	if(panel.sorting_algo == null)//if the sorting has not started
		    		System.exit(-1);//close the program

		    	return 1;//else close the window and continue sorting
		    }
		    algorithm = algoBox.getSelectedIndex();
		    speed = speedBox.getSelectedIndex();
		    preset = presetBox.getSelectedIndex();
		    try{
		    	n = Integer.parseInt(element_field.getText());
		    	if(n < 0)
		    		throw new Exception();
		    }catch (Exception e) {
		    	JOptionPane.showMessageDialog(null, "Enter A Positive Number Only");
				selectionScreen();
			}
		    
		    
		    return 0;
		}
		
		private Action pause = new AbstractAction("Pause") {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.sorting_algo.pause();
				edit.remove(0);
				edit.add(resume);
				
			}
		};
		
		private Action resume = new AbstractAction("Resume") {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.sorting_algo.resume();
				edit.remove(0);
				edit.add(pause);
			}
		};
		
		private Action New = new AbstractAction("New") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!panel.sorting_algo.isPaused())
					panel.sorting_algo.pause();//if not paused pause 
				
				startNewSort();
				
				
				
			}
		};
}