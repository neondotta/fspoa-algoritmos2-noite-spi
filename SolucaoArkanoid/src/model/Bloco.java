package model;

import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Sprite;

public class Bloco extends Sprite {

	private int vidas;

	public Bloco(int x, int y, Color cor, int vidas) {
		super(18, 12, cor);
		setPosition(x,y);
		this.vidas = vidas;
	}

	public void hit() {
		vidas--;
	}

	@Override
	public void draw(Canvas canvas) {
		if (isVivo())
			super.draw(canvas);
	}

	public boolean isVivo() {
		return (vidas > 0);
	}
}
