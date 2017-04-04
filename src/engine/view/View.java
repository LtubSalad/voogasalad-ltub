package engine.view;

import engine.model.Model;
import javafx.scene.Scene;

public interface View {
	
	public Scene getScene();
	
	public void render(Model model);

}
