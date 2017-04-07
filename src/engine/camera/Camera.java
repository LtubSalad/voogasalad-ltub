package engine.camera;

public class Camera {

	public Camera() {
		
	}
	
	// TODO now it is a 1-1 camera, without any scaling
	public GamePoint viewToGame(ViewPoint viewPoint) {
		return new GamePoint(viewPoint.x(), viewPoint.y());
	}
	
	public ViewPoint gameToView(GamePoint gamePoint) {
		return new ViewPoint(gamePoint.x(), gamePoint.y());
	}
}
