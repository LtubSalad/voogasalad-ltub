package newengine.model;

import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import bus.EventBus;
import newengine.events.SpriteModelEvent;
import newengine.events.game.GameLoadEvent;
import newengine.events.game.GameSaveEvent;
import newengine.sprite.Sprite;

/**
 * A container for all the models. For easier data query.
 * @author keping
 *
 */
public class Models {

	private EventBus bus;
	private SpriteModel spriteModel;
	private PlayerStatsModel playerStatsModel;
	private SelectionModel selectionModel;
	private final PlayerRelationModel playerRelationModel;
	
	private XStream xstream = new XStream(new DomDriver());
	private List<Sprite> spritesCopy = new ArrayList<>();
	private List<String> savedSpriteXMLs = new ArrayList<>();
	private String savedSpritesXML;
	
	public Models(EventBus bus, SpriteModel spriteModel, PlayerStatsModel playerStatsModel, 
			PlayerRelationModel playerRelationModel, SelectionModel selectionModel) {
		this.bus = bus;
		this.spriteModel = spriteModel;
		this.playerStatsModel = playerStatsModel;
		this.playerRelationModel = playerRelationModel;
		this.selectionModel = selectionModel;
		initHandlers();
	}
	
	private void initHandlers() {
		bus.on(GameSaveEvent.SAVE, (e) -> {
			System.out.println("Game save event received");
			saveGame();
		});
		bus.on(GameLoadEvent.LOAD, (e) -> {
			System.out.println("Game load event received");
			loadGame();
		});
	}
	
	private void saveGame() {
		xstream.autodetectAnnotations(true);
		xstream.allowTypes(new Class[] {SerializedLambda.class});
		spritesCopy = new ArrayList<>();
		for (Sprite sprite : spriteModel.getSprites()) {
			spritesCopy.add(sprite.clone());
		}
		savedSpritesXML = xstream.toXML(spritesCopy);
	}
	private void loadGame() {
		List<Sprite> loadedSprites = (List<Sprite>) xstream.fromXML(savedSpritesXML);
		System.out.println("size of loaded sprites: "+loadedSprites.size());
		bus.emit(new SpriteModelEvent(SpriteModelEvent.REMOVE, spriteModel.getSprites()));
		System.out.println("let me debug the loaded sprites");
		bus.emit(new SpriteModelEvent(SpriteModelEvent.ADD, loadedSprites));
	}
 	
	public EventBus bus() {
		return bus;
	}
	public SpriteModel spriteModel() {
		return spriteModel;
	}
	public PlayerStatsModel playerStatsModel() {
		return playerStatsModel;
	}
	public PlayerRelationModel playerRelationModel() {
		return playerRelationModel;
	}
	public SelectionModel selectionModel() {
		return selectionModel;
	}
	
}
