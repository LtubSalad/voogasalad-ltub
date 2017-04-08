package gameDevelopmentInterface;

import javafx.geometry.Bounds;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

public class ScreenMap extends StackPane {
	private static final int SCREEN_SIZE = 350;
	private GridPane myGrid;
	private int NUM_ROWS = 10; //make these two as parameters to the constructor, so the user can set them?
	private int NUM_COLS = 8;
	
	public ScreenMap() {
		this.setHeight(SCREEN_SIZE);
		this.setWidth(SCREEN_SIZE);
		Rectangle background = new Rectangle(SCREEN_SIZE, SCREEN_SIZE, Color.AQUA);
		makeGrid();
		this.getChildren().addAll(background, myGrid);
	}
	
	public GridPane getGrid() {
		return myGrid;
	}
	
	public int getNumRows() {
		return NUM_ROWS;
	}
	public int getNumCols() {
		return NUM_COLS;
	}
	
	public Pair<Integer, Integer> getCoordOfSpriteHover(double x, double y) {
		Bounds boundsInScreen = myGrid.localToScreen(myGrid.getBoundsInLocal());
		int colNum = getColOrRowPlacement(boundsInScreen.getMinX(), myGrid.getWidth(), myGrid.getWidth()/NUM_COLS, x, boundsInScreen);
		int rowNum = getColOrRowPlacement(boundsInScreen.getMinY(), myGrid.getHeight(), myGrid.getHeight()/NUM_ROWS, y, boundsInScreen);
		return new Pair<Integer, Integer>(colNum, rowNum);
	}

	private int getColOrRowPlacement(double offset, double bounds, double step, double x, Bounds boundsInScreen) {
		int targetRowOrCol = 0;
		int currRowOrCol = 0;
		for (int i = 0; i < bounds; i += step) {
			double lowerBounds = i + offset;
			double upperBounds = lowerBounds + step;
			if (lowerBounds <= x && x < upperBounds) {
				targetRowOrCol = currRowOrCol;
			}
			currRowOrCol += 1;
		}
		return targetRowOrCol;
	}
	
	private void makeGrid() {
		myGrid = new GridPane();
		myGrid.setMaxSize(SCREEN_SIZE, SCREEN_SIZE);
		for (int i = 0; i < NUM_ROWS; i++) {
			RowConstraints row = new RowConstraints(SCREEN_SIZE/NUM_ROWS);
			myGrid.getRowConstraints().add(row);
		}
		for (int j = 0; j < NUM_COLS; j++) {
			ColumnConstraints col = new ColumnConstraints(SCREEN_SIZE/NUM_COLS);
			myGrid.getColumnConstraints().add(col);
		}
	}

}
