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
	
	public Sprite checkSelection(Model model, double x, double y) {
		for (Sprite sprite: model.getSprites()) {
			Image spriteImage = new Image(sprite.getImage().getInputStream());
			ImageView spriteImageView = new ImageView(spriteImage);
			List<Double> xList = new ArrayList<>(); // ImageView rectangle nodes are added in the clock-wise order
			xList.add(spriteImageView.getX());
			xList.add(spriteImageView.getX() + spriteImageView.getFitWidth());
			xList.add(spriteImageView.getX() + spriteImageView.getFitWidth());
			xList.add(spriteImageView.getX());
			List<Double> yList = new ArrayList<>();
			yList.add(spriteImageView.getY());
			yList.add(spriteImageView.getY());
			yList.add(spriteImageView.getY() + spriteImageView.getFitHeight());
			yList.add(spriteImageView.getY() + spriteImageView.getFitHeight());
			if (rayCasting(4, xList, yList, x, y)) {
				return sprite;
			}
		}
	}

	private boolean rayCasting(int nVert, List<Double> xList, List<Double> yList, double xClicked, double yClicked ) {
		return true;
	}
}
