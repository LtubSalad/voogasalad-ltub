/**
 * 
 */
package imageprocess;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

import javafx.scene.image.Image;

/**
 * @author Zhiyong
 *
 */
public class Collision extends ImageTransformation{
	

// Returns true if there is a collision between object a and object b	
	public boolean checkCollision(Image a,int xPos1, int yPos1, Image b, int xPos2, int yPos2){
		
//		// This method detects to see if the images overlap at all. If they do, collision is possible
//		int ax1 = a.getxPos();
//		int ay1 = a.getyPos();
//		int ax2 = ax1 + a.getWidth();
//		int ay2 = ay1 + a.getHeight();
//		int bx1 = b.getxPos();
//		int by1 = b.getyPos();
//		int bx2 = bx1 + b.getWidth();
//		int by2 = by1 + b.getHeight();
//		
//		if(by2 < ay1 || ay2 < by1 || bx2 < ax1 || ax2 < bx1)
//		{
//			return false; // Collision is impossible.
//		}
//		else // Collision is possible.
//		{
//			// get the masks for both images
//			HashSet<String> maskPlayer1 = getMask(player1);
//			HashSet<String> maskPlayer2 = getMask(player2);
//		
//			maskPlayer1.retainAll(maskPlayer2);  // Check to see if any pixels in maskPlayer2 are the same as those in maskPlayer1
//			
//			if(maskPlayer1.size() > 0){  // if so, than there exists at least one pixel that is the same in both images, thus
//				System.out.println("Collision" + count);//  collision has occurred.
//				count++;
//				return true;
//				
//				}
//			}
			return false;	
		}
}


