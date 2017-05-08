package gameDevelopmentInterface.spriteCreator;


import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import data.DeveloperData;
import data.SpriteMakerModel;
import exception.UnsupportedTypeException;
import gameDevelopmentInterface.developerdata.ObjectSetter;
import gameDevelopmentInterface.spriteCreator.variableSetters.DefaultObjectSetter;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import newengine.sprite.component.Component;
import utilities.AlertHandler;

/**
 * 
 * @author Daniel
 * Displays a series of component setters on a scrollpane. Can be called upon to replace a SpriteMakerModel with 
 * components based on what is currently shown on the screen.
 *
 */
public class SpriteDataPane extends ScrollPane{
	private VBox myPane;
	private ComponentLister lister;
	private DeveloperData developerData;
	private double PREF_WIDTH=600;
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	public static final String RESOURCE_FILE_NAME = "gameAuthoringEnvironment";
	private static ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCE_FILE_NAME);
	
	public SpriteDataPane(SpriteMakerModel spriteData, DeveloperData developerData){
		instantiate(spriteData, developerData);
		for(Component component:spriteData.getActualComponents()){
			addComponent(component,true);
		}
	}
	
	private void instantiate(SpriteMakerModel spriteData, DeveloperData developerData){
		this.developerData=developerData;
		myPane=new VBox();
		lister=new ComponentLister();
		updateLister(spriteData);
		myPane.getChildren().addAll(new Label(myResources.getString("spriteComponents")), lister);
		myPane.setPrefWidth(PREF_WIDTH);
		this.setContent(myPane);
		this.setPrefWidth(PREF_WIDTH);
		
	}
	
	private void updateLister(SpriteMakerModel sprite){
		lister.clear();
		for(Component component:sprite.getActualComponents()){
			addComponent(component,true);
		}
	}
	
	public void setPresetSprite(SpriteMakerModel sprite){
		lister.clear();
		for(Component component:sprite.getActualComponents()){
			addComponent(component,false);
		}
	}
	
	public <T extends Component> void addComponent(T component, boolean removable){
		try{
			ObjectSetter<T> setter=new DefaultObjectSetter<T>(component,developerData);
			if(!removable){
				lister.addComponentView(setter);
			}else{
				lister.addComponentView(lister.new RemovableComponentViewer<T>(setter));
			}
		}
		catch(UnsupportedTypeException e){
			e.printStackTrace();
		}
	}
	
	public <T extends Component> void  addComponent(Class<T> clazz){
		try {
			ObjectSetter<T> setter=new DefaultObjectSetter<T>(clazz,developerData);
			lister.addComponentView(lister.new RemovableComponentViewer<T>(setter));
		} catch (UnsupportedTypeException e) {
			e.printStackTrace();
		}	
	}
	
	public void updateSpriteData(SpriteMakerModel spriteData) throws Exception{
		lister.updateSpriteModel(spriteData);		
	}
	
	/**
	 * Lists a series of ObjectSetters that are used for Components.
	 * @author Daniel
	 *
	 */
	private class ComponentLister extends VBox{
		private List<ObjectSetter<? extends Component>> componentViews;
		
		public ComponentLister(){
			componentViews=new ArrayList<>();
			this.prefWidthProperty().bind(SpriteDataPane.this.prefWidthProperty());
		}
		
		private void clear(){
			componentViews.clear();
			this.getChildren().clear();
		}
		
		private void removeComponentView(ObjectSetter<? extends Component> view){
			componentViews.remove(view);
			this.getChildren().remove(view);
		}
		
		private void addComponentView(ObjectSetter<? extends Component> view){
			for(ObjectSetter<? extends Component> viewer: componentViews){
				if(view.getObjectType().equals(viewer.getObjectType())){
					return;
				}
			}
			componentViews.add(view);
			view.setStyle("-fx-border-color:black");
			this.getChildren().add(view);
		}
		
		/**
		 * ONLY CALL WHEN DONE
		 * @throws Exception
		 */
		private void updateSpriteModel(SpriteMakerModel spriteData){
			try {
				spriteData.clearComponents();
				for(ObjectSetter<? extends Component> component: componentViews){
					spriteData.addComponent(component.produceObject());
				}
			} catch (Exception e) {
				e.printStackTrace();
				AlertHandler.showError(e.getMessage());
			}
			
		}
		
		private class RemovableComponentViewer<T extends Component> extends ObjectSetter<T>{
			private ObjectSetter<T> mySetter;
			private RemovableComponentViewer(ObjectSetter<T> mySetter){
				super(mySetter.getObjectType());
				this.mySetter=mySetter;
				this.getChildren().addAll(mySetter);
				Button removeButton =new Button(myResources.getString("removeComponent"));
				removeButton.setOnAction((click)->{
					removeMe();
				});
				this.getChildren().add(removeButton);
			}
			
			private void removeMe(){
				ComponentLister.this.removeComponentView(this);
			}

			public T produceObject() throws Exception{
				try {
					return mySetter.produceObject();
				} catch (UnsupportedTypeException e) {
					return null;
				}
				
			}
		}
	}	
}
