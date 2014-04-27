package tsp;

import java.util.ArrayList;
import java.util.Date;

public class Algoritm {
	
	
public static void main(String[] args) {
		
	final long startTime = System.nanoTime();
		nFac(14);
		final long duration = System.nanoTime() - startTime;
		Date mdate = new Date(duration/1000);
		System.out.println(duration + " " + duration/1000000000.0);
		//Dynamic.solveInstanceDP();
	}
static void nFac(int n) {
	  for(int i=0; i<n; i++) {
	    nFac(n-1);
	  }
	}

}