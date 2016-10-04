package estagios;

import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.Drawable;

import model.Ball;
import model.Bloco;

abstract
public class Estagio implements Drawable {

	private Bloco[] blocos;
	private int numBlocos = 0;
	
	protected Estagio(int maxBlocos) {
		blocos = new Bloco[maxBlocos];
	}
	
	@Override
	public void draw(Canvas canvas) {
		for (Bloco b : blocos)
			b.draw(canvas);
	}
	
	protected void addBloco(Bloco bloco) {
		if (numBlocos < blocos.length) {
			blocos[numBlocos] = bloco;
			numBlocos++;
		}
	}

	public Bloco[] getBlocos() {
		return blocos;
	}

	public boolean verificarColisao(Ball ball) {
		for (Bloco b : blocos) {
			if (!b.isVivo())
				continue;
			switch (ball.verificaColisao(b.getBounds())) {
			case Ball.COLISAO_NENHUMA: break;
			case Ball.COLISAO_VERTICAL:
				b.hit();
				ball.invertY();
				return true;
			case Ball.COLISAO_HORIZONTAL:
				b.hit();
				ball.invertX();
				return true;
			}
		}
		return false;
	}

}
