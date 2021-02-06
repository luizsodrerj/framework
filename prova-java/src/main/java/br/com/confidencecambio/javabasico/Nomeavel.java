package br.com.confidencecambio.javabasico;

public interface Nomeavel {

	void setNome(String nome) throws NomeInvalidoException;

	String getNomeAbreviado();

	String getUltimoNome();
	
	String getPrimeiroNome();

	String getNomeMaiusculo();
	
	
}








