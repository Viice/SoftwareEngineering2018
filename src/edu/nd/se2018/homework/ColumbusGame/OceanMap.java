package edu.nd.se2018.homework.ColumbusGame;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

// Randall Krueger
// 9/16/18
// OceanMap contains a grid determining what is in each cell and paints the map on the scene.

public class OceanMap {
	int[][] oceanGrid = new int[25][25];
	final int dimensions = 10;

	// Sets colors for cells and draws the map on the scene.
	public void drawMap(ObservableList<Node> root, int scale, ArrayList<Integer> islandList) {
		for (int x = 0; x < dimensions; ++x) {
			for (int y = 0; y < dimensions; ++y) {
				Rectangle rect = new Rectangle(x * scale, y * scale, scale, scale);

				// If the square is in the first 10 numbers of islandList, mark it as an island
				// (oceanGrid = 1).
				for (int i = 0; i < 10; ++i) {
					if (10 * y + x == islandList.get(i)) {
						oceanGrid[x][y] = 1;
					}
				}

				// All cells marked as islands are colored green, others are colored blue and
				// set to ocean (oceanGrid = 0).
				if (oceanGrid[x][y] == 1) {
					rect.setStroke(Color.BLACK);
					rect.setFill(Color.GREEN);

				} else {
					rect.setStroke(Color.BLACK);
					rect.setFill(Color.PALETURQUOISE);
					oceanGrid[x][y] = 0;
				}

				// Adds all rectangles.
				root.add((Node) rect);
			}
		}
	}
}
