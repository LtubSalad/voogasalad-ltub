package data;

import java.util.List;
import java.util.Map;

public interface AttributeHolderData {
	public Map<String,String> getVariables();
	public List<AttributeHolderData> subHolders();
}
