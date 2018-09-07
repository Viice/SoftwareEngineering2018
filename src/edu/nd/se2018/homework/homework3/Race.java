package edu.nd.se2018.homework.homework3;

//Randall Krueger
//9/7/18
//Software Development
//Race Controller Class

import java.util.ArrayList;
import java.util.List;

public class Race {
	private List<Horse> horses = new ArrayList<>();

	public Race() {
	}

	public void enrollHorse(String name, int id, int initialSpeed, RunStrategy runStrategy) {
		this.horses.add(new Horse(name, id, initialSpeed, runStrategy));
	}

	public Horse start() {
		while (true) {
			for (Horse horse : horses) {
				if (horse.run() >= 10) {
					System.out.println(horse.name + " won!");
					return horse;
				}
			}
			System.out.println();
		}
	}
}
