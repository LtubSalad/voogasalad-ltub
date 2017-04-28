package gameauthorgui;

import javafx.scene.Scene;

public interface IGameAuthor {
		
		public void addStep(DeveloperStep step);
		public void addStep(int index, DeveloperStep step);
		public void changeStep(DeveloperStep step);
		public Scene getScene();
	
}
