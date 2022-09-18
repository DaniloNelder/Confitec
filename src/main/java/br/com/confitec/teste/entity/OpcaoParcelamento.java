package br.com.confitec.teste.entity;

import java.math.BigDecimal;

/**
 * 
 * @author Danilo Nelder
 *
 */

public class OpcaoParcelamento {

	private Long quantidadeMinimaParcelas;
	private Long quantidadeMaximaParcelas;
	private BigDecimal juros;

	public OpcaoParcelamento(Long quantidadeMinimaParcelas, Long quantidadeMaximaParcelas, BigDecimal juros) {
		this.quantidadeMinimaParcelas = quantidadeMinimaParcelas;
		this.quantidadeMaximaParcelas = quantidadeMaximaParcelas;
		this.juros = juros;
	}

	public Long getQuantidadeMinimaParcelas() {
		return quantidadeMinimaParcelas;
	}

	public Long getQuantidadeMaximaParcelas() {
		return quantidadeMaximaParcelas;
	}

	public void setQuantidadeMinimaParcelas(Long quantidadeMinimaParcelas) {
		this.quantidadeMinimaParcelas = quantidadeMinimaParcelas;
	}

	public void setQuantidadeMaximaParcelas(Long quantidadeMaximaParcelas) {
		this.quantidadeMaximaParcelas = quantidadeMaximaParcelas;
	}

	public BigDecimal getJuros() {
		return juros;
	}

	public void setJuros(BigDecimal juros) {
		this.juros = juros;
	}
}
