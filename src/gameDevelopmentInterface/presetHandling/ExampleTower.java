package gameDevelopmentInterface.presetHandling;

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
	
	public void loadComponents(){
		this.addComponent(new Owner("TowerTeam"));
		this.addComponent(new Selectable(SelectionBoundType.IMAGE));
		//this.addComponent(new SoundEffect());
		//this.addComponent(new Images());
		this.addComponent(new Speed(20));
		this.addComponent(new Attacker());		
	}
	
	

}
