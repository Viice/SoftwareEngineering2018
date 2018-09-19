package edu.nd.se2018.homework.Homework5.model.vehicles;

import java.util.Observable;
import java.util.Observer;

import edu.nd.se2018.homework.Homework5.model.infrastructure.Road;
import edu.nd.se2018.homework.Homework5.model.infrastructure.gate.CrossingGate;
import edu.nd.se2018.homework.Homework5.view.CarImageSelector;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

/**
 * Represents Car object
 * 
 * @author jane Improved by Randy Krueger 9/19/18
 */
public class Car extends Observable implements IVehicle, Observer {
	private ImageView ivCar;
	private double currentX = 0;
	private double currentY = 0;
	private double originalY = 0;
	private boolean gateDown = false;
	private Car leadCar = null;
	private double speed = 0.5;
	private boolean goingWest = false;
	private Road road;
	private CarFactory factory;
	private boolean turning = false;

	/**
	 * Constructor
	 * 
	 * @param x initial x coordinate of car
	 * @param y initial y coordinate of car
	 */
	public Car(int x, int y, Road road, CarFactory factory) {
		this.currentX = x;
		this.currentY = y;
		originalY = y;
		ivCar = new ImageView(CarImageSelector.getImage());
		ivCar.setX(getVehicleX());
		ivCar.setY(getVehicleY());

		// Car needs references to the road and factory that created it to make turns
		// properly.
		this.road = road;
		this.factory = factory;
	}

	@Override
	public Node getImageView() {
		return ivCar;
	}

	public boolean gateIsClosed() {
		return gateDown;
	}

	public double getVehicleX() {
		return currentX;
	}

	public double getVehicleY() {
		return currentY;
	}

	// When car decides to take the western path, it needs to set goingWest to true
	// for movement.
	public void goWest() {
		// lastCar refers to the last car to turn. This sets this car's leadCar to the
		// last one to turn, and queues this car up for the next one to turn.
		leadCar = road.getLastCar();
		road.setLastCar(this);
		this.goingWest = true;
	}

	public boolean getGoingWest() {
		return this.goingWest;
	}

	public Car getLeadCar() {
		return leadCar;
	}

	public CarFactory getFactory() {
		return factory;
	}

	public void turn() {
		// turning informs move that it still needs to move west, but only so far.
		this.goingWest = false;
		this.turning = true;

		// Sets this car to observe the most recent car on the western road, and sets
		// the 'previous car' from the factory to this car for future cars to observe.
		if (factory.getPartner().getPrevious() != null)
			factory.getPartner().getPrevious().addObserver(this);
		factory.getPartner().setPrevious(this);

		// Removes the car's observance of the gate.
		for (CrossingGate gate : factory.getGates()) {
			gate.deleteObserver(this);
		}

		// Makes the car observe the other gate.
		for (CrossingGate gate : factory.getPartner().getGates()) {
			gate.addObserver(this);
			if (gate != null && gate.getTrafficCommand() == "STOP")
				this.setGateDownFlag(false);
		}
	}

	public void move() {
		boolean canMove = true;

		// If the car is in the proper spot and the random function decrees it, make the
		// car go west.
		if (!goingWest && currentX > 500 && currentY > 195 && currentY < 197
				&& (Math.floor(Math.random() * 100) % 2) == 1) {
			this.goWest();
			setChanged();
			notifyObservers();
		}

		// First case. Car is at the front of the stopping line.
		if (gateDown && getVehicleY() < 430 && getVehicleY() > 390)
			canMove = false;

		if (goingWest) {
			// While the car is going west, stop it at the next road.
			if (getVehicleX() < 430) {
				canMove = false;

				// If there are no cars coming, turn.
				if (factory.getPartner().getPrevious().getVehicleY() > 230) {
					turn();
				}
			}

			// Second case. Car is too close too other car.
			if (leadCar != null && leadCar.getGoingWest() && getDistanceToLeadCarX() < 50)
				canMove = false;

			if (canMove) {
				currentX -= speed;
				ivCar.setX(currentX);
			}
		} else {

			// Stops cars from getting stuck at end.
			if (leadCar == null || leadCar.getVehicleY() > 1000) {
				leadCar = null;
			}

			// If car is turning, animate its turn when it can move.
			if (this.turning) {
				if (this.currentX > 395) {
					currentX -= speed;
					ivCar.setX(currentX);
				} else {
					this.turning = false;
				}
			}

			// Second case. Car is too close too other car.
			if (leadCar != null && !leadCar.getGoingWest() && getDistanceToLeadCarY() < 50)
				canMove = false;

			if (canMove) {
				currentY += speed;
				ivCar.setY(currentY);
			}
		}
		setChanged();
		notifyObservers();
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setGateDownFlag(boolean gateDown) {
		this.gateDown = gateDown;
	}

	public boolean offScreen() {
		if (currentY > 1020)
			return true;
		else
			return false;
	}

	public void reset() {
		currentY = originalY;
	}

	public double getDistanceToLeadCarY() {
		return Math.abs(leadCar.getVehicleY() - getVehicleY());
	}

	public double getDistanceToLeadCarX() {
		return Math.abs(leadCar.getVehicleX() - getVehicleX());
	}

	public void removeLeadCar() {
		leadCar = null;
	}

	@Override
	public void update(Observable o, Object arg1) {
		if (o instanceof Car) {

			// Instead of using just the next car's y position, we keep track of the car
			// object.
			leadCar = ((Car) o);

			// If this car is going south and the car its observing goes west, stop
			// observing it, and observe the car in front of it.
			if (!this.goingWest) {
				if (((Car) o).getGoingWest()) {
					leadCar = ((Car) o).getLeadCar();
					o.deleteObserver(this);
				}

				if (leadCar == null || leadCar.getVehicleY() > 1000) {
					leadCar = null;
				}
			} else {

				// Likewise, if a car goes west and its lead car turns, stop observing it.
				if (!((Car) o).getGoingWest()) {
					o.deleteObserver(this);
				}
			}
		}
		if (o instanceof CrossingGate) {
			CrossingGate gate = (CrossingGate) o;
			if (gate.getTrafficCommand() == "STOP")
				gateDown = true;
			else
				gateDown = false;

		}
	}
}
