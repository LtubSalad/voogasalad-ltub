package data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bus.BusEvent;
import javafx.util.Pair;
import newengine.events.sprite.SpriteKilledEvent;
import newengine.skill.Skill;
import newengine.sprite.Sprite;
import newengine.sprite.component.Component;
import newengine.sprite.component.ComponentType;

public class SpriteMakerModel {
	private Map<String, String> myCustomEventHandlers;
	
	private Map<ComponentType<?>,Component> myComponents;
	private Map<BusEvent, String> myScriptMap;
	private String spriteName;
	private String spriteDescription;
	
	// Jake and Tahia's DO NOT TOUCH
	private Map<String, List<String>> componentsForTransfer; 
	private List<Component> actualComponents; 
	private List<EventHandleData> myEventHandlers; 
	List<Skill> skills; 
	List<Pair<BusEvent, BusEvent>> triggers; // event + Action for the trigger (custom event handling) 	
	
	public SpriteMakerModel() {
		Map<ComponentType<?>, Component >componentMap=new HashMap<>();
		Map<BusEvent, String> handlers=new HashMap<>();
		myComponents=new HashMap<>();
		myScriptMap=new HashMap<>();
		actualComponents = new ArrayList<Component>(); 
		skills = new ArrayList<Skill>(); 
		spriteName="";
		spriteDescription="";
	}
	
	public void addName(String name) {
		spriteName = name;
	}
	
	public String getSpriteName() {
		return spriteName;
	}
	
	public String getName(){
		return spriteName;
	}
	
	public void setName(String name){
		spriteName=name;
	}

	public String getDescription(){
		return spriteDescription;
	}
	
	public void setDescription(String description){
		spriteDescription=description;
	}
	
	public void addSkill(Skill skill){
		skills.add(skill);
	}
	
	
	public void addComponent(String componentName, List<String> params) {
		//TODO: modify this method call to pass data correct data structures (Map<String, DataWrapper>) 
		if (!componentsForTransfer.keySet().contains(componentName)) {
			componentsForTransfer.put(componentName, params);
		}
	}
	
	
	/**
	 * @param componentName
	 * @param parameters
	 * 
	 * USE THIS ONE 
	 */
	public void addActualComponent(Component c){
		actualComponents.add(c);
	}
	
	
	public List<Component> getActualComponents(){
		return actualComponents; 
	}
	
	public void addEventHandler(EventHandleData eventHandler) {
		myEventHandlers.add(eventHandler);
	}
	
	public Map<ComponentType<?>, Component > getComponents() {
		return myComponents;
	}
	
	public List<EventHandleData> getEventHandlers() {
		return myEventHandlers;
	}
	
	public Map<String,List<String>> getTransferComponents() {
		return componentsForTransfer; 
	}
	
//	public SpriteMakerModel(SpriteMakerModel toCopy) {
//		this.myCustomEventHandlers = toCopy.myCustomEventHandlers;
//		this.myComponents = toCopy.myComponents;
//		this.myScriptMap = toCopy.myScriptMap;
//	}
	
	public SpriteMakerModel(String name){
		this();
		spriteName=name;
	}
	
	public void clearComponents(){
		myComponents.clear();
	}
	
	/**
	 * This code means that the sprite only has one component for each type... though
	 * you can possibly have two components of the same class with different "type".
	 * @param comp
	 */
	public void addComponent(Component comp) {
		actualComponents.add(comp);
		myComponents.put(comp.getType(), comp);
	}
	
	public void addScript(BusEvent event, String script) {
		myScriptMap.put(event, script);
	}
	
	public Map<BusEvent,String> getScriptMap() {
		return myScriptMap;
	}
	
	@Deprecated
	public Map<ComponentType<?>,Component> getDeprecatedComponents() {
		return myComponents;
	}
	
//	public void addScript(BusEvent event, String script) {
//		myScriptMap.put(event, script);
//	}
//	
//	public Map<BusEvent,String> getScriptMap() {
//		return myScriptMap;
//	}
//	
//	public ObservableMap<ComponentType<?>,Component> getComponents() {
//		return myComponents;
//	}
//	
	public Collection<BusEvent> getListenedEvents(){
		List<BusEvent> dummyList=new ArrayList<>();
		dummyList.add(new SpriteKilledEvent());
		return dummyList;
	}
	

	public Pair<String, List<String>> getComponentByType(String componentName) {
		for (String c : componentsForTransfer.keySet()) {
			if (c.equals(componentName)) {
				return new Pair<String, List<String>>(c, componentsForTransfer.get(c));
			}
		}
		return null; 
	}
	
	public Component getComponentByType(ComponentType<?> type) {
		for (Component c : actualComponents) {
			if (c.getType().equals(type)) {
				return c;
			}
		}
		return null;
	}
	
	public List<Skill> getSkills(){
		return skills; 
	}
	
	
	
//	/**
//	 * Call this AFTER unserialization to avoid the bus issues
//	 * @return
//	 */
//	public Sprite produceSprite(){
//		Sprite sprite=new Sprite();
//		myComponents.forEach((componentType, component)->{
//			sprite.addComponent(component);
//		});
//		myScriptMap.forEach((event, script)->{
//			sprite.produceHandler(event.getEventType(), script);
//		});
//		return sprite;
//	}
}
