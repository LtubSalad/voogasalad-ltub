package gameDevelopmentInterface;

import java.util.Map;
import java.util.ResourceBundle;

import data.AttributeData;
import commons.point.GamePoint;
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
import javafx.util.Pair;

/**
 * This class will hold the grid on which players can place sprites or tiles to set up their
 * gaming environment. 
 * @author Jake
 *
 */

public class ScreenMap extends StackPane {
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	private static final String Y_POSITION = "Y_POSITION";
	private static final String X_POSITION = "X_POSITION";
	private static final String IMAGE_HOLDER = "IMAGE_HOLDER";
	private static final int SCREEN_SIZE = 350;
	private GridPane myGrid;
	private ScreenModelCreator mySMC;
	private static final String PATH_TO_IMAGE_FILES = "PATH_TO_IMAGE_FILES";
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
	/**
	 * Can change number of rows on screen
	 * @param numRows
	 */
	public void setNumRows(int numRows) {
		NUM_ROWS = numRows;
		makeGrid();
	}
	/**
	 * Can change number of columns on screen
	 * @param numCols
	 */
	public void setNumCols(int numCols) {
		NUM_COLS = numCols;
		makeGrid();
	}
	/**
	 * 
	 * @return number of rows on screen
	 */
	public int getNumRows() {
		return NUM_ROWS;
	}
	/**
	 * 
	 * @return number of columns on screen
	 */
	public int getNumCols() {
		return NUM_COLS;
	}
	/**
	 * 
	 * @return the grid object
	 */
	public GridPane getGrid() {
		return myGrid;
	}
	/**
	 * 
	 * @param x the actual x position of the mouse on the screen
	 * @param y the actual y position of the mouse on the screen
	 * @return the mapped coordinate value of the (x,y) to the grid object
	 */
	public GamePoint getCoordOfMouseHover(double x, double y) {
		Bounds boundsInScreen = myGrid.localToScreen(myGrid.getBoundsInLocal());
		int colNum = getColOrRowPlacement(boundsInScreen.getMinX(), myGrid.getWidth(), myGrid.getWidth()/NUM_COLS, x, boundsInScreen);
		int rowNum = getColOrRowPlacement(boundsInScreen.getMinY(), myGrid.getHeight(), myGrid.getHeight()/NUM_ROWS, y, boundsInScreen);
		return new GamePoint(colNum, rowNum);
	}
	
	public GamePoint getActualLocationOfSprite(GamePoint gp) {
		double actualX = (gp.x()*(getGrid().getWidth()/NUM_COLS)) + ((getGrid().getWidth()/NUM_COLS)/2);
		double actualY = (gp.y()*(getGrid().getHeight()/NUM_ROWS)) + ((getGrid().getHeight()/NUM_ROWS)/2);
		return new GamePoint(actualX, actualY);
	}
	
	private void redrawGrid() {
		Map<AttributeData,Boolean> onScreenOrNot = mySMC.getScreenData().getIfOnScreen();
		for (AttributeData attr : onScreenOrNot.keySet()) {
			if (onScreenOrNot.get(attr) == false) {
				onScreenOrNot.put(attr, true);
				if (attr.hasVariable(myResources.getString(IMAGE_HOLDER))) {
					Integer xPos = Integer.parseInt(attr.getVariable(myResources.getString(X_POSITION)));
					Integer yPos = Integer.parseInt(attr.getVariable(myResources.getString(Y_POSITION)));
					String imageName = attr.getVariable(myResources.getString(IMAGE_HOLDER));
					Image image = new Image(getClass().getClassLoader().getResourceAsStream(myResources.getString(PATH_TO_IMAGE_FILES) + imageName),
							SCREEN_SIZE/NUM_COLS, SCREEN_SIZE/NUM_ROWS, false, false);
					ImageView imageView = new ImageView(image);
					myGrid.add(imageView, xPos, yPos);
				}
			}			
		}
	}
	
	public void addBorderToCoordinate(GamePoint coord) {
		CoordinateConversion cc = new CoordinateConversion();
		Pair<Integer, Integer> gridCoord = cc.fromGamePointToPair(coord);
		Rectangle border = new Rectangle(myGrid.getWidth()/getNumCols(), myGrid.getHeight()/getNumRows());
		border.setFill(Color.TRANSPARENT);
		border.setStroke(Color.BLACK);
		myGrid.add(border, gridCoord.getKey(), gridCoord.getValue());
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