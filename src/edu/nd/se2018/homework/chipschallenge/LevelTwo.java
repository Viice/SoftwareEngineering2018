package edu.nd.se2018.homework.chipschallenge;

//Randall Krueger
//10/12/18
//Represents the second level.  Uses the state pattern.

public class LevelTwo implements Level {
	   public int[][] getMap() {
		   int[][] levelMap = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
								{ 1,30,30,30,30,30,30,30,30, 1,30,30,30,30,30, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
								{ 1,30,30,30, 1,30,30,30,30,30,30,30,30,30,30, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
								{ 1,30,30,30,30,30,30,30,30,30, 1,30,30,30,30, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
								{ 1,30, 1,30,30,30,30,30,30,30,30,30,30,30,30, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
								{ 1, 1,30,30,30,30,30,30,30, 1,30,30,30,30,30, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
								{ 1,30,30,30,30,30,30,30,30,30,30,30,30,30, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
								{ 1,30,30,30,30,30,30, 1,30,30,30,30,30,30,30, 0, 0,80, 1, 1, 1, 1, 1, 1, 1 },
								{ 1,30,30, 1,30,30,30,30,30,30,30,30,30,30,30, 0, 0,13, 1, 1, 1, 1, 1, 1, 1 },
								{ 1,30,30,30,30,30,30,30,30,30,30,30,30,30, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
								{ 1,30,30,30,30,30,30,30, 1,30,30,30,30,30,30, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1 },
								{ 1,30,30,30,30,30, 1,30,30,30, 1,30,30,30,30, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1 },
								{ 1,30,30,30,30,30,30,30,30,30,30,30,30,30,30, 1, 0, 0,20, 0, 0, 0,92, 1, 1 },
								{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1 },
								{ 1, 1,12, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1 },
								{ 1, 1, 0, 0, 0,22, 0, 0, 0, 0,12, 0,23, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1 },
								{ 1, 1,13, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1 },
								{ 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1 },
								{ 1, 1, 1, 1, 1, 1, 1,22, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
								{ 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
								{ 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
								{ 1, 0,80, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
								{ 1, 0, 0, 0, 0,23, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
								{ 1, 0,10, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
								{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };
		   return levelMap;
	   }
	   
	   public int[] getStart() {
		   int[] startPosition = { 16, 16 };
		   return startPosition;
	   }
	   
	   public Level nextLevel() {
		   return new LevelOne();
	   }
}