package engine.input;

import java.util.ArrayList;
import java.util.List;

import engine.camera.GamePoint;
import engine.sprite.SelectionBound;
import engine.sprite.Sprite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SelectionChecker {
	

	public SelectionChecker() {
		
	}
	
	/**
	 * Get the corresponding sprite at the clicked point.
	 * By checking the position relationship between all sprites in the game and the clicked point.
	 * @param model {@code Model} the model holding all sprites in the game
	 * @param pos {@code Point} the clicked point
	 * @return Sprite the clicked sprite
	 */
	public Sprite getSelection(List<Sprite> sprites, GamePoint pos) {
		for (Sprite sprite: sprites) {
			if (sprite.getSelectionBound() == SelectionBound.IMAGE) {
				GamePoint adjustedPos = new GamePoint(sprite.getPos().x() - sprite.getImage().getImagePivot().x(),
						sprite.getPos().y() - sprite.getImage().getImagePivot().y());
				if (checkPointOnImage(sprite.getImage().getFXImage(), pos.x() - adjustedPos.x(), pos.y()-adjustedPos.y())) {
					return sprite;
				}
			}
			else if (sprite.getSelectionBound() == SelectionBound.POLYGON) {
				List<Double> xList = new ArrayList<>(); 
				List<Double> yList = new ArrayList<>();
				for (GamePoint point: sprite.getSelectionBoundVertices()) {
					xList.add(point.x());
					yList.add(point.y());
				}
				if (checkPointInPolygon(sprite.getSelectionBoundVertices().size(), xList, yList, pos.x(), pos.y())) {
					System.out.println("Sprite polygon " + sprite.toString() + " is selected in polygon.");
					return sprite;
				}
			}
		}
		// TODO if nothing is selected, return the background "sprite"
		return null;
	}

	/**
	 * Check if the clicked point is in the Polygon Selection Bound.
	 * Used when the {@code SelectionBound} variable of the {@code Sprite} instance is set to {@code SelectionBound.POLYGON}.
	 * @param nVert number of vertices in the polygon bound
	 * @param xList the list of absolute display x coordinates of vertices in the polygon bound
	 * @param yList the list of absolute display y coordinates of vertices in the polygon bound
	 * @param xClicked the absolute display x coordinate of the clicked point
	 * @param yClicked the absolute display y coordinate of the clicked point
	 * @return boolean if the clicked point is in the polygon
	 * 
	 * @see <a href="http://stackoverflow.com/questions/217578/how-can-i-determine-whether-a-2d-point-is-within-a-polygon">StackOverflow</a>
	 */
	private boolean checkPointInPolygon(int nVert, List<Double> xList, List<Double> yList, double xClicked, double yClicked ) {
		int i, j;
		boolean selected = false;
		for (i = 0, j = nVert-1; i < nVert; j = i++) {
			if ((yList.get(i) > yClicked) != (yList.get(j) > yClicked) && 
					(xClicked < (xList.get(j)-xList.get(i)) * (yClicked-yList.get(i)) / (yList.get(j)-yList.get(i)) + xList.get(i)) ) {
				selected = !selected;
			}
		}
		return selected;
	}
	
	/**
	 * Check if the clicked point is in the opaque areas of the Image.
	 * Used when the {@code SelectionBound} variable of the {@code Sprite} instance is set to {@code SelectionBound.IMAGE}.
	 * @param image JavaFX image
	 * @param x the relative x coordinate of the clicked point relative to the upper-left point of the image
	 * @param y the relative y coordinate of the clicked point relative to the upper-left point of the image
	 * @return boolean if the clicked point is in the image
	 */
	private boolean checkPointOnImage(Image image, double x, double y) {		
		// uses JavaFX ImageView to realize
		return new ImageView(image).contains(x, y);
	}
}
