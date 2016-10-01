package game;

import com.senac.SimpleJava.Graphics.Color;

public class EstagioUm extends Estagio {

	private static final int BLOCOS_POR_LINHA = 10;

	public EstagioUm() {
		super(50);
		Color[] cores = { Color.GRAY, Color.RED, Color.YELLOW,
				Color.GREEN, Color.CYAN };
		for (int i = 0; i < getBlocoCount(); i++) {
			int linha = i / BLOCOS_POR_LINHA;
			int y = 20 + linha * 14;
			int x = 20 + (i % BLOCOS_POR_LINHA) * 23;
			addBloco(new Bloco(x,y,cores[linha]));
		}
	}

}
