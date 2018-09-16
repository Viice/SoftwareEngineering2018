package edu.nd.se2018.homework.ColumbusGame;

import java.awt.Point;
import java.util.Observable;

// Randall Krueger
// 9/16/18
// Ship determines Columbus' behavior.

public class Ship extends Observable {
	int xCell;
	int yCell;
	OceanMap map;

	// Given a starting location, places Columbus' ship there.
	public Ship(int x, int y, OceanMap map) {
		xCell = x;
		yCell = y;
		this.map = map; // We need a reference to it so we can access the grids!
	}

	// The following four methods will move Columbus to an unoccupied location and
	// alert observers of this movement.
	// Each is for a different direction and follow the same formula.
	public void goEast() {
		// If the desired square is on the board...
		if (xCell < 9) {
			// And is not occupied by a pirate or an island...
			if (map.oceanGrid[xCell + 1][yCell] == 0) {
				// Increment Columbus' location...
				++xCell;
			}
		}
		// And notify observers!
		setChanged();
		notifyObservers();
	}

	public void goWest() {
		if (xCell > 0) {
			if (map.oceanGrid[xCell - 1][yCell] == 0) {
				--xCell;
			}
		}
		setChanged();
		notifyObservers();
	}

	public void goSouth() {
		if (yCell < 9) {
			if (map.oceanGrid[xCell][yCell + 1] == 0) {
				++yCell;
			}
		}
		setChanged();
		notifyObservers();
	}

	public void goNorth() {
		if (yCell > 0) {
			if (map.oceanGrid[xCell][yCell - 1] == 0) {
				--yCell;
			}
		}
		setChanged();
		notifyObservers();
	}

	// Returns Columbus' location.
	public Point getShipLocation() {
		return new Point(xCell, yCell);
	}
}
