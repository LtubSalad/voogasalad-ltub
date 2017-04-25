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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
		hb.getChildren().addAll(getButtonList());
		
		return hb;
	}
	
	private Button addButton(String buttonName, Optional<String> imageNameOpt){
		
		String imageName = imageNameOpt.isPresent() ? imageNameOpt.get() : null;
		if(imageName.equals("")){
			Button button = new Button(myResources.getString(buttonName));
			button.setMinSize(30, 10);
			return button;
		}
		
		Image image= new Image(getClass().getClassLoader().getResourceAsStream(imageName), 200,100, false,true);
		Button button = new Button("",new ImageView(image));
		
		return button;
	}
	
	private List<Button> getButtonList(){

		Button btnHelp = addButton("help", Optional.of(""));
		btnHelp.setOnAction(e -> helpAction());
		
		Button btnQuit = addButton("quit", Optional.of(""));
		btnQuit.setOnAction(e -> quitAction());
		
		Button btnLog = addButton("log", Optional.of(""));
		btnLog.setOnAction(e -> logAction());
	
		Button btnMenu = addButton("menu", Optional.of(""));
		btnMenu.setOnAction(e -> meuAction());
		
		Button btnGold = addButton("gold", Optional.of(GOLD));
		btnGold.setOnAction(e -> goldAction());

		Button btnHealth = addButton("health",Optional.of(HEALTH));
		btnHealth.setOnAction(e -> healthAction());
		
		
		List<Button> buttonList = new ArrayList<>();
		buttonList.add(btnHelp);
		buttonList.add(btnQuit);
		buttonList.add(btnLog);
		buttonList.add(btnMenu);
		buttonList.add(btnGold);
		buttonList.add(btnHealth);

		return buttonList;
	}

	private void healthAction() {
		// TODO Auto-generated method stub
	}

	private void goldAction() {
		// TODO Auto-generated method stub
	}

	private void meuAction() {
		// TODO Auto-generated method stub
		
	}

	private Object logAction() {
		// TODO Auto-generated method stub
		return null;
	}

	private void quitAction() {
		// TODO Auto-generated method stub

	}

	private void helpAction() {
		// TODO Auto-generated method stub
		
	}

}
