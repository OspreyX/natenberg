package com.drw.membar;

import static com.drw.membar.Dekker.criticalSection;
import static com.drw.membar.Dekker.intentFirst;
import static com.drw.membar.Dekker.intentSecond;
import static com.drw.membar.Dekker.turn;

public class FirstThread extends Thread{

	@Override public void run() {
		for(int i = 0; i < 100000; i++)
			protocol();
	}
	
	private void protocol(){
		intentFirst = true;

	    while (intentSecond)
	    	if (turn != 0) {
	    		intentFirst = false;
	    		while (turn != 0) {}
	    		intentFirst = true;
	    	}

	    criticalSection();

	    turn = 1;
	    intentFirst = false;
	}

}
