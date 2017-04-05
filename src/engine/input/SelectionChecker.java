package engine.input;

import java.util.ArrayList;
import java.util.List;

import engine.model.Model;
import engine.sprite.Sprite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SelectionChecker {
	

	public SelectionChecker() {
		
	}
	
	public Sprite getSelection(Model model, double x, double y) {
		for (Sprite sprite: model.getSprites()) {
			Image spriteImage = new Image(sprite.getImage().getInputStream());
			List<Double> xList = new ArrayList<>(); // ImageView rectangle nodes are added in the clock-wise order
			xList.add(sprite.getPos().x());
			xList.add(sprite.getPos().x() + spriteImage.getWidth());
			xList.add(sprite.getPos().x() + spriteImage.getWidth());
			xList.add(sprite.getPos().x());
			List<Double> yList = new ArrayList<>();
			yList.add(sprite.getPos().y());
			yList.add(sprite.getPos().y());
			yList.add(sprite.getPos().y() + spriteImage.getHeight());
			yList.add(sprite.getPos().y() + spriteImage.getHeight());
			if (checkPointInPolygon(4, xList, yList, x, y)) {
				System.out.println("Sprite " + sprite.toString() + " is selected.");
				return sprite;
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
