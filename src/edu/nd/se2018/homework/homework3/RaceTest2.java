package edu.nd.se2018.homework.homework3;

//Randall Krueger
//9/7/18
//Software Development
//Test 2

import org.junit.Test;

public class RaceTest2 {
	@Test
	public void test() {
		Race race = new Race();

		race.enrollHorse("Blaze", 0, 26, new EarlySprintStrategy());
		race.enrollHorse("Knox", 1, 25, new SlowStartStrategy());

		assert (race.start().name == "Blaze");
	}
}
