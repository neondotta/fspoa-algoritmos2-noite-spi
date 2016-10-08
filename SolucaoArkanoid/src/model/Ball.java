package model;

import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Rect;
import com.senac.SimpleJava.Graphics.Sprite;

public class Ball extends Sprite {

	public static final int COLISAO_NENHUMA = 0;
	public static final int COLISAO_VERTICAL = 1;
	public static final int COLISAO_HORIZONTAL = 2;

	private double deltaX = 1.5;
	private double deltaY = 1.5;

	public Ball() {
		super(3, 3, Color.BLACK);
	}

	public void move() {
		move(deltaX,  deltaY);
	}

	public void invertX() {
		deltaX *= -1;
	}

	public void invertY() {
		deltaY *= -1;
	}

	public int verificaColisao(Rect obj) {
		Rect ball = getBounds();
		if (ball.x + ball.width < obj.x ||
			ball.y + ball.height < obj.y ||
			ball.x > obj.x + obj.width ||
			ball.y > obj.y + obj.height)
		{
			return COLISAO_NENHUMA;
		}
		
		if (ball.x + ball.width > obj.x &&
				ball.x < obj.x + obj.width)
		{
			return COLISAO_VERTICAL;
		}
		if (ball.y + ball.height > obj.y &&
				ball.y < obj.y + obj.height)
		{
			return COLISAO_HORIZONTAL;
		}
		return COLISAO_NENHUMA;
	}
}
