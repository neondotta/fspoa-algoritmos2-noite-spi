package game;

import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.Drawable;

public class Estagio implements Drawable {
	private Bloco[] blocos;
	private int countBlocos;

	public Estagio(int numBlocos) {
		blocos = new Bloco[numBlocos];
		countBlocos = 0;
	}

	public int getBlocoCount() {
		return blocos.length;
	}
	
	protected void addBloco(Bloco bloco) {
		if (countBlocos < blocos.length) {
			blocos[countBlocos] = bloco;
			countBlocos++;
		}
	}

	@Override
	public void draw(Canvas canvas) {
		for (Bloco b : blocos) {
			b.draw(canvas);
		}
	}

	public int verificaBlocos(Ball ball) {
		int score = 0;
		for (Bloco b : blocos) {
			if (!b.isVivo()) {
				continue;
			}
			int colisao = ball.colidiuExterno(b.getBounds());
			if (colisao != Ball.SEM_COLISAO) {
				if (b.hit()) {
					score += 100;
				}
				if (colisao == Ball.COLISAO_VERTICAL) {
					ball.inverteY();
				} else {
					ball.inverteX();
				}
			}
		}
		return score;
	}

}
