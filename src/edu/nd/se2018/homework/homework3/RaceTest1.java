package edu.nd.se2018.homework.homework3;

//Randall Krueger
//9/7/18
//Software Development
//Test 1

import org.junit.Test;

public class RaceTest1 {
	@Test
	public void test() {
		Race race = new Race();

		race.enrollHorse("Johnny", 0, 25, new EarlySprintStrategy());
		race.enrollHorse("Max", 1, 24, new SlowStartStrategy());
		race.enrollHorse("Flash", 2, 20, new EarlySprintStrategy());
		race.enrollHorse("Winnie", 3, 25, new SlowStartStrategy());
		race.enrollHorse("Tom", 4, 25, new SteadyRunStrategy());

		assert (race.start().name == "Winnie");
	}
}
