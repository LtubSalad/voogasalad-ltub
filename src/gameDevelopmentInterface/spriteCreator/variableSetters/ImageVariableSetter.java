package gameDevelopmentInterface.spriteCreator.variableSetters;

import java.io.File;
import java.io.IOException;

import exception.UnsupportedTypeException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import newengine.utils.image.LtubImage;

/**
 * 
 * A GUI component that allows the user to choose an LtubImage from a file, and get it back later on.
 * @author Daniel
 *
 */
public class ImageVariableSetter extends VariableSetter<LtubImage>{
	private VBox myContents;
	private StringProperty myImagePath;
	private double PREF_IMAGE_WIDTH=300;
	private double PREF_IMAGE_HEIGHT=300;
	private final File base =new File(System.getProperty("user.dir")+File.separator+"src");
	
	public ImageVariableSetter(String variableName) {
		super(variableName);
		myImagePath=new SimpleStringProperty();
		myContents=new VBox();
		getChildren().add(myContents);
		Button button= new Button("Choose Image File");
		button.setOnMouseClicked((event)->{
			FileChooser chooser=new FileChooser();
			chooser.setInitialDirectory(base);	
			File imageFile=chooser.showOpenDialog(new Stage());
			if(imageFile!=null){
				File relativePath=new File(base.toURI().relativize(imageFile.toURI()).getPath());
				myImagePath.set(relativePath.toString());
				System.out.println(relativePath);
			}
		});
		myContents.getChildren().addAll(new ImageDisplay(),button);
		myContents.setSpacing(20);
		System.out.println(base);
	}

	@Override
	public LtubImage getValue() throws UnsupportedTypeException, Exception {
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
			this.setAutoSizeChildren(false);
			myImagePath.addListener((invalidation)->{
				display=new ImageView(new LtubImage(myImagePath.getValue()).getFileName());
				display.fitHeightProperty().setValue(PREF_IMAGE_HEIGHT);
				display.fitWidthProperty().setValue(PREF_IMAGE_WIDTH);
				this.getChildren().clear();
				this.getChildren().add(display);
				
			});
		}
		
	}

}
