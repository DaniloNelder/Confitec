package br.com.confitec.teste.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author Danilo Nelder
 *
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
public class Dado {

	private int quantidadeParcelas;
	private BigDecimal valorPrimeiraParcela;
	private BigDecimal valorDemaisParcelas;
	private BigDecimal valorParcelamentoTotal;

	public Dado(int quantidadeParcelas, BigDecimal valorPrimeiraParcela, BigDecimal valorDemaisParcelas,
			BigDecimal valorParcelamentoTotal) {
		this.quantidadeParcelas = quantidadeParcelas;
		this.valorPrimeiraParcela = valorPrimeiraParcela;
		this.valorDemaisParcelas = valorDemaisParcelas;
		this.valorParcelamentoTotal = valorParcelamentoTotal;
	}

	public Dado() {
	}

	public int getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(int quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public BigDecimal getValorPrimeiraParcela() {
		return valorPrimeiraParcela;
	}

	public void setValorPrimeiraParcela(BigDecimal valorPrimeiraParcela) {
		this.valorPrimeiraParcela = valorPrimeiraParcela.setScale(2, RoundingMode.HALF_EVEN);
	}

	public BigDecimal getValorParcelamentoTotal() {
		return valorParcelamentoTotal;
	}

	public void setValorParcelamentoTotal(BigDecimal valorParcelamentoTotal) {
		this.valorParcelamentoTotal = valorParcelamentoTotal.setScale(2, RoundingMode.HALF_EVEN);
	}

	public BigDecimal getValorDemaisParcelas() {
		return valorDemaisParcelas;
	}

	public void setValorDemaisParcelas(BigDecimal valorDemaisParcelas) {
		this.valorDemaisParcelas = valorDemaisParcelas;
	}

	@Override
	public String toString() {
		return "Dado [quantidadeParcelas=" + quantidadeParcelas + ", valorPrimeiraParcela=" + valorPrimeiraParcela
				+ ", valorDemaisParcelas=" + valorDemaisParcelas + ", valorParcelamentoTotal=" + valorParcelamentoTotal
				+ "]";
	}
}
