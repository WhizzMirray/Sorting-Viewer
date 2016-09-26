package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JPanel;
import algorithms.Algorithms;
import algorithms.BubbleSort;
import algorithms.InsertionSort;
import algorithms.QuickSort;
import algorithms.Sort;


@SuppressWarnings("serial")
public class Panel extends JPanel{
	private int WIDTH;
	private final int HEIGHT = 400;
	private final int MAX_VALUE = 40;
	private int N_ELEMENT,PRESET;
	public static ArrayList<Integer> ar;
	private int rect_width,rect_unit;
	private boolean rndColor;
	public Sort sorting_algo; 
	public Thread thread;
	
	public Panel(){ 
		System.out.println("new Panel() Start");
	}
	
	
	
	/**
	 * Chooses the algorithm to use
	 * @param num num number of values to sort
	 * @param algorithm the number of the algorithm to use
	 * @return instance of Sort
	 */
	private Sort chooseAlgorithm(int num,int algorithm){
		switch (algorithm) {
		case Algorithms.BUBBLESORT:
			return new BubbleSort(num, this);
			
		case Algorithms.INSERTIONSORT:
			return new InsertionSort(num, this);
			
		case Algorithms.QUICKSORT:
			return new QuickSort(num, this);
			
		default:
			return null;
		}
	}
	/**
	 * Starts the sorting process
	 * @param num number of values to sort
	 * @param algorithm the number of the algorithm to use
	 * @see Algorithms
	 */
	public void New(int num,int algorithm,int speed,int preset){
		clearScreen();
		N_ELEMENT = num;
		PRESET = preset;
		init();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		sorting_algo = chooseAlgorithm(num, algorithm);
		sorting_algo.setSpeed(speed);

	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		updateScreen(g);
		System.out.println("repainted");
	}
	
	/**
	 * Generates an ArrayList with random Integers from 0 to MAX_VALUE
	 * @param n Number of Integers to generate
	 * @param p Method of generation
	 * @return an ArrayList of random numbers
	 */
	private ArrayList<Integer> generateList(int n,int p){
		ArrayList<Integer> k = new ArrayList<Integer>();
		
		switch(p){
			case Algorithms.RANDOM :
				for(int i = 0; i < n ; i++)
					k.add((int) (Math.random() * MAX_VALUE + 1));
				break;
			case Algorithms.SORTED :
				k = generateList(n, Algorithms.RANDOM);
				Collections.sort(k);
				break;
			case Algorithms.INVERSED :
				k = generateList(n, Algorithms.SORTED);
				Collections.reverse(k);
				break;
		}
		return k;
	}
	/**
	 * Initializes some variables  
	 */
	private void init(){
		ar = generateList(N_ELEMENT,PRESET);
		rect_width = 800 / N_ELEMENT; //800px Default size
		WIDTH = rect_width * N_ELEMENT;//To compensate for the float loss
		rect_unit = HEIGHT / MAX_VALUE;//To draw every rectangle according to their value in the list
		rndColor = false;
	}
	
	/**
	 * Draws A rectangle in the i'th position in the ArrayList
	 * @param i position of the rectangle in the ArrayList
	 * @param rndColorn true for rainbow mode
	 * @param g Graphics Object of Panel
	 */
	private void drawRect(int i,boolean rndColorn,Graphics2D g){
		int v = ar.get(i);
		if(rndColor){
			int r = (int)(Math.random()*255 + 1),b = (int)(Math.random()*255 + 1)
			  , j = (int) (Math.random()*255 + 1);
			g.setColor(new Color(r,b,j));
			//System.out.println(r + " " + b + " " + j);
		}
		else{
			g.setColor(new Color(101,200,127));
		}
		g.fillRect(i*rect_width, HEIGHT - rect_unit*v, rect_width, rect_unit*v);//Green rectangle
		g.setColor(Color.BLACK);
		g.drawRect(i*rect_width, HEIGHT - rect_unit*v, rect_width, rect_unit*v);//Black border
	}
	
	/**
	 * Updates the screen
	 * @param g Graphics Object of Panel
	 */
	public void updateScreen(Graphics g){
		for(int i = 0; i < N_ELEMENT; i++){
			drawRect(i,rndColor,(Graphics2D)g);
		}
	}
	
	/** Deletes all the rectangles in the screen, can also be used to clear the screen
	 * all together
	 */
	public void clearScreen(){
		for(int i = 0; i < N_ELEMENT; i++){
			clearRect(i);
		}
	}
	
	/**
	 * Deletes the rectangle in the position i in the ArrayList from the screen
	 * @param i position of the rectangle in the ArrayList
	 */
	public void clearRect(int i){
		int v = ar.get(i);
		Graphics2D g = (Graphics2D)getGraphics();
		g.clearRect(i*rect_width, HEIGHT - rect_unit*v, rect_width+1, rect_unit*v);
		g.dispose();
	}


}//Class end