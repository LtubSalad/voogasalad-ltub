package gameDevelopmentInterface.spriteCreator;

import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.List;

import data.SpriteMakerModel;
import exception.UnsupportedTypeException;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import newengine.sprite.component.Component;

public class SpriteInfoPane extends BorderPane{
	private ScrollPane scrollView;
	private final double WIDTH=400;
	private final double HEIGHT=400;
	private SpriteMakerModel spriteData;
	private SpriteDescriptor descriptor;
	private ComponentLister lister;
	public SpriteInfoPane(SpriteMakerModel spriteData){
		scrollView=new ScrollPane();
		this.spriteData=spriteData;
		descriptor=new SpriteDescriptor();
		lister=new ComponentLister();
		this.setTop(descriptor);
		this.setCenter(lister);
	}
	
	private class SpriteDescriptor extends HBox{
		private SpriteDescriptor(){
			try {
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
			ComponentSetter<? extends Component> setter=new ComponentSetter(clazz);
			lister.addComponentView(lister.new ComponentViewer(setter));
		} catch (UnsupportedTypeException e) {
			e.printStackTrace();
			//throw to main screen
		}	
	}
	
	public SpriteMakerModel getSpriteData(){
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
		
		private void updateSpriteModel(){
			for(ComponentViewer component: componentViews){
				spriteData.addComponent(component.getComponent());
			}
		}
		
		private class ComponentViewer<T extends Component> extends VBox{
			private ComponentSetter<T> mySetter;
			public ComponentViewer(ComponentSetter<T> mySetter){
				this.mySetter=mySetter;
				this.getChildren().add(mySetter);
				Button removeButton =new Button("Remove me");
				removeButton.setOnAction((click)->{
					removeMe();
				});
				this.getChildren().add(removeButton);
			}
			
			private void removeMe(){
				ComponentLister.this.removeComponentView(this);
			}
			
			private T getComponent(){
				try {
					return mySetter.makeComponent();
				} catch (UnsupportedTypeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			
		}
	}
	
}
