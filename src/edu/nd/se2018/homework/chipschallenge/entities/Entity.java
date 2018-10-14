package edu.nd.se2018.homework.chipschallenge.entities;

import java.util.Observer;

import edu.nd.se2018.homework.chipschallenge.Chip;

// Randall Krueger
// 10/12/18
// Entity represents objects on the map.

public interface Entity extends Observer {
	public void touched(Chip chip);

}