package edu.nd.se2018.homework.hwk1;

public class Question3 {

	public Question3() {
	}

	public int getMirrorCount(int[] numbers) {

		int longestString = 0;
		int tempString = 0;

		for (int iterator = 0; iterator < numbers.length; iterator++) {
			if (numbers[iterator] == numbers[numbers.length - 1 - iterator]) {
				++tempString;
				if (tempString > longestString) {
					longestString = tempString;
				}
			} else {
				tempString = 0;
			}
		}
		return longestString;
	}
}
