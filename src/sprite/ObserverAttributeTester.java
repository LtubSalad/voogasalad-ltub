package sprite;

import java.util.Observer;

public class ObserverAttributeTester {
	static ObserverAttribute OA = new ObserverAttribute();
	public static void main (String[] args){
		OA.getAttributeInterface(OA.getClass().getInterfaces()); 
		System.out.println(OA instanceof Observer);
	}
	
}
