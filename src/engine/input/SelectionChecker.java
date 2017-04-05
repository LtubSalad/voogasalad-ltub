package engine.input;

import java.util.ArrayList;
import java.util.List;

import commons.Point;
import engine.model.Model;
import engine.sprite.SelectionBound;
import engine.sprite.Sprite;

public class SelectionChecker {
	

	public SelectionChecker() {
		
	}
	
	public Sprite getSelection(Model model, double x, double y) {
		for (Sprite sprite: model.getSprites()) {
			List<Double> xList = new ArrayList<>(); 
			List<Double> yList = new ArrayList<>();
			for (Point point: sprite.getSelectionBoundVertices()) {
				xList.add(point.x());
				yList.add(point.y());
			}
			if (sprite.getSelectionBound() == SelectionBound.IMAGE) {
				if (checkPointInPolygon(sprite.getSelectionBoundVertices().size(), xList, yList, x, y)) {
					// TODO check if clicked on a transparent pixel
					System.out.println("Sprite " + sprite.toString() + " is selected.");
					return sprite;
				}
			}
		}
		// TODO if nothing is selected, return the background "sprite"
		return model.getSprites().get(0);
	}

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
}
