package edu.nd.se2018.homework.chipschallenge.entities;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import edu.nd.se2018.homework.chipschallenge.Map;
import edu.nd.se2018.homework.chipschallenge.Chip;

// Randall Krueger
// 10/12/18
// Exit key represents the keys Chip needs to leave the level.

public class ExitKey implements Entity, Observer {
	int x;
	int y;
	Map map;

	public ExitKey(int x, int y, Map map) {
		this.x = x;
		this.y = y;
		this.map = map;
	}

	// Chip collects these keys.
	@Override
	public void touched(Chip chip) {
		System.out.println("Got a chip!");
		chip.addToken();
		chip.deleteObserver(this);
		map.emptyCell(x, y);
	}

	// When Chip moves, the exit key will act if it occupies the same cell as Chip.
	@Override
	public void update(Observable o, Object arg) {
		Point chipLocation = ((Chip) o).getChipLocation();
		if (chipLocation.x == x && chipLocation.y == y) {
			this.touched((Chip) o);
		}
	};
}
