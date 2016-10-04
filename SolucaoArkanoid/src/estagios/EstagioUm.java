package estagios;

import com.senac.SimpleJava.Graphics.Color;

import model.Bloco;

public class EstagioUm extends Estagio {
	
	private static final int NUM_BLOCOS_LINHA = 10;

	public EstagioUm() {
		super(50);
		Color[] cores = {Color.GRAY, Color.YELLOW,
				Color.GREEN, Color.BLUE, Color.RED}; 
		for (int i = 0; i < 50; i++) {
			int linha = i / NUM_BLOCOS_LINHA;
			int x = 30 + 20 * (i % NUM_BLOCOS_LINHA);
			int y = 20 + linha * 14;
			int vidas = (linha == 0) ? 2 : 1; 
			Bloco b = new Bloco(x, y, cores[linha], vidas);
			addBloco(b);
		}
	}
}
