package br.com.confitec.teste.entity;

import java.math.BigDecimal;

/**
 * 
 * @author Danilo Nelder
 *
 */

public class Cobertura {

	private Long cobertura;
	private BigDecimal valor;

	public Cobertura(Long cobertura, BigDecimal valor) {
		this.cobertura = cobertura;
		this.valor = valor;
	}

	public Long getCobertura() {
		return cobertura;
	}

	public void setCobertura(Long cobertura) {
		this.cobertura = cobertura;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
