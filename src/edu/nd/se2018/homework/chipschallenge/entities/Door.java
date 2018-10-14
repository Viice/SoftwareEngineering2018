package edu.nd.se2018.homework.chipschallenge.entities;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import edu.nd.se2018.homework.chipschallenge.Map;
import edu.nd.se2018.homework.chipschallenge.Chip;

// Randall Krueger
// 10/12/18
// Door blocks chip unless he has the right key.

public class Door implements Entity, Observer {
	int x;
	int y;
	int color;
	Map map;

	public Door(int x, int y, int color, Map map) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.map = map;
	}

	// When Chip touches the door, he will return in the opposite direction unless
	// he has the key and removes the door.
	@Override
	public void touched(Chip chip) {
		if (chip.getKey(color)) {
			System.out.println("Opened door.");
			chip.deleteObserver(this);
			map.emptyCell(x, y);
		} else {

			System.out.println("Need a key.");
			switch (chip.getPreviousDirection()) {
			case 0:
				chip.goSouth();
				break;
			case 1:
				chip.goWest();
				break;
			case 2:
				chip.goNorth();
				break;
			case 3:
				chip.goEast();
				break;
			}
		}
	}

	// When Chip moves, the door will act if it occupies the same cell as Chip.
	@Override
	public void update(Observable o, Object arg) {
		Point chipLocation = ((Chip) o).getChipLocation();
		if (chipLocation.x == x && chipLocation.y == y) {
			this.touched((Chip) o);
		}
	};
}
