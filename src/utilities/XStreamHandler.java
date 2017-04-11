package utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;

import data.AttributeData;
import data.ScreenModelData;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class XStreamHandler {
	public AttributeData getAttributeFromFile() {
		XStream xstream = new XStream();
		FileChooser chooser = new FileChooser();
		File attributeFile = chooser.showOpenDialog(new Stage());
		AttributeData attribute = (AttributeData)xstream.fromXML(attributeFile);
		return attribute;
	}
	
	public ScreenModelData getScreenModelFile() {
		XStream xstream = new XStream();
		FileChooser chooser = new FileChooser();
		File attributeFile = chooser.showOpenDialog(new Stage());
		ScreenModelData attribute = (ScreenModelData)xstream.fromXML(attributeFile);
		return attribute;
	}

	public void saveObjectToFile(Object data) {
		FileChooser chooser = new FileChooser();
		File location = chooser.showSaveDialog(new Stage());
		XStream xstream = new XStream();
		String content = xstream.toXML(data);
		try {
			FileWriter fileWriter = new FileWriter(location);
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException e) {

		}
	}
}
