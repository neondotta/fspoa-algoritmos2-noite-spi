package game;

import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Resolution;
import com.senac.SimpleJava.Graphics.Sprite;

public class Paddle extends Sprite {

	private double deltaX = 0;

	public Paddle() {
		super(28,5, Color.BLUE);
	}

	public void move(Resolution res) {
		Point pos = getPosition();
		if (pos.x + getWidth() + deltaX > res.width) {
			stop();
		}
		if (pos.x + deltaX < 0) {
			stop();
		}
		move(deltaX , 0);
	}

	public void stop() {
		deltaX = 0;
	}

	public void moveRight() {
		deltaX = +3;
	}

	public void moveLeft() {
		deltaX = -3;
	}

}
