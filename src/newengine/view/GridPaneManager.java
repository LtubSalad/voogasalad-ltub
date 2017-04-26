/**
 * 
 */
package newengine.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import newengine.sprite.Sprite;

/**
 * @author Zhiyong
 * The gridpane in the bottom of the BorderPane
 * It contains the selected sprite, all the sprites 
 * and the corresponding skills of the selected sprites
 *
 */
public class GridPaneManager implements NodeManager<Node>{
	
	public static final int NUMER_OF_ROW_SKILL= 3;
	public static final int NUMER_OF_COLUMN_SKILL= 3;
	
	public static final int NUMER_OF_ROW_SPRITE= 3;
	public static final int NUMER_OF_COLUMN_SPRITE= 3;
	
	private Map<Pair<Integer, Integer>, Button> skillButtonMap = new HashMap<>();
	private Map<Pair<Integer, Integer>, Button> spriteButtonMap = new HashMap<>();
	private Map<Pair<Integer, Integer>, Button> selectedSpriteButtonMap = new HashMap<>();

	@Override
	public Node getNode() {
		// TODO Auto-generated method stub
		GridPane grid = new GridPane();
		grid.setMinSize(30, 500);
		grid.setHgap(5);
		grid.add(getSmallMap(), 0, 0);
		grid.add(getSelectedSpriteButton(), 1, 0);
		grid.add(getAllSpritesButton(), 2, 0);
		grid.add(getSkillsButton(), 3, 0);
		
		return grid;
	}

	private Node getSkillsButton() {
		// TODO Auto-generated method stub
		GridPane buttonPane = new GridPane();
		for(int i = 0; i < NUMER_OF_COLUMN_SKILL; i ++){
			for(int j = 0; j < NUMER_OF_COLUMN_SKILL; j++){
				Button button = new Button();
				skillButtonMap.put(new Pair<Integer, Integer>(i, j ), button);
			}
		}
		return buttonPane;
	}
	
	
	/**
	 * @return the button of the skills
	 * this information of the skill button will change according to the
	 * corresponding skills of the  selected sprite 
	 */
	public ObservableMap<Pair<Integer, Integer>, Button> getSkillButtonMap(){
		
		return FXCollections.observableMap(skillButtonMap);
	}

	private Node getAllSpritesButton() {
				GridPane buttonPane = new GridPane();
				for(int i = 0; i < NUMER_OF_COLUMN_SPRITE; i ++){
					for(int j = 0; j < NUMER_OF_COLUMN_SPRITE; j++){
						Button button = new Button();
						spriteButtonMap.put(new Pair<Integer, Integer>(i, j ), button);
					}
				}
				return buttonPane;
	}
	
	/**
	 * @return the button of the skills
	 * this information of the skill button will change according to the
	 * corresponding skills of the  selected sprite 
	 */
	public ObservableMap<Pair<Integer, Integer>, Button> getSpritesButtonMap(){
		
		return FXCollections.observableMap(spriteButtonMap);
	}

	private Node getSelectedSpriteButton() {
		// TODO Auto-generated method stub

		Button button = new Button();
		//button.backgroundProperty().bind();
		return button;
	}
	
	public ObservableList<Button> getSelectedSpriteButtonObseervable(){
		List<Button> selectedSpriteButton = new ArrayList<Button>();
		selectedSpriteButton.add((Button) getSelectedSpriteButton());
		
		return FXCollections.observableList(selectedSpriteButton);
	}

	private Node getSmallMap() {
		// TODO Auto-generated method stub
		return null;
	}

}
