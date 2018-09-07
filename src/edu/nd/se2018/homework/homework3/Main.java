package edu.nd.se2018.homework.homework3;

//Randall Krueger
//9/7/18
//Software Development
//Main Sample Execution

public class Main {

	public static void main(String[] args) {
		Race race = new Race();

		race.enrollHorse("Sam", 0, 22, new EarlySprintStrategy());
		race.enrollHorse("Molly", 1, 24, new SlowStartStrategy());
		race.enrollHorse("Joe", 2, 25, new EarlySprintStrategy());
		race.enrollHorse("Blizzard", 3, 25, new SlowStartStrategy());
		race.enrollHorse("Flicker", 4, 25, new SteadyRunStrategy());

		race.start();
	}

}
