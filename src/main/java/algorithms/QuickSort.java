package algorithms;

import main.Panel;

public class QuickSort extends Sort{

	public QuickSort(int num, Panel panel) {
		super(num, panel);
		start();
		
	}
	
	@Override
	public void run() {
		System.out.println("run started");
		quicksort(0,num-1);
		setFinished(true);
		finishedMessage();
		System.out.println("finished");
	}
	private void quicksort(int l,int h){
		if(l < h){
			int p = partition(l, h);
			quicksort(l, p-1);
			quicksort(p+1, h);
		}
	}
	private int partition(int l,int h){
		long start;
		int pivot = ar.get(h),i = l;
		for(int j = l;j < h;j++){
			start = System.currentTimeMillis();
			if(ar.get(j) <= pivot){
				int k = ar.get(j);
				ar.set(j, ar.get(i));
				ar.set(i,k);
				update_and_pause(start);
				i++;
			}
		}
		start = System.currentTimeMillis();
		int k = ar.get(h);
		ar.set(h, ar.get(i));
		ar.set(i,k);
		//TODO Test update and pause frequency for better display
		update_and_pause(start);
		return i;
	}
}
