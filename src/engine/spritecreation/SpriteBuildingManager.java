package engine.spritecreation;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class SpriteBuildingManager {
	private static final DocumentBuilder D_BUILDER = getDocumentBuilder();
	public static final String SPRITE_TAG = "Sprite";

	public SpriteBuildingManager(String filePath) {
		File xmlFile = new File(filePath);
		Document doc;
		try {
			doc = D_BUILDER.parse(xmlFile);
			//There is a reason we normalize
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName(SPRITE_TAG);
			for(int i = 0; i < nList.getLength(); i++){
				SpriteBuilder builder = new SpriteBuilder(D_BUILDER, nList.item(i));
			}
		} 
		catch (Exception e) {
			throw new SpriteCreationException("Unable to create Document Builder in SpriteBuildingManager");
		}
	}

	private static DocumentBuilder getDocumentBuilder () {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		}
		catch (Exception e) {
			throw new SpriteCreationException("Unable to create Document Builder in SpriteBuildingManager");
		}
	}

}
