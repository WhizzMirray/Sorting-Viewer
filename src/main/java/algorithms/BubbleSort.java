package algorithms;

import main.Panel;

public class BubbleSort extends Sort{

	public BubbleSort(int num,Panel panel) {
		super(num,panel);
		start();
		
	}
	
	@Override
	public void run() {
		System.out.println("run started");
		long start;
		int n = num;
		while(n != 0){
			int nn = 0;
			for(int i = 1 ; i < n; i++){
				System.out.println(i);
				start = System.currentTimeMillis();
		

				if(ar.get(i-1) > ar.get(i)){
					
					int k = ar.get(i-1);
					ar.set(i-1, ar.get(i));
					ar.set(i,k);
					nn = i;
				}
				
				update_and_pause(start);
				
			}
			n = nn;
		}
		setFinished(true);
		finishedMessage();
		System.out.println("finished");
	}
	
	
}
