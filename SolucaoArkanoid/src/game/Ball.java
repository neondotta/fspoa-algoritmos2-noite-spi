package game;

import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Rect;
import com.senac.SimpleJava.Graphics.Sprite;

public class Ball extends Sprite {

	public static final int SEM_COLISAO = 0;
	public static final int COLISAO_VERTICAL = 1;
	public static final int COLISAO_HORIZONTAL = 2;
	
	private double deltaX = 1.5;
	private double deltaY = 1.5;

	public Ball() {
		super(3,3,Color.BLACK);
	}

	public void move() {
		super.move(deltaX, deltaY);
	}

	public boolean colidiuInterno(Rect area) {
		Rect bounds = getBounds(); 
		if (bounds.x <= area.x) {
			inverteX();
			return true;
		}
		if (bounds.x + bounds.width >= area.x + area.width) {
			inverteX();
			return true;
		}
		if (bounds.y <= area.y) {
			inverteY();
			return true;
		}
		if (bounds.y + bounds.height >= area.y + area.height) {
			inverteY();
			return true;
		}
		return false;
	}

	public void inverteY() {
		deltaY *= -1;
	}

	public void inverteX() {
		deltaX *= -1;
	}

	public int colidiuExterno(Rect obj) {
		Rect bola = getBounds();
		if (bola.x + bola.width < obj.x) {
			return 0;
		}
		if (bola.x > obj.x + obj.width) {
			return 0;
		}
		if (bola.y + bola.height < obj.y) {
			return 0;
		}
		if (bola.y > obj.y + obj.height) {
			return 0;
		}
		
		if (bola.x + bola.width > obj.x && bola.x < obj.x + obj.width) {
			return COLISAO_VERTICAL;
		} else {		
			return COLISAO_HORIZONTAL;
		}
	}

}
