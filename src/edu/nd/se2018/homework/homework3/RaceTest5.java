package edu.nd.se2018.homework.homework3;

//Randall Krueger
//9/7/18
//Software Development
//Test 5

import org.junit.Test;

public class RaceTest5 {
	@Test
	public void test() {
		Race race = new Race();

		race.enrollHorse("Tortoise", 0, 0, new SteadyRunStrategy());
		race.enrollHorse("Hare", 1, 10000, new SteadyRunStrategy());

		assert (race.start().name == "Hare");
	}
}
