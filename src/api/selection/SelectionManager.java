package api.selection;

import java.util.List;

/**
 * Basically a storage to indicate what sprites are currently selected.
 * @author keping
 *
 */
public interface SelectionManager {

	/**
	 * return currently selected objects. could be a list.
	 * @return
	 */
	public List<Object> getSelected();
	
	
}
