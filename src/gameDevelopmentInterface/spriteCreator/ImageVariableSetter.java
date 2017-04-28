package gameDevelopmentInterface.spriteCreator;

import java.io.File;

import exception.UnsupportedTypeException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import newengine.utils.image.LtubImage;

public class ImageVariableSetter extends VariableSetter<LtubImage>{
	private VBox myContents;
	private StringProperty myImagePath;
	private double PREF_IMAGE_WIDTH=300;
	private double PREF_IMAGE_HEIGHT=300;
	
	public ImageVariableSetter(String variableName) {
		super(variableName);
		myImagePath=new SimpleStringProperty();
		myContents=new VBox();
		getChildren().add(myContents);
		Button button= new Button("Choose Image File");
		button.setOnMouseClicked((event)->{
			FileChooser chooser=new FileChooser();
			File imageFile=chooser.showOpenDialog(new Stage());
			if(imageFile!=null){
				myImagePath.set(imageFile.toURI().toString());
			}
			
		});
		myContents.getChildren().addAll(new ImageDisplay(),button);
		myContents.setSpacing(20);
		// TODO Auto-generated constructor stub
	}

	@Override
	public LtubImage getValue() throws UnsupportedTypeException, Exception {
		// TODO Auto-generated method stub
		return new LtubImage(myImagePath.getValue());
	}

	@Override
	public void setField(LtubImage initialValue) {
		myImagePath.set(initialValue.getFileName());
	}
	
	private class ImageDisplay extends Group{
		private ImageView display;
		private ImageDisplay(){
			Rectangle rec=new Rectangle(PREF_IMAGE_WIDTH,PREF_IMAGE_HEIGHT);
			this.getChildren().add(rec);
			rec.setFill(Color.BEIGE);
			myImagePath.addListener((invalidation)->{
				display=new ImageView(new LtubImage(myImagePath.getValue()).getFileName());
				display.fitHeightProperty().setValue(PREF_IMAGE_HEIGHT);
				display.fitWidthProperty().setValue(PREF_IMAGE_WIDTH);
				this.getChildren().add(display);
				
			});
		}
		
	}

}
