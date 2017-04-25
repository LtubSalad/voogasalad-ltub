package gamedata;

import newengine.sprite.Sprite;

/**
 * @author tahiaemran
 * 
 * interface used to define behavior for classes that translate data from a saved XML file 
 *
 * an implementing class should be a composition of translators, one for each direction in which translation proceeds 
 *
 */
public interface FileTranslator extends Translator<Sprite> {
	/**
	 * 
	 * method used to set the translator for translating from Authoring Data to Game Data
	 * 
	 */
	public void setTranslatorForAuthFile();
	
	/**
	 * method used to set the translator for translating from Game Data to Authoring Data
	 * 
	 * 
	 */
	public void setTranslatorForGameFile();
	
	
}
