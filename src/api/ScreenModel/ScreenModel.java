package api.ScreenModel;

import java.util.Collection;

public interface ScreenModel {
	public Collection<Object> getReadOnlySprites(); 
	public void addGameObject(Object gameObject);
	public Collection<Object> getMonsters(); 
}
