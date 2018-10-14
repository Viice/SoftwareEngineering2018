package edu.nd.se2018.homework.chipschallenge.entities;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import edu.nd.se2018.homework.chipschallenge.Map;
import edu.nd.se2018.homework.chipschallenge.Chip;

// Randall Krueger
// 10/12/18
// Exit represents the cell that moves Chip to the next level.

public class Exit implements Entity, Observer {
	int x;
	int y;
	int size;
	Map map;

	public Exit(int x, int y, int size, Map map) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.map = map;
	}

	// If Chip touches the exit without all the exit keys, he will move back,
	// otherwise the game engine will exit.
	@Override
	public void touched(Chip chip) {
		if (chip.getTokens() == size) {
			System.out.println("Woohoo!");
			chip.deleteObserver(this);
			map.emptyCell(x, y);
			map.exit();
		} else {
			System.out.println("Need " + String.valueOf(size - chip.getTokens()) + " more chips.");
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

	// When Chip moves, the exit will act if it occupies the same cell as Chip.
	@Override
	public void update(Observable o, Object arg) {
		Point chipLocation = ((Chip) o).getChipLocation();
		if (chipLocation.x == x && chipLocation.y == y) {
			this.touched((Chip) o);
		}
	};
}
