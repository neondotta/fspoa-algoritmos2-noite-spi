package game;

import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.GraphicApplication;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Rect;
import com.senac.SimpleJava.Graphics.Resolution;
import com.senac.SimpleJava.Graphics.events.KeyboardAction;

import estagios.Estagio;
import estagios.EstagioUm;
import model.Ball;
import model.Paddle;

public class Arkanoid extends GraphicApplication {

	private final Resolution resolution = Resolution.MSX;
	private static final int FPS = 60;
	
	private Ball ball;

	private int vida = 3;
	private String mensagem = "";
	private int tempoExibicao;
	private Paddle paddle;
	private int paddleHit;
	
	private Estagio[] estagios;
	private int atual = 0;
	
	private int score = 0;
	private int highestScore = 1000;

	@Override
	protected void setup() {
		setResolution(resolution);
		setFramesPerSecond(FPS);
		
		ball = new Ball();
		paddle = new Paddle();
		paddle.setPosition(resolution.width / 2,
						   resolution.height - 10);
		
		estagios = new Estagio[3];
		for (int i = 0; i < estagios.length; i++)
			estagios[i] = new EstagioUm();
		
		configuraTeclado();
	}

	private void configuraTeclado() {
		bindKeyPressed("RIGHT", new KeyboardAction() {
			@Override
			public void handleEvent() {
				paddle.moveRight();
			}
		});
		bindKeyReleased("RIGHT", new KeyboardAction() {
			@Override
			public void handleEvent() {
				paddle.stop();
			}
		});
		bindKeyPressed("LEFT", () -> paddle.moveLeft() );
		bindKeyReleased("LEFT", ()->paddle.stop() );
	}

	@Override
	protected void draw(Canvas canvas) {
		canvas.clear();
		
		String texto =
				String.format("Score: %d   Hi Score: %d",
							 score, highestScore);
		canvas.putText(20, 5, 10, texto);
		
		if (!mensagem.isEmpty()) {
			canvas.putText(80, 120, 12, mensagem);
		}
		
		ball.draw(canvas);
		paddle.draw(canvas);
		estagios[atual].draw(canvas);
	}

	@Override
	protected void loop() {
		
		if (!mensagem.isEmpty()) {
			tempoExibicao--;
			if (tempoExibicao == 0) {
				mensagem = "";
			}
		}
		
		ball.move();
		verificaColisaoComParedes();
		
		verificaSePaddlePodeMover();
		paddle.move();

		verificaColisaoPaddle();
		
		if (estagios[atual].verificarColisao(ball)) {
			score += 100;
			if (score > highestScore) {
				highestScore = score;
			}
		}
		
		redraw();
	}

	private void verificaColisaoPaddle() {
		if (paddleHit <= 0) {
			switch (ball.verificaColisao(paddle.getBounds()))
			{
			case Ball.COLISAO_NENHUMA: break;
			default:
				paddleHit = paddle.getHeight();
				ball.invertY();
			}
		} else { paddleHit--; }
	}


	private void verificaSePaddlePodeMover() {
		if (colidiuParedeEsquerda(paddle.getBounds())
				&& paddle.isMovingLeft())
		{
			paddle.stop();
		}
		if (colidiuParedeDireita(paddle.getBounds())
				&& paddle.isMovingRight())
		{
			paddle.stop();
		}
	}

	private void verificaColisaoComParedes() {
		if (colidiuLateral(ball.getBounds())) {
			ball.invertX();
		}
		if (colidiuTopo(ball)) {
			ball.invertY();
		}
		if (colidiuFundo(ball)) {
			perdeVida();
			// debug
			ball.invertY();
		}
	}

	private void perdeVida() {
		vida--;
		if (vida == 0) {
			// TODO: tratar game over
			mostraMensagem("Game Over", 2);
		} else {
			mostraMensagem("Voce Morreu", 2);
		}
	}

	private void mostraMensagem(String msg, int sec) {
		mensagem = msg;
		tempoExibicao = sec * FPS;
	}

	private boolean colidiuFundo(Ball ball2) {
		Point pos = ball.getPosition();
		return (pos.y > resolution.height);
	}

	private boolean colidiuTopo(Ball ball) {
		Point pos = ball.getPosition();
		return (pos.y <= 0);
	}

	private boolean colidiuLateral(Rect bounds) {
		return colidiuParedeEsquerda(bounds) ||
				colidiuParedeDireita(bounds);
	}

	private boolean colidiuParedeEsquerda(Rect bounds) {
		return (bounds.x <= 0);
	}

	private boolean colidiuParedeDireita(Rect bounds) {
		return (bounds.x + bounds.width >= resolution.width);
	}

}
