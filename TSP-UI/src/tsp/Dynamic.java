package tsp;

import java.util.ArrayList;
import java.util.HashMap;

public class Dynamic {

	static int size = Instance.size();
	private static ArrayList<Integer> subset = new ArrayList<Integer>();
	private static ArrayList<Integer> set = new ArrayList<Integer>();
	private static ArrayList<Integer> numbers = new ArrayList<Integer>();
	
	public static void findPath(){
		
		for(int i=0;i<Instance.size();i++){
			set.add(i);
		}
		set.remove(0);
		
		HashMap<String, ArrayList> sets = new HashMap<String, ArrayList>();
        HashMap<String, ArrayList> sets2 = new HashMap<String, ArrayList>();
        for(int i=0;i<set.size();i++){
        	ArrayList<Integer> subset = new ArrayList<Integer>();
        	subset.add(set.get(i));
        	sets2.put("f("+set.get(i)+")",subset);
        }
        
        System.out.println(sets2.toString());
        
        int number = set.size();
        do{
        	sets = new HashMap<String, ArrayList>(sets2);
        	sets2 = new HashMap<String, ArrayList>();
	        for(int i=0;i<set.size();i++){
	        	ArrayList<Integer> subset = new ArrayList<Integer>();
        	
	        	for(java.util.Map.Entry<String, ArrayList> entry : sets.entrySet()) {
	        	    String key = entry.getKey();
	        	    ArrayList<Integer> value = entry.getValue();
	        	    
	        	    if(!value.contains(set.get(i))){
	        	    	
	        	    	subset = new ArrayList<Integer>(value);
	        	    	subset.add(0,set.get(i));
	        	    	
	        	    	String functionName = "f(";
	        	    	for(int k=0;k<subset.size();k++){
	        	    		if( k == subset.size()-1 ){
	        	    			functionName = functionName + subset.get(k) + ")";
	        	    		}
	        	    		else{
	        	    			functionName = functionName + subset.get(k) + ",";
	        	    		}
	        	    	}
	        	    	
	        	    	sets2.put(functionName,subset);
	        	    }
	        	}
	        	
	        }
	        number--;
	        System.out.println(sets.toString());
        } while( number >= 1);
	}
}
