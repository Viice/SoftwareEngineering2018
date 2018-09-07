package edu.nd.se2018.homework.homework3;

//Randall Krueger
//9/7/18
//Software Development
//Horse Class

public class Horse {
	public String name;
	public int id;
	public int initialSpeed;
	public RunStrategy runStrategy;
	public float distanceRun;

	public Horse(String name, int id, int initialSpeed, RunStrategy runStrategy) {
		this.name = name;
		this.id = id;
		this.initialSpeed = initialSpeed;
		this.runStrategy = runStrategy;
		this.distanceRun = 0;
	}

	public void setRunStrategy(RunStrategy runStrategy) {
		this.runStrategy = runStrategy;
	}

	public float run() {
		runStrategy.run(this);
		System.out.println(this.name + " has run " + String.format("%.2f", this.distanceRun) + " miles.");
		return this.distanceRun;
	}
}
