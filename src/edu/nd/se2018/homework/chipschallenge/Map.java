package edu.nd.se2018.homework.chipschallenge;

import java.util.ArrayList;

import edu.nd.se2018.homework.chipschallenge.entities.Door;
import edu.nd.se2018.homework.chipschallenge.entities.Entity;
import edu.nd.se2018.homework.chipschallenge.entities.Exit;
import edu.nd.se2018.homework.chipschallenge.entities.ExitKey;
import edu.nd.se2018.homework.chipschallenge.entities.Ice;
import edu.nd.se2018.homework.chipschallenge.entities.Key;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.awt.Point;
import java.lang.Math;

// Randall Krueger
// 10/12/18
// Map contains a grid determining what is in each cell and paints the map on the scene.

public class Map {
	int[][] grid;
	int dimensions;
	Rectangle[][] rectangleGrid;
	Image[][] entityImageGrid;
	ImageView[][] entityImageViewGrid;
	ArrayList<ImageView> keys = new ArrayList<ImageView>();
	int scale;
	Chip chip;
	Pane pane;
	GameEngine game;

	public Map(GameEngine game) {
		this.game = game;
	}

	public int[][] getGrid() {
		return grid;
	}

	public void redraw(Point chipLocation, ImageView chipImageView) {
		int dispX = Math.min(Math.max(chipLocation.x, 5), dimensions - 6) - 5;
		int dispY = Math.min(Math.max(chipLocation.y, 5), dimensions - 6) - 5;
		for (int x = 0; x < dimensions; ++x) {
			for (int y = 0; y < dimensions; ++y) {
				rectangleGrid[x][y].setX(x * scale - dispX * scale);
				rectangleGrid[x][y].setY(y * scale - dispY * scale);
			}
		}
		chipImageView.setX(rectangleGrid[chipLocation.x][0].getX());
		chipImageView.setY(rectangleGrid[0][chipLocation.y].getY());

		// Paints all entities in the appropriate cells.

		for (int x = 0; x < dimensions; ++x) {
			for (int y = 0; y < dimensions; ++y) {
				if (entityImageViewGrid[x][y] != null) {

					entityImageViewGrid[x][y].setX(rectangleGrid[x][0].getX());
					entityImageViewGrid[x][y].setY(rectangleGrid[0][y].getY());
					entityImageViewGrid[x][y].toFront();
					if (grid[x][y] == 0) {
						entityImageViewGrid[x][y].setX(-500);
						entityImageViewGrid[x][y].setY(-500);

					}
				}
			}
		}

		for (int i = 0; i < keys.size(); i++) {
			keys.get(i).setImage(null);
		}

		// Paints held keys in the upper left of the screen.
		keys.clear();

		for (int x = 0; x < 4; ++x) {
			for (int y = 0; y < chip.getKeyRing(x); ++y) {
				Image keyImage = new Image("/images/1" + x + ".png", 30, 30, true, true);
				ImageView keyView = new ImageView(keyImage);
				keyView.setX(x * 32);
				keyView.setY(y * 32);
				pane.getChildren().add(keyView);
				keys.add(keyView);
			}
		}
	}

	public void emptyCell(int x, int y) {
		grid[x][y] = 0;
		rectangleGrid[x][y].setFill(Color.rgb(234, 215, 253));
	}

	// Adds the right type of entity for each number.
	public Entity createEntity(int x, int y, int type) {
		Entity entity;
		// Uses the map valye to assign image.
		Image image = new Image("/images/" + type + ".png", scale, scale, true, true);
		ImageView imageView = new ImageView(image);
		imageView.setX(rectangleGrid[x][0].getX());
		imageView.setY(rectangleGrid[0][y].getY());
		entityImageViewGrid[x][y] = imageView;
		pane.getChildren().add(imageView);
		switch (type / 10) {
		case 1:
			entity = new Key(x, y, type % 10, this);
			return entity;
		case 2:
			entity = new Door(x, y, type % 10, this);
			return entity;
		case 3:
			entity = new Ice(x, y, this);
			return entity;
		case 8:
			entity = new ExitKey(x, y, this);
			return entity;
		case 9:
			entity = new Exit(x, y, type % 10, this);
			return entity;
		}
		return null;
	}

	public Pane getPane() {
		return pane;
	}

	public void setChip(Chip chip) {
		this.chip = chip;
	}

	public void exit() {
		game.nextLevel();
	}

	// Sets colors for cells and draws the map on the scene.
	public void drawMap(int gridSize, ObservableList<Node> root, int scale, int[][] levelMap,
			ArrayList<Entity> entityList, Pane pane) {
		dimensions = gridSize;
		this.scale = scale;
		grid = levelMap;
		this.pane = pane;
		rectangleGrid = new Rectangle[dimensions][dimensions];
		entityImageGrid = new Image[dimensions][dimensions];
		entityImageViewGrid = new ImageView[dimensions][dimensions];
		for (int x = 0; x < dimensions; ++x) {
			for (int y = 0; y < dimensions; ++y) {
				Rectangle rect = new Rectangle(x * scale, y * scale, scale, scale);

				// All cells marked as walls are colored dark purple, otherwise if not empty,
				// adds the appropriate entity.
				rect.setStroke(Color.BLACK);
				rect.setFill(Color.rgb(234, 215, 253));
				if (grid[x][y] == 1) {
					rect.setFill(Color.rgb(99, 73, 118));
				} else if (grid[x][y] != 0) {
					entityList.add(createEntity(x, y, grid[x][y]));
				}

				rectangleGrid[x][y] = rect;
				// Adds all rectangles.
				root.add((Node) rect);
			}
		}
	}
}
