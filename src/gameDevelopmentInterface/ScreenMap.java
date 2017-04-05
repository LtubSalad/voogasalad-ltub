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
	private int NUM_ROWS = 10;
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
		Integer colNum = 0;
		Integer rowNum = 0;
		int currCol = 0;
		int currRow = 0;
		Bounds boundsInScreen = myGrid.localToScreen(myGrid.getBoundsInLocal());
		for (int i = 0; i < myGrid.getWidth(); i += myGrid.getWidth()/NUM_COLS) {
			double xOffset = boundsInScreen.getMinX();
			//System.out.println("xOffset: "+ xOffset);
			double lowerBounds = i + xOffset;
			double upperBounds = lowerBounds + myGrid.getWidth()/NUM_COLS;
			if (lowerBounds <= x && x < upperBounds) {
				colNum = currCol;
			}
			currCol += 1;
		}
		for (int i = 0; i < myGrid.getHeight(); i += myGrid.getHeight()/NUM_ROWS) {
			double yOffset = boundsInScreen.getMinY();
			double lowerBounds = i + yOffset;
			double upperBounds = lowerBounds + myGrid.getHeight()/NUM_ROWS;
			if (lowerBounds <= y && y < upperBounds) {
				rowNum = currRow;
			}
			currRow += 1;
		}
		return new Pair<Integer, Integer>(colNum, rowNum);
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
