package edu.nd.se2018.homework.ColumbusGame;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import edu.nd.se2018.homework.ColumbusGame.Ship;

// Randall Krueger
// 9/16/18
// Pirate implements a pirate's ship and its behavior.

public class Pirate implements Observer {
	int xCell;
	int yCell;
	OceanMap map;

	public Pirate(int x, int y, OceanMap map) {
		xCell = x;
		yCell = y;
		this.map = map; // We need a reference to it so we can access the grids!
		// Sets the initial square of the ocean grid to 2, so other ships know it is
		// occupied.
		map.oceanGrid[xCell][yCell] = 2;
	}

	// The following four methods will move the pirate to an unoccupied location.
	// Each is for a different direction and follow the same formula.
	public boolean goEast() {
		// If the cell is on the board...
		if (xCell < 9) {
			// And the cell is not occupied...
			if (map.oceanGrid[xCell + 1][yCell] == 0) {
				// Set the current cell to 'ocean' and the cell we are moving to to 'pirate'...
				map.oceanGrid[xCell + 1][yCell] = 2;
				map.oceanGrid[xCell][yCell] = 0;
				// Move the ship...
				++xCell;
				// And return true, informing the move method that we were successful.
				return true;
			}
		}
		// If we are unsuccessful, return false.
		return false;
	}

	public boolean goWest() {
		if (xCell > 0) {
			if (map.oceanGrid[xCell - 1][yCell] == 0) {
				map.oceanGrid[xCell - 1][yCell] = 2;
				map.oceanGrid[xCell][yCell] = 0;
				--xCell;
				return true;
			}
		}
		return false;
	}

	public boolean goSouth() {
		if (yCell < 9) {
			if (map.oceanGrid[xCell][yCell + 1] == 0) {
				map.oceanGrid[xCell][yCell + 1] = 2;
				map.oceanGrid[xCell][yCell] = 0;
				++yCell;
				return true;
			}
		}
		return false;
	}

	public boolean goNorth() {
		if (yCell > 0) {
			if (map.oceanGrid[xCell][yCell - 1] == 0) {
				map.oceanGrid[xCell][yCell - 1] = 2;
				map.oceanGrid[xCell][yCell] = 0;
				--yCell;
				return true;
			}
		}
		return false;
	}

	// Move towards Columbus' location.
	public void movePirate(Point columbusLocation) {

		// If the pirate is farther away in the x direction, it will attempt to move in
		// this direction first, otherwise it will try to move in the y direction first.
		if (Math.abs(xCell - columbusLocation.x) > Math.abs(yCell - columbusLocation.y)) {
			// If the pirate is east of columbus...
			if (xCell - columbusLocation.x > 0) {
				// It will try to move west. If it succeeds, it will return, otherwise it will
				// attempt to move in another direction.
				if (goWest()) {
					return;
				}
			} else if (xCell - columbusLocation.x < 0) {
				if (goEast()) {
					return;
				}
			}
		}
		if (yCell - columbusLocation.y > 0) {
			if (goNorth()) {
				return;
			}
		} else if (yCell - columbusLocation.y < 0) {
			if (goSouth()) {
				return;
			}
		}
		if (xCell - columbusLocation.x > 0) {
			if (goWest()) {
				return;
			}
		} else if (xCell - columbusLocation.x < 0) {
			if (goEast()) {
				return;
			}
		}
	}

	// When Columbus moves, the pirate will get his location and move towards it.
	@Override
	public void update(Observable o, Object arg) {
		Point columbusLocation = ((Ship) o).getShipLocation();
		movePirate(columbusLocation);
	}

	// Returns the pirate's location.
	public Point getPirateLocation() {
		return new Point(xCell, yCell);
	}
}
