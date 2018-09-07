package edu.nd.se2018.homework.homework3;

//Randall Krueger
//9/7/18
//Software Development
//Test 3

import org.junit.Test;

public class RaceTest3 {
	@Test
	public void test() {
		Race race = new Race();

		race.enrollHorse("Whiz", 0, 24, new SteadyRunStrategy());
		race.enrollHorse("Jerry", 1, 24, new SteadyRunStrategy());

		assert (race.start().name == "Whiz");
	}
}
