package newengine.sprite.components;

import java.util.ResourceBundle;

import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.VariableName;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.image.LtubImage;

public class Images extends Component {
	public static final ComponentType<Images> TYPE = new ComponentType<>(Images.class.getName());
//	private ImageSet imageSet;
	private String imageFilePath;
	
//	public Images(ImageSet imageSet) {
//		this.imageSet = imageSet;
//	}
	
	public Images(String filePath) {
		this.imageFilePath = filePath;
	}
//	
	@ConstructorForDeveloper
	public Images(@VariableName(name = "Image") LtubImage image){
		this(image.getFileName());
	}	
	
	public LtubImage image() {
		double heading = 0;
		double dist = 100;
		if (sprite!=null&&sprite.getComponent(Speed.TYPE).isPresent()) {
			Position positionComponent = (Position) sprite.getComponent(Position.TYPE).get();
			heading = positionComponent.heading();
		}
		return new LtubImage(imageFilePath);//imageSet.getImage(heading, dist);
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}

	@Override
	public Images clone() {
		return new Images(imageFilePath);
	}
}
