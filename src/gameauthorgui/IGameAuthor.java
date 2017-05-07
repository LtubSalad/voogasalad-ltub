package gameauthorgui;

import javafx.scene.Scene;

/**
 * Expected behavior for the GameAuthor class
 * @author Matthew Tribby
 */
public interface IGameAuthor {
		
		/**
		 * Adds a step to the process in the GUI
		 * @param step
		 */
		public void addStep(DeveloperStep step);
		
		/**
		 * Add a step to the process at a certain index
		 * @param index
		 * @param step
		 */
		public void addStep(int index, DeveloperStep step);
		
		/**
		 * Change the current step
		 * @param step
		 */
		public void changeStep(DeveloperStep step);
		
		/**
		 * Return scene to display on a stage
		 * @return Scene
		 */
		public Scene getScene();
	
}
