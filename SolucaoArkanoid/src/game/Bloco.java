package game;

import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Sprite;

public class Bloco extends Sprite {

	private int vidas;

	public Bloco(int x, int y, Color background) {
		super(22, 10, background);
		setPosition(x,y);
		vidas = 1;
	}

	public boolean hit() {
		if (!isVivo()) {
			return false;
		}
		vidas--;
		return !isVivo();
	}

	public boolean isVivo() {
		return vidas > 0;
	}

	@Override
	public void draw(Canvas canvas) {
		if (isVivo())
			super.draw(canvas);
	}
	
}
