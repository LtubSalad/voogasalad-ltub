package newengine.animation;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ImageAnimationTest {

	private static final int WIDTH    = 200;
	private static final int HEIGHT   = 200;

	private static final Image IMAGE = new Image("resources/gold.jpeg", WIDTH, HEIGHT, true,true);
	private static final Image IMAGE1 = new Image("resources/health.jpg", WIDTH, HEIGHT, true,true);
	private static final Image IMAGE2 = new Image("resources/bahamut_left.png", WIDTH, HEIGHT, true,true);
	private static final Image IMAGE3 = new Image("resources/bahamut_right.png", WIDTH, HEIGHT, true,true);

	public ImageView getImageView() {

		final ImageView imageView = new ImageView();
		//imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH , HEIGHT));

		List<Image> list = new ArrayList<>();
		list.add(IMAGE);
		list.add(IMAGE1);
		list.add(IMAGE2);
		list.add(IMAGE3);


		final Animation animation = new ImageAnimation(
				list,
				imageView,
				Duration.millis(1000),
				WIDTH, HEIGHT
				);
		animation.setCycleCount(Animation.INDEFINITE);
		animation.play();

		return imageView;
	}
}