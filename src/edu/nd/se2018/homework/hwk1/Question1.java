package edu.nd.se2018.homework.hwk1;

import java.util.HashSet;

public class Question1 {
		
	public Question1(){}
	
	public int getSumWithoutDuplicates(int[] numbers){
		HashSet<Integer> set = new HashSet<Integer>();
		int total = 0;
		
		for(int number : numbers){
			  set.add(number);
		}
		
		for (int number : set) {
			total += number;
		}
		
		return total;	
	}
}
