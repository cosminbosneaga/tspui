package tsp;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class Tsp {

	public static void main(String[] args) {
		
		final long startTime = System.nanoTime();
		Genetic.evolutionary(15,200);
		final long duration = System.nanoTime() - startTime;
		Date mdate = new Date(duration/1000);
		System.out.println(duration + " " + duration/1000000000.0);
		
		
		
		//Dynamic.solveInstanceDP();
	}
}
