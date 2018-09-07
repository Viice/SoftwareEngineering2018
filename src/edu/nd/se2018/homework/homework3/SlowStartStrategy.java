package edu.nd.se2018.homework.homework3;

//Randall Krueger
//9/7/18
//Software Development
//Slow Start Strategy

public class SlowStartStrategy implements RunStrategy {

	@Override
	public void run(Horse horse) {
		if (horse.distanceRun < 6) {
			horse.distanceRun += horse.initialSpeed * .0075;
		} else if (horse.distanceRun < 9) {
			horse.distanceRun += horse.initialSpeed * .009;
		} else {
			horse.distanceRun += horse.initialSpeed * .01;
		}
	}
}
