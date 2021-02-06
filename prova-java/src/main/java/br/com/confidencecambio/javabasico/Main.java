package br.com.confidencecambio.javabasico;

public class Main {

	public static void main(String[] args) {
		Nomeavel cliente = new Cliente();
		Nomeavel gerente = new Gerente();
		Nomeavel robo    = new Robo();

		try {
			cliente.setNome(null);
		} catch (NomeInvalidoException e) {
			System.out.println("Erro ao definir o nome: " + e.getMessage());
		}

		try {
			cliente.setNome("");
		} catch (NomeInvalidoException e) {
			System.out.println("Erro ao definir o nome: " + e.getMessage());
		}
		
		try {
			cliente.setNome(" Fulano Com Espaco ");
		} catch (NomeInvalidoException e) {
			System.out.println("Erro ao definir o nome: " + e.getMessage());
		}

		try {
			gerente.setNome("Um Dois Oliveira Quatro");
		} catch (NomeInvalidoException e) {
			System.out.println("Erro ao definir o nome: " + e.getMessage());
		}

		try {
			robo.setNome("Ce Tres Po");
		} catch (NomeInvalidoException e) {
			System.out.println("Erro ao definir o nome: " + e.getMessage());
		}

		System.out.println("Primeiro nome - " + gerente.getPrimeiroNome());
		System.out.println("Primeiro nome - " + robo.getPrimeiroNome());
		System.out.println("Nome Maiusculo - " + gerente.getNomeMaiusculo());
		System.out.println("Nome Maiusculo - " + robo.getNomeMaiusculo());
		System.out.println("Ultimo nome - " + gerente.getUltimoNome());
		System.out.println("Ultimo nome - " + robo.getUltimoNome());
		System.out.println("Nome abreviado - " + gerente.getNomeAbreviado());
		System.out.println("Nome abreviado - " + robo.getNomeAbreviado());
		
	}

}








