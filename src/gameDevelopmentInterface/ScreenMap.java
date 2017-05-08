package gameDevelopmentInterface;

import java.util.Map;
import java.util.ResourceBundle;

import commons.point.GamePoint;
import data.DeveloperData;
import data.SpriteMakerModel;
import javafx.collections.ListChangeListener;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import newengine.sprite.component.Component;
import newengine.sprite.components.Images;
import newengine.sprite.components.Position;

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
	private int CELL_SIZE = 100;
	private GridPane myGrid;
	//private ScreenModelCreator myScreenModelCreator;
	private static final String PATH_TO_IMAGE_FILES = "PATH_TO_IMAGE_FILES";
	private DeveloperData myModel;
	
	public ScreenMap(DeveloperData model) {
		myModel = model;
		addListeners();
		this.setHeight(myModel.getNumRows()*CELL_SIZE);
		this.setWidth(myModel.getNumCols()*CELL_SIZE);
		makeGrid();
	}
	private void addListeners() {
		myModel.getBackgroundTiles().addListener(new ListChangeListener<SpriteMakerModel>() {
			@Override
			public void onChanged(@SuppressWarnings("rawtypes") ListChangeListener.Change change) {
				System.out.println("Drop detected");
				redrawGrid();
			}
		});
	}
//	public void resize(int width, int height) {
//		myScreenModelCreator.getScreenData().getAllObjectsOnScreen().addListener(new ListChangeListener<SpriteMakerModel>() {
//			@Override
//			public void onChanged(@SuppressWarnings("rawtypes") ListChangeListener.Change change) {
//				redrawGrid();
//			}
//		});
//		myScreenHeight = height;
//		myScreenWidth = width;
//		CELL_SIZE = myScreenHeight/NUM_COLS;
//		makeGrid();
//		redrawGrid();
//	}
	
//	/**
//	 * Can change number of rows on screen
//	 * @param numRows
//	 */
//	public void setNumRows(int numRows) {
//		NUM_ROWS = numRows;
//		myScreenHeight = NUM_ROWS*CELL_SIZE;
//		makeGrid();
//	}
//	/**
//	 * Can change number of columns on screen
//	 * @param numCols
//	 */
//	public void setNumCols(int numCols) {
//		NUM_COLS = numCols;
//		myScreenWidth = NUM_COLS*CELL_SIZE;
//		makeGrid();
//	}
	/**
	 * 
	 * @return number of rows on screen
	 */
	public int getNumRows() {
		return myModel.getNumRows();
	}
	/**
	 * 
	 * @return number of columns on screen
	 */
	public int getNumCols() {
		return myModel.getNumCols();
	}
	
	public int getScreenWidth() {
		return myModel.getNumCols()*CELL_SIZE;
	}
	public int getScreenHeight() {
		return myModel.getNumRows()*CELL_SIZE;
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
		int colNum = getColOrRowPlacement(0, myGrid.getWidth(), myGrid.getWidth()/getNumCols(), x, boundsInScreen);
		int rowNum = getColOrRowPlacement(0, myGrid.getHeight(), myGrid.getHeight()/getNumRows(), y, boundsInScreen);
		return new GamePoint(colNum, rowNum);
	}
	
	public GamePoint getActualLocationOfSprite(GamePoint gp) {
		double actualX = (gp.x()*(getGrid().getWidth()/getNumCols())) + ((getGrid().getWidth()/getNumCols())/2);
		double actualY = (gp.y()*(getGrid().getHeight()/getNumRows())) + ((getGrid().getHeight()/getNumRows())/2);
		return new GamePoint(actualX, actualY);
	}

	
	private void redrawGrid() {
		Map<SpriteMakerModel, Boolean> onScreenOrNot = myModel.getIfTileOnScreen();
		for (SpriteMakerModel sprite : onScreenOrNot.keySet()) {
			if (onScreenOrNot.get(sprite) == false) {
				onScreenOrNot.put(sprite, true);
				for (Component c : sprite.getDeprecatedComponents().values()) {
					if (c.getType().equals(Images.TYPE)) {
						Images imageComponent = (Images) c;
						ImageView imageView = new ImageView(imageComponent.image().getFXImage());
						imageView.setFitHeight(myGrid.getHeight() / myModel.getNumRows());
						imageView.setFitWidth(myGrid.getWidth() / myModel.getNumCols());
						Component possiblePosition = sprite.getComponentByType(Position.TYPE);
						if (possiblePosition != null) {
							Position pos = (Position) possiblePosition;
							GamePoint percentPoint = pos.pos();
							GamePoint gridCoords = getCoordOfMouseHover(percentPoint.x()*getScreenWidth(), percentPoint.y()*getScreenHeight());
							Integer xPos = (int) gridCoords.x();
							Integer yPos = (int) gridCoords.y();
							myGrid.add(imageView, xPos, yPos);
						}
					}
				}
			}			
		}
	}
	
	public void addBorderToCoordinate(GamePoint coord) {
		CoordinateConversion cc = new CoordinateConversion();
		Pair<Integer, Integer> gridCoord = cc.fromGamePointToPair(coord, myGrid);
		Rectangle border = new Rectangle(myGrid.getWidth()/myModel.getNumCols(), myGrid.getHeight()/myModel.getNumRows());
		border.setFill(Color.TRANSPARENT);
		border.setStroke(Color.RED);
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
		myGrid.setMaxHeight(getScreenHeight());
		myGrid.setMaxWidth(getScreenWidth());
		for (int i = 0; i < getNumRows(); i++) {
			RowConstraints row = new RowConstraints(getScreenHeight()/getNumRows());
			myGrid.getRowConstraints().add(row);
		}
		for (int j = 0; j < getNumCols(); j++) {
			ColumnConstraints col = new ColumnConstraints(getScreenWidth()/getNumCols());
			myGrid.getColumnConstraints().add(col);
		}
		for (int i = 0; i < getNumRows(); i++) {
			for (int j = 0; j < getNumCols(); j++) {
				myGrid.add(new Rectangle(CELL_SIZE, CELL_SIZE, Color.WHITESMOKE), j, i);
			}
		}
		this.getChildren().clear();
		this.getChildren().add(myGrid);
	}

}
