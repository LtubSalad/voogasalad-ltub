package commons;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


public class FileLoader{
    // kind of data files to look for
    public static final String DATA_FILE_EXTENSION = "*.xml";
    // it is generally accepted behavior that the chooser remembers where user left it last
    private FileChooser myChooser = makeChooser(DATA_FILE_EXTENSION);
    private Stage callingStage;
    
    /**
     * Constructor of the {@code FileLoader} class.
     * @param stage The Stage instance of the calling JavaFX app.
     */
    public FileLoader(Stage stage){
    	this.callingStage = stage;
    }
    
    /**
     * Open up a dialog to choose file
     * @return File the chosen file. If no file is chosen, return {@code null}.
     */
    public File chooseFile() {
        File dataFile = myChooser.showOpenDialog(callingStage);
        return dataFile;
    }
    
    // set some sensible defaults when the FileChooser is created
    private FileChooser makeChooser (String extensionAccepted) {
        FileChooser result = new FileChooser();
        result.setTitle("Choose Game XML File");
        // pick a reasonable place to start searching for files
        result.setInitialDirectory(new File(System.getProperty("user.dir")));
        result.getExtensionFilters().setAll(new ExtensionFilter("XML Files", extensionAccepted));
        return result;
    }
}