package utilities;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import data.SpriteMakerModel;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class XStreamHandler {
	//TODO: Remove duplicate code using generics
	
	public SpriteMakerModel getAttributeFromFile() {
		XStream xstream = new XStream(new DomDriver());
		FileChooser chooser = new FileChooser();
		File attributeFile = chooser.showOpenDialog(new Stage());
		SpriteMakerModel attribute = (SpriteMakerModel)xstream.fromXML(attributeFile);
		return attribute;
	}
	
	public SpriteMakerModel getAttributeFromFile(File file) {
		XStream xstream = new XStream(new DomDriver());
		SpriteMakerModel attribute = (SpriteMakerModel)xstream.fromXML(file);
		return attribute;
	}
	
	public <T> T getObjectFromFile(Class<T> clazz, File file){
		XStream xstream = new XStream(new DomDriver());
		T object = (T)xstream.fromXML(file);
		return object;
	}
	
	public List<SpriteMakerModel> getScreenModelFile() {
		XStream xstream = new XStream(new DomDriver());
		FileChooser chooser = new FileChooser();
		File attributeFile = chooser.showOpenDialog(new Stage());
		List<SpriteMakerModel> attribute = (List<SpriteMakerModel>)xstream.fromXML(attributeFile);
		return attribute;
	}
	
	public List<SpriteMakerModel> getScreenModel(File file) {
		XStream xstream = new XStream(new DomDriver());
		List<SpriteMakerModel> attribute = (List<SpriteMakerModel>)xstream.fromXML(file);
		return attribute;
	}

	public void saveToFile(Object data) {
		FileChooser chooser = new FileChooser();
		File location = chooser.showSaveDialog(new Stage());
		saveToFile(data,location);
	}
	
	public void saveToFile(Object data, File location){
		XStream xstream = new XStream(new DomDriver());
		String content = xstream.toXML(data);
		try {
			FileWriter fileWriter = new FileWriter(location);
			fileWriter.write(content);
			fileWriter.close();
		} catch (Exception e) {
			// user clicked cancel. No need to exclaim anything went wrong
		}
	}
}