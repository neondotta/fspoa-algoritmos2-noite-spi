package game;

import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.GraphicApplication;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Rect;
import com.senac.SimpleJava.Graphics.Resolution;
import com.senac.SimpleJava.Graphics.events.KeyboardAction;

public class Arkanoid extends GraphicApplication {

	private final Resolution res = Resolution.MSX;
	private final int FPS = 60; 
	
	private Ball ball;
	private Paddle paddle;

	private String mensagem = "";
	private int tempoMensagem = 0;
	private int paddleHit = 0;

	private int score = 0;
	private int vidas = 0;
	
	private boolean gameOver = true;
	private Estagio estagio;

	@Override
	protected void setup() {
		setResolution(res);
		setFramesPerSecond(FPS);
		ball = new Ball();
		ball.setPosition(80,120);
		paddle = new Paddle();
		paddle.setPosition(res.width / 2 + 12, res.height - 10);

		estagio = new EstagioUm();
		
		configureKeyboard();
	}

	private void configureKeyboard() {
		bindKeyPressed("LEFT", new KeyboardAction() {
			public void handleEvent() {
				paddle.moveLeft();
			}
		});
		bindKeyReleased("LEFT", new KeyboardAction() {
			public void handleEvent() {
				paddle.stop();
			}
		});
		bindKeyPressed("RIGHT", new KeyboardAction() {
			public void handleEvent() {
				paddle.moveRight();
			}
		});
		bindKeyReleased("RIGHT", new KeyboardAction() {
			public void handleEvent() {
				paddle.stop();
			}
		});
		bindKey("SPACE", new KeyboardAction() {
			public void handleEvent() {
				iniciarJogo();
			}
		});
	}

	@Override
	protected void draw(Canvas canvas) {
		canvas.clear();
		String status = "Score: %06d";
		canvas.putText(30, 2, 10, String.format(status, score));

		for (int i = 0; i < vidas; i++) {
			canvas.putText(120 + i*20, 2, 10, "*-*");
		}
		
		if (!mensagem.isEmpty()) {
			canvas.putText(80, 110, 12, mensagem);
		}
		
		paddle.draw(canvas);
		ball.draw(canvas);
		
		if (estagio != null)
			estagio.draw(canvas);
	}

	@Override
	protected void loop() {
		if (gameOver) {
			return;
		}
		
		ball.move();

		countMessageTime();
		
		moveBall();
		movePaddle();
		
		if (paddleHit <= 0 && ball.colidiuExterno(paddle.getBounds()) > 0) {
			paddleHit  = 4;
			ball.inverteY();
		} else {
			paddleHit--;
		}
		
		estagio.verificaBlocos(ball);
		
		redraw();
	}
	
	private void movePaddle() {
		paddle.move(res);
	}

	private void moveBall() {
		Point pos = ball.getPosition();
		if (pos.y + ball.getHeight() >= res.height) {
			geraMorte();
		} else {
			Rect area = new Rect(0,0,res.width,res.height);  
			ball.colidiuInterno(area);
		}
	}
	
	private void countMessageTime() {
		if (!mensagem.isEmpty()) {
			tempoMensagem--;
			if (tempoMensagem == 0) {
				mensagem = "";
			}
		}		
	}

	private void geraMorte() {
		// TODO: decrementa numero vidas.
		mensagem  = "voce morreu";
		tempoMensagem  = 3 * FPS;
		vidas--;
		
		if (vidas < 0) {
			mensagem = "Game Over";
			gameOver = true;
			bindKey("SPACE", new KeyboardAction() {
				public void handleEvent() {
					iniciarJogo();
				}
			});
		}
		// TODO: Remover antes de entregar.
		ball.inverteY();
	}


	private void iniciarJogo() {
		gameOver = false;
		ball.setPosition(80,110);
		vidas = 3;
		score = 0;
		bindKey("SPACE", new KeyboardAction() { public void handleEvent() { } });
		
		if (estagio == null) {
			estagio = new EstagioUm();
		}
	}
}
