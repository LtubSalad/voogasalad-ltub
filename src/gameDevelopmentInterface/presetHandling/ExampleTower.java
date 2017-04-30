package gameDevelopmentInterface.presetHandling;

import java.io.File;

import data.SpriteMakerModel;
import newengine.sprite.components.Attacker;
import newengine.sprite.components.Images;
import newengine.sprite.components.Owner;
import newengine.sprite.components.Selectable;
import newengine.sprite.components.Selectable.SelectionBoundType;
import newengine.sprite.components.SoundEffect;
import newengine.sprite.components.Speed;

public class ExampleTower extends SpriteMakerModel {
	public ExampleTower(){
		loadComponents();
	}
	
	private void loadComponents(){
		this.addComponent(new Owner("team 1"));
		this.addComponent(new Selectable(SelectionBoundType.IMAGE));
		this.addComponent(new SoundEffect(new File("sounds\\Dnpisd1.wav")));
		this.addComponent(new Images("images\\Mario.jpg"));
		this.addComponent(new Speed(20));
		this.addComponent(new Attacker());
		
	}
	
	

}
