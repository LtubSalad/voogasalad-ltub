package gameDevelopmentInterface;

import data.AttributeData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javafx.util.Pair;

public class AttributeDataFactory {
	public AttributeData produceAttribute(File file){
		try{	
			DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse(file);
			return produceAttributeHelper(doc.getFirstChild());
		}
		//TODO Handle exceptions properly later
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private AttributeData produceAttributeHelper(Node element){
		String name=element.getNodeName();
		AttributeData fullAttribute=new AttributeData(name);
		NamedNodeMap elementAttributes=element.getAttributes();
		for(int i=0;i<elementAttributes.getLength();i++){
			String attributeKey=elementAttributes.item(i).getNodeName();
			if(attributeKey.startsWith("function")){
				String[] nameAndParameters=attributeKey.split("_");
				String functionName=nameAndParameters[1];
				List<String> parameters=new ArrayList<>();
				for(int j=2;j<nameAndParameters.length;j++){
					parameters.add(nameAndParameters[j]);
				}
				fullAttribute.getScripts().put(new Pair<>(functionName,parameters), elementAttributes.item(i).getNodeValue());
			}
			else if (attributeKey.startsWith("variable")){
				String[] variableTypeAndName=attributeKey.split("_");
				fullAttribute.getVariables().put(variableTypeAndName[1], elementAttributes.item(i).getNodeValue());
			}
			else{
				//TODO handle this properly later on
				System.out.print("unrecognized data");
			}
		}
		
		NodeList childNodes=element.getChildNodes();
		for(int i=0;i<childNodes.getLength();i++){
			if(childNodes.item(i).getNodeType()==Node.ELEMENT_NODE){
				fullAttribute.addAttributeData(produceAttributeHelper(childNodes.item(i)));
			}
		}
		return fullAttribute;	
	}

}
