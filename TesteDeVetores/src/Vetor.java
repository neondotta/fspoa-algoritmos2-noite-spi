
public class Vetor {
	
	private int vet[] = new int[2];
	private int numElementos = 0;
	
	public void append(int valor) {
		garantirTamanho();
		vet[numElementos] = valor;
		numElementos++;
	}

	private void garantirTamanho() {
		if(numElementos == vet.length){
			int newVet[] = new int[vet.length * 2];
			for(int i = 0; i < vet.length; i++){
				newVet[i] = vet[i];
			}
			vet = newVet;
		}
	}

	public int size() {
		return numElementos;
	}

	public int get(int index) {
		if(index < numElementos && index >= 0)
			return vet[index];
		
		throw new ArrayIndexOutOfBoundsException(index);
	}
	
}
