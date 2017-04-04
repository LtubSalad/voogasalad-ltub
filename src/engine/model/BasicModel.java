package engine.model;

import engine.sprite.KepingSprite;
import engine.view.View;

public class BasicModel implements Model {

	private View view;

	@Override
	public void setView(View view) {
		this.view = view;
	}

	@Override
	public void addSprite(KepingSprite sprite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeSprite(KepingSprite sprite) {
		// TODO Auto-generated method stub
		
	}

}
