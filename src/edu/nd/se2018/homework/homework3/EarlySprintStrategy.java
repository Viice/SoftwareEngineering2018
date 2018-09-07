package edu.nd.se2018.homework.homework3;

//Randall Krueger
//9/7/18
//Software Development
//Early Sprint Strategy

public class EarlySprintStrategy implements RunStrategy {

	@Override
	public void run(Horse horse) {
		if (horse.distanceRun < 2) {
			horse.distanceRun += horse.initialSpeed * .01;
		} else {
			horse.distanceRun += horse.initialSpeed * .0075;
		}
	}

}
