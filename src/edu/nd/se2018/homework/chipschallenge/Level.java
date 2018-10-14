package edu.nd.se2018.homework.chipschallenge;

//Randall Krueger
//10/12/18
//High level state class.

public interface Level {
	public int[] getStart();

	public int[][] getMap();

	public Level nextLevel();
}