package gameDevelopmentInterface.spriteCreator;


import java.util.ArrayList;
import java.util.List;

import data.DeveloperData;
import data.SpriteMakerModel;
import exception.UnsupportedTypeException;
import gameDevelopmentInterface.developerdata.ComponentSetterView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import newengine.sprite.component.Component;
import newengine.sprite.components.PathFollower;

public class SpriteInfoPane extends ScrollPane{
	private VBox myPane;
	private final double WIDTH=400;
	private final double HEIGHT=400;
	private SpriteMakerModel spriteData;
	private SpriteDescriptor descriptor;
	private ComponentLister lister;
	private DeveloperData developerData;
	
	public SpriteInfoPane(SpriteMakerModel spriteData, DeveloperData developerData){
		this.developerData=developerData;
		myPane=new VBox();
		this.spriteData=spriteData;
		descriptor=new SpriteDescriptor();
		lister=new ComponentLister();
		myPane.getChildren().addAll(descriptor,lister);
		this.setContent(myPane);
	}
	
	private class SpriteDescriptor extends VBox{
		private SpriteDescriptor(){
			try {
				this.getChildren().add(new Label("Add Sprite Components"));
				this.getChildren().add(new SimpleVariableSetter(String.class, "Sprite name:"));
				this.getChildren().add(new SimpleVariableSetter(String.class, "Sprite description:"));
			} catch (UnsupportedTypeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void addComponent(Class<? extends Component> clazz){
		try {
			ComponentSetterView<? extends Component> setter;
			if(clazz.equals(PathFollower.class)){
				setter=new PathFollowerSetter(developerData.getPaths());
			}
			else{
				setter=new SimpleComponentSetter(clazz);
			}
			lister.addComponentView(lister.new ComponentViewer(setter));
		} catch (UnsupportedTypeException e) {
			e.printStackTrace();
			//throw to main screen
		}	
	}
	
	public SpriteMakerModel getSpriteData() throws Exception{
		lister.updateSpriteModel();
		return spriteData;
		
	}
	
	private class ComponentLister extends VBox{
		private List<ComponentViewer> componentViews;
		
		public ComponentLister(){
			componentViews=new ArrayList<>();
		}
		
		private void removeComponentView(ComponentViewer view){
			componentViews.remove(view);
			this.getChildren().remove(view);
			
		}
		
		private void addComponentView(ComponentViewer view){
			componentViews.add(view);
			this.getChildren().add(view);
		}
		
		private void updateSpriteModel() throws Exception{
			for(ComponentViewer component: componentViews){
				spriteData.addComponent(component.getComponent());
			}
		}
		
		private class ComponentViewer<T extends Component> extends VBox{
			private ComponentSetterView<T> mySetter;
			public ComponentViewer(ComponentSetterView<T> mySetter){
				this.mySetter=mySetter;
				this.getChildren().add(mySetter);
				Button removeButton =new Button("Remove component");
				removeButton.setOnAction((click)->{
					removeMe();
				});
				this.getChildren().add(removeButton);
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
			
		}
	}
	
}
