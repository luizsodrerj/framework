package br.com.confidencecambio.javabasico;

public abstract class Entidade {

	protected String nome;


	public String getNomeAbreviado() {
		if (nome == null) {
			return nome;
		}
		
		StringBuilder abreviado = new StringBuilder();
		boolean achouPriEspaco = false;
		boolean achouSegEspaco = false;
		int i = 0;
		
		while (i < nome.length()) {
			char caracter = nome.charAt(i);
			
			if (achouPriEspaco && abreviado.toString().indexOf('.') == -1) {
				abreviado.append(caracter).append(".");
			} else if (caracter == ' ') {
				if (!achouPriEspaco) {
					achouPriEspaco = true;	
				} else {
					achouSegEspaco = true;	
				}
				abreviado.append(caracter);
			} else if ((!achouPriEspaco && !achouSegEspaco) ||
					  (achouPriEspaco && achouSegEspaco)) {
				abreviado.append(caracter);				
			}
			i++;
		}
		return abreviado.toString();
	}

	public String getUltimoNome() {
		if (nome == null) {
			return nome;
		}
		
		StringBuilder ultNome = new StringBuilder();
		boolean achouPriEspaco = false;
		int i = 0;
		
		while (i < nome.length()) {
			char caracter = nome.charAt(i);
			
			if (achouPriEspaco) {
				ultNome.append(caracter);
			} else if (caracter == ' ') {
				achouPriEspaco = true;
			}
			i++;
		}
		return ultNome.toString();
	}
	
	public String getPrimeiroNome() {
		if (nome == null) {
			return nome;
		}
		
		StringBuilder priNome = new StringBuilder();
		int i = 0;
		
		while (i < nome.length()) {
			char caracter = nome.charAt(i);
			
			if (caracter == ' ') {
				break;
			} else {
				priNome.append(caracter); 
			}
			i++;
		}
		return priNome.toString();
	}

	public String getNomeMaiusculo() {
		return nome.toUpperCase();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws NomeInvalidoException {
		if (nome == null) {
			throw new NomeInvalidoException("Nome não pode ser nulo");
		} 
		
		if ("".equals(nome.trim())) {
			throw new NomeInvalidoException("Nome não pode ser vazio");
		} 
		
		String nameStart = nome.substring(0, 1);
		String nameEnd = nome.substring(nome.length() - 1, nome.length());

		if (" ".equals(nameStart) || " ".equals(nameEnd)) {
			throw new NomeInvalidoException("Nome não pode conter espaços extras no início e no fim");
		}
		
		this.nome = nome;
	}
	
	
	
}






