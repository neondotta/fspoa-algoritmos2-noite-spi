
public class Vetor <T> {
	
	private Object[] vet = new Object[2];
	private int numElementos = 0;
	
	public void append(T valor) {
		garantirTamanho();
		vet[numElementos] = valor;
		numElementos++;
	}

	private void garantirTamanho() {
		if(numElementos == vet.length){
			Object newVet[] = new Object[vet.length * 2];
			for(int i = 0; i < vet.length; i++){
				newVet[i] = vet[i];
			}
			vet = newVet;
		}
	}

	public int size() {
		return numElementos;
	}

	@SuppressWarnings("unchecked")
	public T get(int index) {
		validaIndice(index);
		return (T)vet[index];
	}
	
	
	private void validaIndice(int index) {
		if(index < 0 && index >= numElementos) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
	}

	public void insert(int index, T valor) {
		garantirTamanho();
		validaIndice(index);
		for(int i = numElementos;i > index; i--){
			vet[i] = vet[i - 1];
		}
		vet[index] = valor;
		numElementos++;
	}

	public void remove(int index) {
		validaIndice(index);
		for (int i = index; i < numElementos-1; i++)
		{
			vet[i] = vet[i + 1];
		}
		numElementos--;
	}
	
}
