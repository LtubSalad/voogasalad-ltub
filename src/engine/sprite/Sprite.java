package engine.sprite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import commons.MathUtils;
import engine.camera.GamePoint;
import engine.player.Player;
import engine.sprite.attack.Attacker;
import engine.sprite.attack.Weapon;
import engine.sprite.collision.Collidable;
import engine.sprite.health.HealthHolder;
import engine.sprite.images.ImageSet;
import engine.sprite.images.LtubImage;
import engine.sprite.movable.Movable;
import engine.sprite.spritespawner.NonSpawningSpriteSpawner;
import engine.sprite.spritespawner.SpriteSpawner;
import engine.sprite.team.Team;

public class Sprite  {

	// initialize empty image.
	private ImageSet imageSet;
	// private boolean locked = false; // TODO
	private GamePoint pos;
	private int z;

	private SelectionBound selectionBound = SelectionBound.IMAGE;
	private List<GamePoint> selectionBoundVertices;
	// composition objects 
	private Movable movable;
	private Collidable collidable;
	private Attacker attacker;
	private Weapon weapon;
	private HealthHolder healthHolder;
	private SpriteSpawner spriteSpawner;
	private Team team;


	/**
	 * The player that this sprite belongs to.
	 */
	private Player player = Player.DEFAULT;
	private ActionQueue actionQueue = new ActionQueue();

	/**
	 * sprite constructor - sets all composition elements (movable, collidable, spritefactory, health, weapon, images to default values)
	 */
	public Sprite() {
		initAttributes();
	}

	private void initAttributes(){
		movable = null;
		collidable = null;
		attacker = null;
		weapon = null;
		healthHolder = null;
		spriteSpawner = null;
		team = null;
	}

	public void setImageSet(ImageSet imageSet) {
		this.imageSet = imageSet;		
	}

	/**
	 * Return an image corresponding to the sprite at the 
	 * current frame. Could change with direction and moving distance.
	 * @return
	 */
	public LtubImage getImage() {
		// TODO: pass in angle and dist
		return imageSet.getImage(0, 0);
	}

	public GamePoint getPos() {
		return pos;
	}

	public void setPos(GamePoint pos) {
		this.pos = pos;
	}

	public void setSelectionBound(SelectionBound selectionBound) {
		this.selectionBound = selectionBound;
	}

	public SelectionBound getSelectionBound() {
		return selectionBound;
	}

	private void setSelectionBoundVertices() {
		selectionBoundVertices = new ArrayList<>();
		if (selectionBound == SelectionBound.IMAGE) {
			//			if (ltubImage != null) {
			//				// Image rectangle nodes are added in a clock-wise order
			//				selectionBoundVertices.add(this.getDisplayPos());
			//				selectionBoundVertices.add(new Point(this.getDisplayPos().x() + ltubImage.width(), this.getDisplayPos().y()));
			//				selectionBoundVertices.add(new Point(this.getDisplayPos().x() + ltubImage.width(), this.getDisplayPos().y() + ltubImage.height()));
			//				selectionBoundVertices.add(new Point(this.getDisplayPos().x(), this.getDisplayPos().y() + ltubImage.height()));
			//			}
		}
		else if (selectionBound == SelectionBound.POLYGON) {
			// TODO
		}
	}

	/**
	 * Get a list of Point indicating the definite display positions of selection bound vertices
	 * @return List<Point>
	 */
	public List<GamePoint> getSelectionBoundVertices() {
		setSelectionBoundVertices();
		return selectionBoundVertices;
	}

	public void setDetectionRange(double detectionRange) {
		attacker.setRange(detectionRange);
	}

	public double getDetectionRange() {
		return attacker.getRange();
	}


//    public Movable getMovable(){
//		return this.movable;
//	}

	public Optional<Attribute> getMovable() {
		return Optional.ofNullable(movable);
	}

	public Optional<Attribute> getCollidable() {
		return Optional.ofNullable(collidable);
	}

	public Optional<Attribute> getAttacker() {
		return Optional.ofNullable(attacker);
	}

	public Optional<Attribute> getWeapon() {
		return Optional.ofNullable(weapon);
	}

	public Optional<Attribute> getHealthHolder(){
		return Optional.ofNullable(healthHolder);
	}

	public Optional<Attribute> getSpawner(){
		return Optional.ofNullable(spriteSpawner);
	}

	public Optional<Attribute> getTeam(){
		return Optional.ofNullable(team);
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	public Player getPlayer() {
		return player;
	}
	public void executeAction(Action action) {
		actionQueue.clearQueue();
		action.execute();
	}
	public void queueAction(Action action) {
		actionQueue.addAction(action);
	}

	public void updatePos(double dt) {
		double tRemain = dt;
		// TODO: this piece of actions queueing code has to be improved

		//trigger Movable
		if (movable != null && !actionQueue.isEmpty()){
			actionQueue.executeNextAction();
		}
		while (!MathUtils.doubleEquals(tRemain, 0) && getMovable().isPresent()) {
			tRemain = movable.update(dt);
			if (!MathUtils.doubleEquals(tRemain, 0) && !actionQueue.isEmpty()) {
				actionQueue.executeNextAction();
			}
		}
	}


	public void setSpawner(SpriteSpawner spawner) {
		this.spriteSpawner = spawner; 		
	}

	public void setMovable(Movable movable){
		this.movable = movable; 
	}

	public void setCollidable(Collidable collidable){
		this.collidable = collidable;
	}
	
	public void setAttacker(Attacker attacker){
		this.attacker = attacker;
	}
	
	public void setWeapon(Weapon weapon){
		this.weapon = weapon;
	}
	
	public void setTeam(Team team){
		this.team = team;
	}	
}
