package edu.nd.se2018.homework.hwk1;

import java.util.Arrays;
import java.util.HashMap;

public class Question2 {

	public Question2(){}
	
	public String getMostFrequentWord(String input, String stopwords){
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int highestOccurrance = 0;
		String mostCommonWord = null;
		String[] stopWordsArray = stopwords.split(" ");
		
		for (String i : input.split(" ")) {
			if (map.containsKey(i)) {
				map.put(i, map.get(i) + 1);
			} else {
				map.put(i,  1);
			}
		}
		
		for (String i : map.keySet()) {
			if (!(Arrays.asList(stopWordsArray).contains(i))) {
				if (map.get(i) == highestOccurrance) {
					mostCommonWord = null;
				} else if (map.get(i) > highestOccurrance) {
					highestOccurrance = map.get(i);
					mostCommonWord = i;
				}
			}
		}
		return mostCommonWord;
	}
}
