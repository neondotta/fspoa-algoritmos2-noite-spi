import com.senac.SimpleJava.Console;

public class TesteDeVetores {
	public void run() {
		Console.println("Testes de Vetores");
		
		Vetor vetor = new Vetor();
		
		vetor.append(1);
		vetor.append(2);
		vetor.append(3);
		vetor.append(4);
		for (int i = 0; i < vetor.size(); i++)
			Console.println(i, ": ", vetor.get(i));
	}
}
