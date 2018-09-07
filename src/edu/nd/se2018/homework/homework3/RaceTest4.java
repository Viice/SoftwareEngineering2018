package edu.nd.se2018.homework.homework3;

//Randall Krueger
//9/7/18
//Software Development
//Test 4

import org.junit.Test;

public class RaceTest4 {
	@Test
	public void test() {
		Race race = new Race();

		race.enrollHorse("JJ", 0, 24, new SteadyRunStrategy());
		race.enrollHorse("Damian", 1, 24, new EarlySprintStrategy());

		assert (race.start().name == "JJ");
	}
}
