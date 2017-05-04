package newengine.animation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.animation.Animation;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class AnimationImage {

	private static final int WIDTH    = 200;
	private static final int HEIGHT   = 200;
	public static final int NUMBER_OF_IMAGE = 6;

	public ImageView getImageView(int seed) {

		final ImageView imageView = new ImageView();
		//imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH , HEIGHT));

		int[] numberList = new int[6];
		for(int i = 0; i < NUMBER_OF_IMAGE; i++){
			numberList[i] = i;
		}

		shuffleArray(seed, numberList);


		List<Image> list = new ArrayList<>();

		for(int j =0; j < NUMBER_OF_IMAGE; j++){
			list.add(getImage(numberList[j]));
		}


		final Animation animation = new ImageAnimation(list, imageView, Duration.millis(5000), WIDTH, HEIGHT);
		animation.setCycleCount(Animation.INDEFINITE);
		animation.play();

		return imageView;
	}

	private Image getImage(int i){
		return new Image("resources/monsterImages/Bahamut" + i +".png", WIDTH, HEIGHT, true,true);
	}

	private void shuffleArray(int seed, int[] array)
	{
		int index, temp;
		Random random = new Random(seed);
		for (int i = array.length - 1; i > 0; i--)
		{
			index = random.nextInt(i + 1);
			temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		}
	}
}