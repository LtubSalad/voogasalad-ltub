package newengine.gamevariable;

public class Var<T> {

	private T t;
	
	public Var() {
		t = null;
	}
	public Var(T t) {
		this.t = t;
	}
	
	public T get() {
		return t;
	}
	public void set(T t) {
		this.t = t;
	}
	
	
}
