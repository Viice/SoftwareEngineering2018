package edu.nd.se2018.homework.ColumbusGame;

import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

// Randall Krueger
// 9/16/18
// OceanExplorer - main class for Columbus game, sets everything up and has main run loop.

public class OceanExplorer extends Application {

	final private int scale = 50;
	final private int numberOfCells = 10;

	Scene scene;

	Image shipImage;
	ImageView shipImageView;
	Image pirateImage;
	ImageView pirateImageView1;
	ImageView pirateImageView2;

	Ship ship;
	Pirate pirate1;
	Pirate pirate2;
	OceanMap map;
	Pane root;
	ArrayList<Integer> islandList;
	boolean gameOver = false;

	@Override
	public void start(Stage stage) throws Exception {

		// Randomly assigns unique locations for islands, Columbus, and the pirates by
		// shuffling 100 numbers, and taking the first 12.
		islandList = new ArrayList<Integer>();
		for (int i = 0; i < 100; ++i) {
			islandList.add(new Integer(i));
		}
		Collections.shuffle(islandList);

		// Initializes map and pane.
		map = new OceanMap();
		root = new Pane();

		// Draws map, assigns islands.
		map.drawMap(root.getChildren(), scale, islandList);

		// Places Columbus and the pirates in the scene.
		int startPosition = islandList.get(10);
		ship = new Ship(startPosition % 10, startPosition / 10, map);

		startPosition = islandList.get(11);
		pirate1 = new Pirate(startPosition % 10, startPosition / 10, map);

		startPosition = islandList.get(12);
		pirate2 = new Pirate(startPosition % 10, startPosition / 10, map);

		// Loads ship images.
		loadImages();

		// Starts scene
		scene = new Scene(root, numberOfCells * scale, numberOfCells * scale);
		stage.setTitle("Columbus Game");
		stage.setScene(scene);
		stage.show();

		// Adds observers and starts the game.
		ship.addObserver(pirate1);
		ship.addObserver(pirate2);
		startSailing(scene);
	}

	// Handles movmeent.
	private void startSailing(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent ke) {
				// If the player has already lost, don't allow any more movement.
				if (!gameOver) {
					switch (ke.getCode()) {
					case RIGHT:
						ship.goEast();
						break;
					case LEFT:
						ship.goWest();
						break;
					case UP:
						ship.goNorth();
						break;
					case DOWN:
						ship.goSouth();
						break;
					default:
						break;
					}

					// After each movement, update the images in the scene.
					shipImageView.setX(ship.getShipLocation().x * scale);
					shipImageView.setY(ship.getShipLocation().y * scale);

					pirateImageView1.setX(pirate1.getPirateLocation().x * scale);
					pirateImageView1.setY(pirate1.getPirateLocation().y * scale);

					pirateImageView2.setX(pirate2.getPirateLocation().x * scale);
					pirateImageView2.setY(pirate2.getPirateLocation().y * scale);

					// If Columbus and a pirate occupy the same space, the player lost the game.
					if (pirate1.getPirateLocation().x == ship.getShipLocation().x
							&& pirate1.getPirateLocation().y == ship.getShipLocation().y) {
						System.out.println("You lose!");
						gameOver = true;
					}

					if (pirate2.getPirateLocation().x == ship.getShipLocation().x
							&& pirate2.getPirateLocation().y == ship.getShipLocation().y) {
						System.out.println("You lose!");
						gameOver = true;
					}
				}
			}
		});
	}

	// Loads images for Columbus and the pirates.
	private void loadImages() {

		shipImage = new Image("/images/ColumbusShip.png", scale, scale, true, true);
		shipImageView = new ImageView(shipImage);
		shipImageView.setX(ship.getShipLocation().x * scale);
		shipImageView.setY(ship.getShipLocation().y * scale);

		pirateImage = new Image("/images/pirateship.gif", scale, scale, true, true);
		pirateImageView1 = new ImageView(pirateImage);
		pirateImageView1.setX(pirate1.getPirateLocation().x * scale);
		pirateImageView1.setY(pirate1.getPirateLocation().y * scale);

		pirateImageView2 = new ImageView(pirateImage);
		pirateImageView2.setX(pirate2.getPirateLocation().x * scale);
		pirateImageView2.setY(pirate2.getPirateLocation().y * scale);

		// Adds all three ships to the scene.
		root.getChildren().add(shipImageView);
		root.getChildren().add(pirateImageView1);
		root.getChildren().add(pirateImageView2);
	}

	// Launches game.
	public static void main(String[] args) {
		launch(args);
	}

}
