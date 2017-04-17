package utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import data.AttributeData;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class XStreamHandler {
	//TODO: Remove duplicate code using generics
	
	public AttributeData getAttributeFromFile() {
		XStream xstream = new XStream(new DomDriver());
		FileChooser chooser = new FileChooser();
		File attributeFile = chooser.showOpenDialog(new Stage());
		AttributeData attribute = (AttributeData)xstream.fromXML(attributeFile);
		return attribute;
	}
	
	public AttributeData getAttributeFromFile(File file) {
		XStream xstream = new XStream(new DomDriver());
		AttributeData attribute = (AttributeData)xstream.fromXML(file);
		return attribute;
	}
	
	public <T> T getObjectFromFile(Class<T> clazz, File file){
		XStream xstream = new XStream(new DomDriver());
		T object = (T)xstream.fromXML(file);
		return object;
	}
	
	public List<AttributeData> getScreenModelFile() {
		XStream xstream = new XStream(new DomDriver());
		FileChooser chooser = new FileChooser();
		File attributeFile = chooser.showOpenDialog(new Stage());
		List<AttributeData> attribute = (List<AttributeData>)xstream.fromXML(attributeFile);
		return attribute;
	}
	
	public List<AttributeData> getScreenModel(File file) {
		XStream xstream = new XStream(new DomDriver());
		List<AttributeData> attribute = (List<AttributeData>)xstream.fromXML(file);
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}