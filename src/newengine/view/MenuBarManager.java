/**
 * 
 */
package newengine.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import player.App;

/**
 * @author Zhiyong
 *
 *This menubar contains help, gold, health, quit?
 */
public class MenuBarManager implements NodeManager<Node>{
	//the path of the gold image
	public static final String GOLD = "resources/gold.jpeg";
	public static final String HEALTH = "resources/health.jpg";
	
	private  ResourceBundle myResources = ResourceBundle.getBundle(App.RESOURCES_LOCATION);

	@Override
	public Node getNode() {
		// TODO Auto-generated method stub
		HBox hb = new HBox();
		hb.setPadding(new Insets(15, 12, 15, 12));
		hb.setSpacing(10);
		hb.getChildren().addAll(addButtonList());
		
		
		return hb;
	}
	
	private Button addButton(String buttonName, Optional<String> imageNameOpt){
		
		String imageName = imageNameOpt.isPresent() ? imageNameOpt.get() : null;
		
		Image image= new Image(getClass().getClassLoader().getResourceAsStream(imageName));
		Button button = new Button(myResources.getString(buttonName),new ImageView(image));
		
		return button;
	}
	
	private List<Button> addButtonList(){
		System.out.println("Here");
//		Button btnHelp = addButton("help", Optional.of(null));
//		Button btnQuit = addButton("quit", Optional.of(null));
//		Button btnLog = addButton("log", Optional.of(null));
//		
//		Button btnMenu = addButton("menu", Optional.of(null));
		
		Button btnGold = addButton("gold", Optional.of(GOLD));
		
		Button btnHealth = addButton("health",Optional.of(HEALTH));
		
		List<Button> buttonList = new ArrayList<>();
//		buttonList.add(btnHelp);
//		buttonList.add(btnQuit);
//		buttonList.add(btnLog);
//		buttonList.add(btnMenu);
		buttonList.add(btnGold);
		buttonList.add(btnHealth);
		return buttonList;
	}

}
