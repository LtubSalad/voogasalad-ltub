/**
 * 
 */
package player.levelChoice;

/**
 * @author Zhiyong
 *
 */
public class LanguageSettingMenuHandler extends SettingMenuHandler {
	
	private String language;

	public LanguageSettingMenuHandler(String language) {		
		super(language);
		
		this.language = language;
	}

	@Override
	public void handle() {
		// TODO change the language of the backend play
		System.out.println(language);
		
	}
	
	public String getLanguage(){
		return language;
	}

}
