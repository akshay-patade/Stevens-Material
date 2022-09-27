/*
 * Name: Akshay Pradeep Patade
 * CWID: 20009092
 */


package assignment6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Anagrams {
	
	//Initializing an array of prime numbers for each character
	final Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61,
			67, 71, 73, 79, 83, 89, 97, 101};
	
	//Letter table is used to store the unique prime number for each character
	Map < Character, Integer > letterTable;
	
	//Storing all the anagrams
	Map < Long, ArrayList < String > > anagramTable;
	
	//Initializing default constructor all the method builLetterTable() so that our hashmap letterTable is created
	public Anagrams() {
		
		buildLetterTable();
		anagramTable = new HashMap<>();
	}
	
	//Method which is used to assign each character with a unique prime number
	private void buildLetterTable() {
		
		letterTable = new HashMap<>();
		
		for(int i = 97; i <= 122; i++) 
			letterTable.put(Character.valueOf((char)i), primes[i - 97]);
		
	}
	
	//Storing the anagrams in the anagramTable
	private void addWord(String strLine) {
		// TODO Auto-generated method stub
		
		Long value = myHashCode(strLine);
		
		if(anagramTable.containsKey(value)) {
			
			anagramTable.get(value).add(strLine);	
		}
		
		else {

			ArrayList <String> s =new ArrayList<>();
			s.add(strLine);
			anagramTable.put(value, s);	
		}
	}
	
	//Calculating the hashcode for each word
	private Long myHashCode(String s) {
		
		long value = 1;
		
		for(int i = 0; i < s.length(); i++) 
			
			value = value * (long)letterTable.get(s.charAt(i));
		
		return value;
	}
	
	//Processing each word in the file till the end and adding the word in the anagramTable
	private void processFile(String s) throws IOException { 
		
		FileInputStream fstream = new FileInputStream( s );
		BufferedReader br = new BufferedReader ( new InputStreamReader( fstream ));
		String strLine ;
		while (( strLine = br. readLine ()) != null ) {
			
			this.addWord ( strLine );
		}
		
		br.close ();
	}
	
	//Function used to find the maximum anagram entries in the anagramTable
	private ArrayList < Map.Entry < Long, ArrayList< String > > > getMaxEntries() {
		
		ArrayList < Map.Entry < Long, ArrayList < String > > > entries = new ArrayList <>();
		
		int size = 0;
		
		for(Map.Entry<Long,ArrayList<String>> entry : anagramTable.entrySet()) {
			
			if(entry.getValue().size() > size) {
				
				entries.clear();
				entries.add(entry);
				size = entry.getValue().size();
			}
			
			else if(entry.getValue().size() == size)
				
				entries.add(entry);
		}
		
		return entries;	
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Anagrams a = new Anagrams ();
		final long startTime = System.nanoTime ();
		try {
			
			a.processFile ( "words_alpha.txt" );
		}
		catch ( IOException e1 ) {
			
			e1.printStackTrace ();
		}
		
		ArrayList < Map.Entry < Long , ArrayList < String >>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime () - startTime;
		final double seconds = (( double ) estimatedTime / 1000000000 );
		System.out.println ( " Time : "+ seconds );
		System.out.println ( " List of max anagrams : "+ maxEntries );
	}

}
