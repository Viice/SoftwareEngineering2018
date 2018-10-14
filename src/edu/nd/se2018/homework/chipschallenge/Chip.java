package edu.nd.se2018.homework.chipschallenge;

import java.awt.Point;
import java.util.Observable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// Randall Krueger
// 10/12/18
// Chip uses the singleton pattern, represents the player.

public class Chip extends Observable {
	private static Chip chip = new Chip();
	int xCell;
	int yCell;
	Map map;
	int dimensions;
	int[] keyRing = { 0, 0, 0, 0 };
	int previousDirection;
	Image chipImage1;
	Image chipImage2;
	Image chipImage3;
	Image chipImage4;
	ImageView chipImageView;
	int scale;
	int tokens = 0;

	// As chip is a singleton, his constructor is empty.
	private Chip() {
	}

	public int getKeyRing(int color) {
		return keyRing[color];
	}

	// Chip resets his keys when he reaches the next level.
	public void reset() {
		tokens = 0;
		this.keyRing = new int[] { 0, 0, 0, 0 };
	}

	public void setX(int x) {
		xCell = x;
	}

	public void setY(int y) {
		yCell = y;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public void setMap(Map map) {
		this.map = map;
		dimensions = map.dimensions;
	}

	// Allows the singleton pattern to give a reference to Chip.
	public static Chip getChip() {
		return chip;
	}

	public void addKey(int color) {
		this.keyRing[color] += 1;
		System.out.println("Got a key!");
	}

	// Removes a key from Chip's key ring.
	public boolean getKey(int color) {
		if (this.keyRing[color] > 0) {
			this.keyRing[color]--;
			return true;
		}
		return false;
	}

	public void addToken() {
		this.tokens++;
	}

	public int getTokens() {
		return this.tokens;
	}

	// The following four methods will move Columbus to an unoccupied location and
	// alert observers of this movement.
	// Each is for a different direction and follow the same formula.
	public void goEast() {
		// If the desired square is on the board...
		if (xCell < dimensions - 1) {
			// And is not occupied by a pirate or an island...
			if (map.grid[xCell + 1][yCell] != 1) {
				// Increment Columbus' location...
				++xCell;
			}
		}
		// Set chip's image to face the right direction.
		chipImageView.setImage(chipImage2);
		// Allows ice to keep Chip going straight and doors to repel Chip.
		previousDirection = 1;
		// And notify observers!
		setChanged();
		notifyObservers();
	}

	public void goWest() {
		if (xCell > 0) {
			if (map.grid[xCell - 1][yCell] != 1) {
				--xCell;
			}
		}
		chipImageView.setImage(chipImage4);
		previousDirection = 3;
		setChanged();
		notifyObservers();
	}

	public void goSouth() {
		if (yCell < dimensions - 1) {
			if (map.grid[xCell][yCell + 1] != 1) {
				++yCell;
			}
		}
		chipImageView.setImage(chipImage3);
		previousDirection = 2;
		setChanged();
		notifyObservers();
	}

	public void goNorth() {
		if (yCell > 0) {
			if (map.grid[xCell][yCell - 1] != 1) {
				--yCell;
			}
		}
		chipImageView.setImage(chipImage1);
		previousDirection = 0;
		setChanged();
		notifyObservers();
	}

	// Loads images for Columbus and the pirates.
	public void loadImages() {

		chipImage1 = new Image("/images/chip1.png", scale, scale, true, true);
		chipImage2 = new Image("/images/chip2.png", scale, scale, true, true);
		chipImage3 = new Image("/images/chip3.png", scale, scale, true, true);
		chipImage4 = new Image("/images/chip4.png", scale, scale, true, true);
		chipImageView = new ImageView(chipImage1);
	}

	public ImageView getImageView() {
		return chipImageView;
	}

	// Returns Chip' location.
	public Point getChipLocation() {
		return new Point(xCell, yCell);
	}

	public int getPreviousDirection() {
		return previousDirection;
	}
}
