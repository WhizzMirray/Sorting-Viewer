package algorithms;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import main.Panel;
//TODO Javadoc
public class Sort implements Runnable{
	private boolean pause;
	private Panel panel;
	private Thread thread;
	private boolean finished;
	protected int num;
	protected ArrayList<Integer> ar = Panel.ar;
	
	private long targetTime;
	
	protected void start(){
			setFinished(false);
			thread = new Thread(this);
			thread.start();
			System.out.println("Thread Started");
	}
	protected Sort(int num,Panel panel) {
		this.panel= panel;
		this.num = num;
	}
	
	@Override
	public void run() {}//not needed, it gets overridden later 
	
	public void setSpeed(int speed){
		switch (speed) {
		case Algorithms.SLOW:
			targetTime = 60;
			break;
		case Algorithms.NORMAL:
			targetTime = 30;
			break;
		case Algorithms.FAST:
			targetTime = 18;
			break;
		default:
			break;
		}
	}
	public void resume() {
        synchronized (this) {
            pause = false;
            this.notifyAll(); // Unblocks thread
        }      
    }
	
	public void pause(){
		pause = true;
	}
	
	public boolean isPaused(){
		return pause;
	}
	
	public boolean isFinished() {
		return finished;
	}
	protected void setFinished(boolean finished) {
		this.finished = finished;
		
	}
	private void pauseThread(){
		try {
			synchronized (this) {
				this.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private void sleep(long wait){
		try {
			Thread.sleep(wait);
		}catch (InterruptedException e) {
			e.printStackTrace();
			run();
		}
	}
	protected void update_and_pause(long start){//updates the screen and pauses the thread
		System.out.println("update and pause");
		long elapsed,wait;
		panel.repaint();
		elapsed = System.currentTimeMillis() - start;
		wait = targetTime - elapsed; 
		sleep(wait);
		System.out.println("Wait " + wait);;
		if(pause){
			System.out.println("thread paused");
			pauseThread();
		}
	}
	
	protected void finishedMessage(){
		JOptionPane.showMessageDialog(null, "Sorted !");
		System.out.println("Finished");
	}
	
	protected void swap(int i,int j){
		int k = j;
		ar.set(j, ar.get(i));
		ar.set(i,k);
	}
	
}
