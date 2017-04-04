package engine.model;

import java.util.List;
import static commons.RunningMode.DEV_MODE;

import engine.gameloop.LoopComponent;
import engine.sprite.Sprite;
import engine.view.View;

public class BasicModel implements Model {

	
	private List<Sprite> sprites;
	private View view;

	@Override
	public void setView(View view) {
		this.view = view;
	}

	@Override
	public void addSprite(Sprite sprite) {
		if (view == null && DEV_MODE) {
			System.out.println("View is null");
		}
		if (view != null) {
			view.addSprite(sprite);
		}
	}

	@Override
	public void removeSprite(Sprite sprite) {
		// TODO Auto-generated method stub
	}

	@Override
	public LoopComponent getLoopComponent() {
		return (dt) -> {
			for (Sprite sprite : sprites) {
				sprite.update(dt);
			}
		};
	}

}
