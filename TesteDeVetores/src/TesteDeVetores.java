import com.senac.SimpleJava.Console;

public class TesteDeVetores {
	public void run() {
		Console.println("Testes de Vetores");
		
		Vetor<Integer> vetor = new Vetor<>();
		
		vetor.append(1);
		vetor.append(2);
		vetor.append(3);
		vetor.append(5);
		
		vetor.insert(2, 20);
	
		for (int i = 0; i < vetor.size(); i++) {
			Console.println(i, ": ", vetor.get(i));
		}

		System.out.println("==============");

		int soma = 0;
		for (int i = 0; i < vetor.size(); i++) {
			soma += vetor.get(i);
		}
		System.out.println("Soma = " + soma);
		
		System.out.println("==============");
		
		vetor.remove(2);
		
		for (int i = 0; i < vetor.size(); i++) {
			Console.println(i, ": ", vetor.get(i));
		}
	}
}
