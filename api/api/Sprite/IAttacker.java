package api.Sprite;

/**
 * This is the interface that needs to be implemented by the Attacker components
 * of the Sprite. This interface is used to set forward expectations for the
 * methods that will be employed by the component to provide a wide range of
 * varieties of attack. For example, a concrete implementation that will not
 * attack will have an empty method and one that has a long range attack will
 * have a longer range and do a long range attack.
 * @author Matthew Tribby
 *
 */
public interface IAttacker {
		/**
		 * Attacks the sprite. This will mean different things for different
		 * types of sprites, whether they are fighters or not
		 * @param s Sprite to be attacked
		 */
		public void attack(Sprite s);
}
