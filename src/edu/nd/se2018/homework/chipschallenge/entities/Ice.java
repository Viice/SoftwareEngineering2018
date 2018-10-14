package edu.nd.se2018.homework.chipschallenge.entities;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import edu.nd.se2018.homework.chipschallenge.Map;
import edu.nd.se2018.homework.chipschallenge.Chip;

// Randall Krueger
// 10/12/18
// Ice slides Chip until he reaches a wall.

public class Ice implements Entity, Observer {
	int x;
	int y;
	Map map;

	public Ice(int x, int y, Map map) {
		this.x = x;
		this.y = y;
		this.map = map;
	}

	// Chip will continue to move along ice until he hits a wall or open floor.
	@Override
	public void touched(Chip chip) {
		switch (chip.getPreviousDirection()) {
		case 0:
			if (map.getGrid()[x][y - 1] != 1) {
				chip.goNorth();
			}
			break;
		case 1:
			if (map.getGrid()[x + 1][y] != 1) {
				chip.goEast();
			}
			break;
		case 2:
			if (map.getGrid()[x][y + 1] != 1) {
				chip.goSouth();
			}
			break;
		case 3:
			if (map.getGrid()[x - 1][y] != 1) {
				chip.goWest();
			}
			break;

		}
	}

	// When Chip moves, the ice will act if it occupies the same cell as Chip.
	@Override
	public void update(Observable o, Object arg) {
		Point chipLocation = ((Chip) o).getChipLocation();
		if (chipLocation.x == x && chipLocation.y == y) {
			this.touched((Chip) o);
		}
	};
}
