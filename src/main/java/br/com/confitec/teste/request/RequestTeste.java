package br.com.confitec.teste.request;

import java.util.List;

import br.com.confitec.teste.entity.Cobertura;
import br.com.confitec.teste.entity.OpcaoParcelamento;

/**
 * 
 * @author Danilo Nelder
 *
 */

public class RequestTeste {

	private List<Cobertura> listCobertura;
	private List<OpcaoParcelamento> listOpcaoParcelamento;

	public List<Cobertura> getListCobertura() {
		return listCobertura;
	}

	public List<OpcaoParcelamento> getListOpcaoParcelamento() {
		return listOpcaoParcelamento;
	}

	public void setListCobertura(List<Cobertura> listCobertura) {
		this.listCobertura = listCobertura;
	}

	public void setListOpcaoParcelamento(List<OpcaoParcelamento> listOpcaoParcelamento) {
		this.listOpcaoParcelamento = listOpcaoParcelamento;
	}

	@Override
	public String toString() {
		return "RequestTeste [listCobertura=" + listCobertura + ", listOpcaoParcelamento=" + listOpcaoParcelamento
				+ "]";
	}

}
