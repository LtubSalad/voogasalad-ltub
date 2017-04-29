package newengine.sprite.components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bus.BusEventHandler;
import helperAnnotations.ConstructorForDeveloper;
import helperAnnotations.DeveloperMethod;
import helperAnnotations.VariableName;
import newengine.events.SpriteModelEvent;
import newengine.events.collision.CollisionEvent;
import newengine.events.sprite.ChangeHealthEvent;
import newengine.events.sprite.FireProjectileEvent;
import newengine.events.sprite.MoveEvent;
import newengine.events.stats.ChangeWealthEvent;
import newengine.model.PlayerStatsModel.WealthType;
import newengine.player.Player;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;
import newengine.utils.Target;

public class Health extends Component {

	public static final ComponentType<Health> TYPE = new ComponentType<>(Health.class.getName());
	private int health;

	@ConstructorForDeveloper
	public Health(@VariableName(name="health")int health){
		this.health = health;
	}
	
	@Override
	public ComponentType<? extends Component> getType() {
		return TYPE;
	}
	
	public int getHealth(){
		return health;
	}
	
	@Override
	public void afterAdded(){
		sprite.on(ChangeHealthEvent.ANY, e -> {
			changeHealth(e.getChange());
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
		System.out.println(health);
		checkForDeath();
	}
	
	private void checkForDeath() {
		if (health <= 0) {
			List<Sprite> spritesToRemove = new ArrayList<>();
			spritesToRemove.add(sprite);
			sprite.getComponent(GameBus.TYPE).ifPresent((gameBus) -> {
				gameBus.getGameBus().emit(new SpriteModelEvent(SpriteModelEvent.REMOVE, spritesToRemove));
			});
		}
	}
	
	@Override
	public Component clone() {
		return new Health(health);
	}

}
