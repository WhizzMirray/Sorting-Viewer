package algorithms;

import main.Panel;

public class InsertionSort extends Sort{

	public InsertionSort(int num,Panel panel) {
		super(num,panel);
		start();
		
	}
	@Override
	public void run() {
		long start;	
		for(int i = 1 ; i < num; i++){
				
			int j = i;
			while(j > 0 && ar.get(j-1)>ar.get(j)){
				start = System.currentTimeMillis();
			
			
				//swap(j-1, j);
				int k = ar.get(j-1);
				ar.set(j-1, ar.get(j));
				ar.set(j,k);
				--j;
				
				update_and_pause(start);	
			}
		}
		setFinished(true);
		finishedMessage();
		System.out.println("finished");
	}

}

