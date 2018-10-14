package edu.nd.se2018.homework.chipschallenge;

import java.util.ArrayList;
import java.util.Collections;

import edu.nd.se2018.homework.chipschallenge.entities.Entity;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

// Randall Krueger
// 10/12/18
// GameEngine - main class for Chip game, sets everything up and has main run loop.

public class GameEngine extends Application {

	final private int scale = 50;
	Scene scene;
	Chip chip;
	Map map;
	Pane root;
	int dimensions;
	ArrayList<Entity> entityList = new ArrayList<Entity>();
	Level level;

	@Override
	public void start(Stage stage) throws Exception {
		dimensions = 25;

		// Initializes the first level.
		level = new LevelOne();
		int[][] levelMap = level.getMap();
		int[] startPosition = level.getStart();

		// Initializes map and pane.
		map = new Map(this);
		root = new Pane();

		// Draws map.
		map.drawMap(dimensions, root.getChildren(), scale, levelMap, entityList, root);

		// Places Chip
		chip = Chip.getChip();
		chip.setX(startPosition[0]);
		chip.setY(startPosition[1]);
		;
		chip.setMap(map);
		chip.setScale(scale);
		chip.loadImages();

		map.setChip(chip);

		for (int entityIndex = 0; entityIndex < entityList.size(); ++entityIndex) {
			chip.addObserver(entityList.get(entityIndex));
		}

		// Loads chip images.
		root.getChildren().add(chip.getImageView());

		// Starts scene
		scene = new Scene(root, 11 * scale, 11 * scale);
		stage.setTitle("Chip's Challenge");
		stage.setScene(scene);
		stage.show();

		// Adds observers and starts the game.
		map.redraw(chip.getChipLocation(), chip.getImageView());
		startChipping(scene);
	}

	// Handles movmeent.
	private void startChipping(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent ke) {
					switch (ke.getCode()) {
					case RIGHT:
						chip.goEast();
						break;
					case LEFT:
						chip.goWest();
						break;
					case UP:
						chip.goNorth();
						break;
					case DOWN:
						chip.goSouth();
						break;
					case ESCAPE:
						System.exit(0);
					default:
						break;
					}

					map.redraw(chip.getChipLocation(), chip.getImageView());
					chip.getImageView().toFront();
			}
		});
	}

	public void nextLevel() {
		dimensions = 25;

		// Uses the state pattern to move to the next level
		level = level.nextLevel();

		int[][] levelMap = level.getMap();

		int[] startPosition = level.getStart();

		// Removes old entities.
		for (int entityIndex = 0; entityIndex < entityList.size(); ++entityIndex) {
			chip.deleteObserver(entityList.get(entityIndex));
		}

		entityList = new ArrayList<Entity>();

		// Initializes new map.
		map = new Map(this);

		// Draws map.
		map.drawMap(dimensions, root.getChildren(), scale, levelMap, entityList, root);

		// Resets Chip for the next level
		chip.reset();
		chip.setX(startPosition[0]);
		chip.setY(startPosition[1]);
		;
		chip.setMap(map);
		chip.setScale(scale);
		chip.loadImages();

		map.setChip(chip);

		for (int entityIndex = 0; entityIndex < entityList.size(); ++entityIndex) {
			chip.addObserver(entityList.get(entityIndex));
		}

		root.getChildren().add(chip.getImageView());
		map.redraw(chip.getChipLocation(), chip.getImageView());
		startChipping(scene);
	}

	public static void main(String[] args) {
		launch();
	}

}
