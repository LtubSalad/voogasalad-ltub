package commons;

public class MathUtils {

	private static final double DOUBLE_EQUAL_DELTA = 1e-6;
	
	private MathUtils() { }
	
	public static boolean doubleEquals(double num1, double num2) {
		double diff = num1 - num2;
		return (- DOUBLE_EQUAL_DELTA) < diff && diff < DOUBLE_EQUAL_DELTA;
	}
	
}
