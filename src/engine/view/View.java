package engine.view;

import engine.model.Model;
import engine.model.PlayerLocalModel;
import javafx.scene.Scene;

public interface View {
	
	public Scene getScene();
	
	public void render(Model model);

	public void render(PlayerLocalModel localModel);
	
}
