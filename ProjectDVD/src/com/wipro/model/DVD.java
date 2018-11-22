package com.wipro.model;

public class DVD {
	private String nome;
	private String diretor;
	private int anoDePublicacao;
	private String sinopse;

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the diretor
	 */
	public String getDiretor() {
		return diretor;
	}

	/**
	 * @param diretor
	 *            the diretor to set
	 */
	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

	/**
	 * @return the anoDePublicacao
	 */
	public int getAnoDePublicacao() {
		return anoDePublicacao;
	}

	/**
	 * @param anoDePublicacao
	 *            the anoDePublicacao to set
	 */
	public void setAnoDePublicacao(int anoDePublicacao) {
		this.anoDePublicacao = anoDePublicacao;
	}

	/**
	 * @return the sinopse
	 */
	public String getSinopse() {
		return sinopse;
	}

	/**
	 * @param sinopse
	 *            the sinopse to set
	 */
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

}
