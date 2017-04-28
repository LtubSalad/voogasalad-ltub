package gameDevelopmentInterface.spriteCreator;


import java.util.ArrayList;
import java.util.List;

import data.DeveloperData;
import data.SpriteMakerModel;
import exception.UnsupportedTypeException;
import gameDevelopmentInterface.developerdata.ComponentSetterView;
import gameauthorgui.inputhelpers.StringParameterInput;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import newengine.sprite.component.Component;
import newengine.sprite.components.PathFollower;
import newengine.sprite.components.SkillSet;

public class SpriteInfoPane extends ScrollPane{
	private VBox myPane;
	private SpriteMakerModel spriteData;
	private SpriteDescriptor descriptor;
	private ComponentLister lister;
	private DeveloperData developerData;
	private double PREF_WIDTH=600;
	
	public SpriteInfoPane(SpriteMakerModel spriteData, DeveloperData developerData){
		this.developerData=developerData;
		myPane=new VBox();
		this.spriteData=spriteData;
		descriptor=new SpriteDescriptor();
		lister=new ComponentLister();
		myPane.getChildren().addAll(descriptor,lister);
		this.setContent(myPane);
		this.setPrefWidth(PREF_WIDTH);
	}
	
	private class SpriteDescriptor extends VBox{
		private SpriteDescriptor(){
				this.getChildren().add(new Label("Add Sprite Components"));
				this.getChildren().add(new StringParameterInput( "Sprite name:"));
				this.getChildren().add(new StringParameterInput("Sprite description:"));
		}
	}
	
	public <T extends Component> void  addComponent(Class<T> clazz){
		try {
			ComponentSetterView<? extends Component> setter=new ComponentSetter<T>(clazz,developerData);
			lister.addComponentView(lister.new ComponentViewer(setter));
		} catch (UnsupportedTypeException e) {
			e.printStackTrace();
			//throw to main screen
		}	
	}
	
	public void updateSpriteData() throws Exception{
		lister.updateSpriteModel();		
	}
	
	private class ComponentLister extends VBox{
		private List<ComponentViewer<? extends Component>> componentViews;
		
		public ComponentLister(){
			componentViews=new ArrayList<>();
			this.prefWidthProperty().bind(SpriteInfoPane.this.prefWidthProperty());
		}
		
		private void removeComponentView(ComponentViewer<? extends Component> view){
			componentViews.remove(view);
			this.getChildren().remove(view);
			
		}
		
		private void addComponentView(ComponentViewer<? extends Component> view){
			for(ComponentViewer<? extends Component> viewer: componentViews){
				if(view.getType().equals(viewer.getType())){
					return;
				}
			}
			componentViews.add(view);
			this.getChildren().add(view);
		}
		
		/**
		 * ONLY CALL WHEN DONE
		 * @throws Exception
		 */
		private void updateSpriteModel() throws Exception{
			for(ComponentViewer<? extends Component> component: componentViews){
				spriteData.addComponent(component.getComponent());
			}
		}
		
		private class ComponentViewer<T extends Component> extends VBox{
			private ComponentSetterView<T> mySetter;
			public ComponentViewer(ComponentSetterView<T> mySetter){
				this.setStyle("-fx-background-color: cyan;-fx-border-color:red");
				this.mySetter=mySetter;
				this.getChildren().add(mySetter);
				Button removeButton =new Button("Remove component");
				removeButton.setOnAction((click)->{
					removeMe();
				});
				this.getChildren().add(removeButton);
				this.prefWidthProperty().bind(ComponentLister.this.prefWidthProperty());
			}
			
			private void removeMe(){
				ComponentLister.this.removeComponentView(this);
			}
			
			private T getComponent() throws Exception{
				try {
					return mySetter.produceComponent();
				} catch (UnsupportedTypeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			
			private Class<T> getType(){
				return mySetter.getComponentType();
			}
			
		}
	}
	
}
