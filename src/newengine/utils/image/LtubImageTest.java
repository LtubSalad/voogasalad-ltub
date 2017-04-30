package newengine.utils.image;

import java.io.InputStream;

public class LtubImageTest {

	public InputStream getInputStream() {
		return getClass().getClassLoader().getResourceAsStream("images/skills/build.png");
	}
	
	public static void main(String[] args) {
		LtubImageTest test = new LtubImageTest();
		System.out.println(test.getInputStream());
	}
}
