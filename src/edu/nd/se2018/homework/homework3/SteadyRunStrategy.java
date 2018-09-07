package edu.nd.se2018.homework.homework3;

//Randall Krueger
//9/7/18
//Software Development
//Steady Run Strategy

public class SteadyRunStrategy implements RunStrategy {

	@Override
	public void run(Horse horse) {
		horse.distanceRun += horse.initialSpeed * .008;
	}
}
