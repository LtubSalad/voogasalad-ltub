package exception;

public class UnsupportedTypeException extends Exception{
	public UnsupportedTypeException(Class<?> type){
		System.out.print(type.getName());
	}

}
