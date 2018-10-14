package edu.nd.se2018.homework.chipschallenge.entities;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import edu.nd.se2018.homework.chipschallenge.Map;
import edu.nd.se2018.homework.chipschallenge.Chip;

// Randall Krueger
// 9/16/18
// Chip collects keys

public class Key implements Entity, Observer {
	int x;
	int y;
	int color;
	Map map;

	public Key(int x, int y, int color, Map map) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.map = map;
	}

	// The key will add itself to Chip's keyring under the appropriate color.
	@Override
	public void touched(Chip chip) {
		chip.addKey(color);
		chip.deleteObserver(this);
		map.emptyCell(x, y);
	}

	// When Chip moves, the key will act if it occupies the same cell as Chip.
	@Override
	public void update(Observable o, Object arg) {
		Point chipLocation = ((Chip) o).getChipLocation();
		if (chipLocation.x == x && chipLocation.y == y) {
			this.touched((Chip) o);
		}
	};
}
