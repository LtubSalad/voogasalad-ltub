package gameDevelopmentInterface;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;

public class PathCreator {
	private Path myPath;
	private List<Pair<Integer, Integer>> replacementPath = new ArrayList<Pair<Integer, Integer>>();
	private ScreenModelCreator myScreenModel;
	
	public PathCreator(ScreenModelCreator smc) {
		myScreenModel = smc;
		myPath = new Path();
	}
	
	public void makePath() {
		ScreenMap target = myScreenModel.getScreen();
		target.setOnMouseEntered(e -> target.getGrid().setGridLinesVisible(true));
		target.setOnMouseExited(e -> target.getGrid().setGridLinesVisible(false));
		target.setOnMousePressed(e -> {
			targetSetOnMousePressed(target, e);
		});
	}
	
	private void targetSetOnMousePressed(ScreenMap target, MouseEvent e) {
		double mouseX = e.getScreenX();
		double mouseY = e.getScreenY();
		Pair<Integer, Integer> coords = target.getCoordOfMouseHover(mouseX, mouseY);
		if (!replacementPath.contains(coords)) {
			replacementPath.add(coords);
		}
		e.consume();
	}
	
	public void replacePath() {
		if (isValidPath(replacementPath)) {
			myPath.changePath(replacementPath);
		}
	}

	private boolean isValidPath(List<Pair<Integer, Integer>> possiblePath) {
		return true;
	}
	
}
