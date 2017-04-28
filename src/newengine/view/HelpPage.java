
package newengine.view;
	 
	import java.util.ResourceBundle;

/**
	 * @author Zhiyong
	 *
	 */
	import javafx.scene.Scene;
	import javafx.scene.layout.Pane;
	import javafx.scene.paint.Color;
	import javafx.scene.text.Font;
	import javafx.scene.text.Text;
	import javafx.stage.Stage;
import player.App;

	public class HelpPage{
		public static final double SIZE =400;
		private Scene scene;
		private String message;
		private  ResourceBundle myResources = ResourceBundle.getBundle(App.RESOURCES_LOCATION);
		
		public HelpPage(String message){
	       this.message = message;
		}
		
		/**
		 * @return
		 * return he scene if other program needs the scene
		 */
		public Scene getScene(){
			return scene;
		}
		
		/**
		 * show up the help message from the pop up screen
		 */
		public void showMessage(){
			Pane root = new Pane();
			// create a place to see the shapes
			scene = new Scene(root, SIZE, SIZE);
			
			Text splash = new Text();
			//show the exception to the user
			splash = new Text(10,50,message);
			splash.setFont(Font.font(25));
			splash.setFill(Color.DARKVIOLET);
			root.getChildren().addAll(splash);
			Stage s = new Stage();
			s.setScene(scene);
			s.setTitle(myResources.getString("help"));
			s.show();
		}
	}