/**
 * 
 */
package newengine.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
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
		hb.getChildren().addAll(getButtons().values());
		
		return hb;
	}
	
	private Button addButton(String buttonName, Optional<String> imageNameOpt){
		
		String imageName = imageNameOpt.isPresent() ? imageNameOpt.get() : null;
		if(imageName.equals("")){
			Button button = new Button(myResources.getString(buttonName));
			button.setMinSize(10, 10);
			return button;
		}
		
		Image image= new Image(getClass().getClassLoader().getResourceAsStream(imageName), 20,10, false,true);
		Button button = new Button("",new ImageView(image));
		
		return button;
	}
	
	private Map<String, Button> getButtons(){

		Button btnHelp = addButton("help", Optional.of(""));
		btnHelp.setOnAction(e -> helpAction());
		
		Button btnQuit = addButton("quit", Optional.of(""));
		btnQuit.setOnAction(e -> quitAction());
		
		Button btnLog = addButton("log", Optional.of(""));
		btnLog.setOnAction(e -> logAction());
	
		Button btnMenu = addButton("menu", Optional.of(""));
		btnMenu.setOnAction(e -> meuAction());
		
		
		
		Map<String, Button> buttons = new HashMap<>();
		buttons.put("help", btnHelp);
		buttons.put("quit", btnQuit);
		buttons.put("log", btnLog);
		buttons.put("menu", btnMenu);


		return buttons;
	}
	

	private void meuAction() {
		// TODO Auto-generated method stub
		
	}

	private void logAction() {
		// TODO Auto-generated method stub
		
	}

	private void quitAction() {
		// TODO Auto-generated method stub

	}

	private void helpAction() {
		System.out.println("Help me");
		
	}

}
