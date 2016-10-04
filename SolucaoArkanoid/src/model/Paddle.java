package model;

import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Sprite;

public class Paddle extends Sprite {

	private int delta = 0;

	public Paddle() {
		super(25, 5, Color.RED);
	}

	public void moveRight() {
		delta  = +3;
	}

	public void stop() {
		delta = 0;
	}

	public void moveLeft() {
		delta = -3;
	}

	public void move() {
		move(delta, 0);
	}

	public boolean isMovingLeft() {
		return delta < 0;
	}

	public boolean isMovingRight() {
		return delta > 0;
	}

}
