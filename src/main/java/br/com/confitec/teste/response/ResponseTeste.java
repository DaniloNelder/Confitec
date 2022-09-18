package br.com.confitec.teste.response;

import java.util.List;

import br.com.confitec.teste.entity.Dado;

/**
 * 
 * @author Danilo Nelder
 *
 */

public class ResponseTeste {

	private List<Dado> dados;

	public List<Dado> getDados() {
		return dados;
	}

	@Override
	public String toString() {
		return "ResponseTeste [dados=" + dados + "]";
	}

	public void setDados(List<Dado> dados) {
		this.dados = dados;
	}

}
