package gamecreation;

public interface DataWrapper {
	
	public String getType();
	
	public Object get();

	public Class<?> checkType();
}
