package edu.nd.se2018.homework.Homework5.model.vehicles;

import java.util.Observable;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the train entity object
 * 
 * @author jane Improved by Randy Krueger 9/19/18
 *
 */
public class Train extends Observable implements IVehicle {
	private double currentX = 0;
	private double currentY = 0;
	private double originalX = 0;
	private Image img;
	private ImageView imgView;
	private int trainLength = 35;
	private int movement = -2;
	private Train partner;

	public Train(int x, int y, String imageURL, int movement) {
		this.currentX = x;
		this.currentY = y;
		originalX = x;
		this.movement = movement;
		img = new Image(imageURL, 120, trainLength, false, false);
		imgView = new ImageView(img);
		imgView.setX(currentX);
		imgView.setY(currentY);
	}

	public double getVehicleX() {
		return currentX;
	}

	public double getVehicleY() {
		return currentY;
	}

	public void move() {
		// Movement variable allows for multiple trains going in different directions.
		currentX += movement;
		imgView.setX(currentX);
		setChanged();
		notifyObservers();
	}

	// Check both sides of screen.
	public boolean offScreen() {
		if (currentX < -200 || currentX > 1400)
			return true;
		else
			return false;
	}

	public void reset() {
		currentX = originalX;
	}

	public void setPartner(Train partner) {
		this.partner = partner;
	}

	public int getMovement() {
		return this.movement;
	}

	// Partner must be referenced for gates to access both trains at once.
	public Train getPartner() {
		return this.partner;
	}

	// @Override
	public Node getImageView() {
		return imgView;
	}
}