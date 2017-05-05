package player.levelChoice;

/**
 * @author Zhiyong
 *Set the MenuItem for the language choice
 */
public class LanguageSettingMenuHandler extends SettingMenuHandler {
	
	private String language;

	/**
	 * @param language
	 * Set the name of the item as the language
	 */
	public LanguageSettingMenuHandler(String language) {		
		super(language);		
		this.language = language;
	}

	@Override
	public void handle() {
		
	}
	
	public String getLanguage(){
		return language;
	}

}
