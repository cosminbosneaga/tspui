package tsp;

import java.util.Date;

public class Main {

	public static void main(String[] args) {
		
		final long startTime = System.nanoTime();
		//Evolutionary evo = new Evolutionary();
		//evo.evolveInstance(15,200);
		final long duration = System.nanoTime() - startTime;
		Date mdate = new Date(duration/1000);
		System.out.println(duration + " " + duration/1000000000.0);
		System.out.println(mdate.toString());
		
		
		//Dynamic.solveInstanceDP();
	}
}
