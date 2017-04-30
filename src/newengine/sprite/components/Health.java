package newengine.sprite.components;

import java.util.ArrayList;
import java.util.List;

import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.DeveloperMethod;
import helperAnnotations.VariableName;
import newengine.events.SpriteModelEvent;
import newengine.events.sprite.ChangeHealthEvent;
import newengine.events.sprite.MoveEvent;
import newengine.events.sprite.StateChangeEvent;
import newengine.events.sprite.UpgradeEvent;
import newengine.events.stats.ChangeWealthEvent;
import newengine.model.PlayerStatsModel.WealthType;
import newengine.player.Player;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.Target;

public class Health extends Component {

	public static final ComponentType<Health> TYPE = new ComponentType<>(Health.class.getName());
	private int initHealth;
	private int health;

	@ConstructorForDeveloper
	public Health(@VariableName(name="health")int health){
		this.initHealth = health;
		this.health = health;
		
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}
	
	public int getHealth(){
		return health;
	}
	
	public int getInitHealth(){
		return initHealth;
	}
	
	@Override
	public void afterAdded(){
		sprite.emit(new StateChangeEvent(StateChangeEvent.HEALTH, sprite, health));
		sprite.on(ChangeHealthEvent.ANY, e -> {
			changeHealth(e.getValue());
		});
		sprite.on(UpgradeEvent.RESET, e -> {
			health = e.getValue();
			sprite.emit(new StateChangeEvent(StateChangeEvent.HEALTH, e.getSprite(), health));
			System.out.println("health was upgraded and reset to " + e.getValue());
		});
		sprite.on(MoveEvent.STOP, (e) -> {
			if (!e.getSprite().getComponent(Weapon.TYPE).isPresent()){
				return;
			}
			if (!e.getTarget().getSprite().isPresent()){
				return;
			}
			Sprite weapon = e.getSprite();
			Sprite target = e.getTarget().getSprite().get();
			weapon.getComponent(Owner.TYPE).ifPresent((weaponOwner) -> {
					if (weaponOwner.player() != target.getComponent(Owner.TYPE).get().player()) {
						weapon.getComponent(DamageStrength.TYPE).ifPresent((damageStrength) -> {
							int damage = damageStrength.getStrength();
							sprite.emit(new ChangeHealthEvent(ChangeHealthEvent.ANY, -damage));
							System.out.println("health decremented");
						});				
					}
				});	
			// TODO check collision with other monster-type sprites
		});	
	}

	@DeveloperMethod
	private void changeHealth(int change) {
		health += change;
		sprite.emit(new StateChangeEvent(StateChangeEvent.HEALTH, sprite, health));
		checkForDeath();
	}
	
	private void checkForDeath() {
		if (health <= 0) {
			List<Sprite> spritesToRemove = new ArrayList<>();
			spritesToRemove.add(sprite);
			sprite.getComponent(GameBus.TYPE).ifPresent((gameBus) -> {
				gameBus.getGameBus().emit(new SpriteModelEvent(SpriteModelEvent.REMOVE, spritesToRemove));
				System.out.println("REMOVE SPRITE " + spritesToRemove);
			});
		}
	}
	
	@Override
	public Component clone() {
		return new Health(health);
	}

	@Override
	public Object[] getParameters() {
		Object[] parameters=new Object[1];
		parameters[0]=health;
		return parameters;
	}

}
