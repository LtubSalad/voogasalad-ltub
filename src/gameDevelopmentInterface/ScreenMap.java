package gameDevelopmentInterface;

import data.AttributeData;
import engine.camera.GamePoint;
import javafx.collections.ListChangeListener;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class will hold the grid on which players can place sprites or tiles to set up their
 * gaming environment. 
 * @author Jake
 *
 */

public class ScreenMap extends StackPane {
	private static final String Y_POSITION = "yPosition";
	private static final String X_POSITION = "xPosition";
	private static final String IMAGE2 = "image";
	private static final String IMAGE_HOLDER = "ImageHolder";
	private static final int SCREEN_SIZE = 350;
	private GridPane myGrid;
	private ScreenModelCreator mySMC;
	private static final String PATH_TO_IMAGE_FILES = "images/characters/";
	private int NUM_ROWS = 10;
	private int NUM_COLS = 8;
	
	public ScreenMap(ScreenModelCreator smc) {
		mySMC = smc;
		mySMC.getScreenData().getAllObjectsOnScreen().addListener(new ListChangeListener<AttributeData>() {
			@Override
			public void onChanged(@SuppressWarnings("rawtypes") ListChangeListener.Change change) {
				redrawGrid();
			}
		});
		this.setHeight(SCREEN_SIZE);
		this.setWidth(SCREEN_SIZE);
		makeGrid();
	}
	
	public void setNumRows(int numRows) {
		NUM_ROWS = numRows;
		makeGrid();
	}
	
	public void setNumCols(int numCols) {
		NUM_COLS = numCols;
		makeGrid();
	}
	
	public int getNumRows() {
		return NUM_ROWS;
	}
	public int getNumCols() {
		return NUM_COLS;
	}
	
	public GridPane getGrid() {
		return myGrid;
	}
	
	public GamePoint getCoordOfMouseHover(double x, double y) {
		Bounds boundsInScreen = myGrid.localToScreen(myGrid.getBoundsInLocal());
		int colNum = getColOrRowPlacement(boundsInScreen.getMinX(), myGrid.getWidth(), myGrid.getWidth()/NUM_COLS, x, boundsInScreen);
		int rowNum = getColOrRowPlacement(boundsInScreen.getMinY(), myGrid.getHeight(), myGrid.getHeight()/NUM_ROWS, y, boundsInScreen);
		return new GamePoint(colNum, rowNum);
	}
	
	private void redrawGrid() {
		for (AttributeData attr : mySMC.getScreenData().getAllObjectsOnScreen()) {
			Double xPosDub = Double.parseDouble(attr.getVariable(X_POSITION));
			Double yPosDub = Double.parseDouble(attr.getVariable(Y_POSITION));
			Integer xPos = xPosDub.intValue();
			Integer yPos = yPosDub.intValue();
			attr.getAttributes().forEach(att -> {
				if (att.getName().equals(IMAGE_HOLDER)) {
					String imageName = attr.getVariable(IMAGE2);
					Image image = new Image(getClass().getClassLoader().getResourceAsStream(PATH_TO_IMAGE_FILES + imageName),
							SCREEN_SIZE/NUM_COLS, SCREEN_SIZE/NUM_ROWS, false, false);
					ImageView imageView = new ImageView(image);
					myGrid.add(imageView, xPos, yPos);
				}
			});
		}
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
		myGrid.setMaxHeight(SCREEN_SIZE);
		myGrid.setMaxWidth(SCREEN_SIZE);
		for (int i = 0; i < NUM_ROWS; i++) {
			RowConstraints row = new RowConstraints(SCREEN_SIZE/NUM_ROWS);
			myGrid.getRowConstraints().add(row);
		}
		for (int j = 0; j < NUM_COLS; j++) {
			ColumnConstraints col = new ColumnConstraints(SCREEN_SIZE/NUM_COLS);
			myGrid.getColumnConstraints().add(col);
		}
		for (int i = 0; i < NUM_ROWS; i++) {
			for (int j = 0; j < NUM_COLS; j++) {
				myGrid.add(new Rectangle(SCREEN_SIZE/NUM_COLS, SCREEN_SIZE/NUM_ROWS, Color.WHITESMOKE), j, i);
			}
		}
		this.getChildren().clear();
		this.getChildren().add(myGrid);
	}

}