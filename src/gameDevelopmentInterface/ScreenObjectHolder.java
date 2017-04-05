package gameDevelopmentInterface;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Pair;

public class ScreenObjectHolder extends HBox {
	public static final String MARIO_IMAGE = "images/Mario.jpg";
	public static final String GRASS_IMAGE = "images/Grass.jpg";
	public static final String STONE_IMAGE = "images/Stone.jpg";
	public static final String WATER_IMAGE = "images/Water.jpg";
	private ImageView mario, grass, stone, water;
	private ScreenModelCreator myScreenModel;
	
	public ScreenObjectHolder(ScreenModelCreator smc) {
		myScreenModel = smc;
		Image m = new Image(getClass().getClassLoader().getResourceAsStream(MARIO_IMAGE), 100, 100, false, false);
		Image g = new Image(getClass().getClassLoader().getResourceAsStream(GRASS_IMAGE), 100, 100, false, false);
		Image s = new Image(getClass().getClassLoader().getResourceAsStream(STONE_IMAGE), 100, 100, false, false);
		Image w = new Image(getClass().getClassLoader().getResourceAsStream(WATER_IMAGE), 100, 100, false, false);
		mario = new ImageView(m);
		grass = new ImageView(g);
		stone = new ImageView(s);
		water = new ImageView(w);
		mario.setOnMousePressed(e -> dragAndDrop(mario));
		grass.setOnMousePressed(e -> dragAndDrop(grass));
		stone.setOnMousePressed(e -> dragAndDrop(stone));
		water.setOnMousePressed(e -> dragAndDrop(water));
		this.getChildren().addAll(mario, grass, stone, water);
	}
	
	private void dragAndDrop(ImageView source) {
		//Point p = MouseInfo.getPointerInfo().getLocation();
		//ImageView source = myObjectsToPlace.getMario();
		//ImageView source = myObjectsToPlace.getSpriteToDrag(MouseInfo.getPointerInfo().getLocation().getX(), MouseInfo.getPointerInfo().getLocation().getY());
		ScreenMap target = myScreenModel.getScreen();
		
		source.setOnDragDetected(e -> {
			Dragboard db = source.startDragAndDrop(TransferMode.COPY);
			ClipboardContent content = new ClipboardContent();
			content.putImage(source.getImage());
			db.setContent(content);
			e.consume();
		});
		
		target.setOnDragOver(e -> {
			if (e.getDragboard().hasImage()) {
				e.acceptTransferModes(TransferMode.COPY);
			}
			e.consume();
		});
		
		target.setOnDragEntered(e -> {
			if (e.getDragboard().hasImage()) {
				target.getGrid().setGridLinesVisible(true);
			}
			e.consume();
		});
		
		target.setOnDragExited(e -> {
			if (e.getGestureSource() != target && e.getDragboard().hasImage()) {
				target.getGrid().setGridLinesVisible(false);
			}
			e.consume();
		});
				
		target.setOnDragDropped(e -> {
			Dragboard db = e.getDragboard();
			boolean success = false;
			if (e.getDragboard().hasImage()) {
				GridPane grid = target.getGrid();
				Image im = db.getImage();
				ImageView toAdd = new ImageView(im);
				toAdd.setFitHeight(grid.getHeight()/target.getNumRows());
				toAdd.setFitWidth(grid.getWidth()/target.getNumCols());
				double spriteX = e.getSceneX();
				double spriteY = e.getScreenY();
				Pair<Integer, Integer> coords = target.getCoordOfSpriteHover(spriteX, spriteY);
				grid.add(toAdd, coords.getKey(), coords.getValue());
				success = true;
			}
			e.setDropCompleted(success);
			e.consume();
		});
		
		source.setOnDragDone(e -> {
			e.consume();
		});
	}
	
	public ImageView getSpriteToDrag(double x, double y) {
		for(Node n : this.getChildren()) {
			if (n.getBoundsInLocal().contains(x,y)) {
				return (ImageView) n;
			}
			
//			if (isWithinNode(n, x, y)) {
//				return (ImageView) n;
//			}
		}
		return null;
	}
	
//	private boolean isWithinNode(Node n, double x, double y) {
//		double nodeXLower = n.getLayoutX();
//		double nodeYLower = n.getLayoutY();
//		//double nodeXUpper = nodeXLower + n.getWidth();
//		return false;
//	}
	
	/**
	 * Will be deleted later! Only here because we don't have actual
	 * sprites yet. As soon as we have real sprites, we will delete 
	 * this dummy image
	 * @return the imageview of the object to move
	 */
	public ImageView getMario() {	
		return mario;
	}
	/**
	 * Add a created sprite to the screen object selector
	 * @param screenObject the sprite to add to the HBox
	 */
	public void addObject(Node screenObject) {
		this.getChildren().add(screenObject);
	}
	
}