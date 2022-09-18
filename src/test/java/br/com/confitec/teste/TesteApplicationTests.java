package br.com.confitec.teste;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.confitec.teste.entity.Cobertura;
import br.com.confitec.teste.entity.Dado;
import br.com.confitec.teste.entity.OpcaoParcelamento;
import br.com.confitec.teste.facade.TesteServiceFacade;
import br.com.confitec.teste.request.RequestTeste;
import br.com.confitec.teste.response.ResponseTeste;

@SpringBootTest
class TesteApplicationTests {

	private RequestTeste requestMock = new RequestTeste();
	private ResponseTeste responseMock = new ResponseTeste();
	private List<Dado> listaDadosMock = new ArrayList<>();
	private List<OpcaoParcelamento> listaOpcao = new ArrayList<>();

	@Autowired
	private TesteServiceFacade testeServiceFacade;

	@Test
	public void testaParcelas() {
		mockInicial();

		mockRequest(new OpcaoParcelamento(1L, 6L, new BigDecimal(0.0)));
		mockRequest(new OpcaoParcelamento(7L, 9L, new BigDecimal(0.01).setScale(2, RoundingMode.HALF_EVEN)));
		mockRequest(new OpcaoParcelamento(10L, 12L, new BigDecimal(0.03).setScale(2, RoundingMode.HALF_EVEN)));
		ResponseTeste response = testeServiceFacade.calculoDeParcelasSobreJuros(requestMock);
		for (int i = 0; i < response.getDados().size(); i++)
			Assert.assertEquals(response.getDados().get(i).toString(), responseMock.getDados().get(i).toString());
	}

	private void mockInicial() {
		mockDados(1, new BigDecimal(468.57).setScale(2, RoundingMode.HALF_EVEN), null,
				new BigDecimal(468.57).setScale(2, RoundingMode.HALF_EVEN));
		mockDados(2, new BigDecimal(234.29).setScale(2, RoundingMode.HALF_EVEN),
				new BigDecimal(234.28).setScale(2, RoundingMode.HALF_EVEN),
				new BigDecimal(468.57).setScale(2, RoundingMode.HALF_EVEN));
		mockDados(3, new BigDecimal(156.19).setScale(2, RoundingMode.HALF_EVEN),
				new BigDecimal(156.19).setScale(2, RoundingMode.HALF_EVEN),
				new BigDecimal(468.57).setScale(2, RoundingMode.HALF_EVEN));
		mockDados(4, new BigDecimal(117.15).setScale(2, RoundingMode.HALF_EVEN),
				new BigDecimal(117.14).setScale(2, RoundingMode.HALF_EVEN),
				new BigDecimal(468.57).setScale(2, RoundingMode.HALF_EVEN));
		mockDados(5, new BigDecimal(93.73).setScale(2, RoundingMode.HALF_EVEN),
				new BigDecimal(93.71).setScale(2, RoundingMode.HALF_EVEN),
				new BigDecimal(468.57).setScale(2, RoundingMode.HALF_EVEN));
		mockDados(6, new BigDecimal(78.12).setScale(2, RoundingMode.HALF_EVEN),
				new BigDecimal(78.09).setScale(2, RoundingMode.HALF_EVEN),
				new BigDecimal(468.57).setScale(2, RoundingMode.HALF_EVEN));

		mockDados(7, new BigDecimal(71.81).setScale(2, RoundingMode.HALF_EVEN),
				new BigDecimal(71.76).setScale(2, RoundingMode.HALF_EVEN),
				new BigDecimal(502.37).setScale(2, RoundingMode.HALF_EVEN));
		mockDados(8, new BigDecimal(63.45).setScale(2, RoundingMode.HALF_EVEN),
				new BigDecimal(63.42).setScale(2, RoundingMode.HALF_EVEN),
				new BigDecimal(507.39).setScale(2, RoundingMode.HALF_EVEN));
		mockDados(9, new BigDecimal(56.95).setScale(2, RoundingMode.HALF_EVEN),
				new BigDecimal(56.94).setScale(2, RoundingMode.HALF_EVEN),
				new BigDecimal(512.47).setScale(2, RoundingMode.HALF_EVEN));

		mockDados(10, new BigDecimal(62.99).setScale(2, RoundingMode.HALF_EVEN),
				new BigDecimal(62.97).setScale(2, RoundingMode.HALF_EVEN),
				new BigDecimal(629.72).setScale(2, RoundingMode.HALF_EVEN));
		mockDados(11, new BigDecimal(59.01).setScale(2, RoundingMode.HALF_EVEN),
				new BigDecimal(58.96).setScale(2, RoundingMode.HALF_EVEN),
				new BigDecimal(648.61).setScale(2, RoundingMode.HALF_EVEN));
		mockDados(12, new BigDecimal(55.70).setScale(2, RoundingMode.HALF_EVEN),
				new BigDecimal(55.67).setScale(2, RoundingMode.HALF_EVEN),
				new BigDecimal(668.07).setScale(2, RoundingMode.HALF_EVEN));
	}

	private void mockRequest(OpcaoParcelamento opcaoParcelamento) {
		requestMock
				.setListCobertura(mockListaCoberturas(1L, new BigDecimal(468.57).setScale(2, RoundingMode.HALF_EVEN)));
		listaOpcao.add(opcaoParcelamento);
		requestMock.setListOpcaoParcelamento(listaOpcao);
		mockResponse();
	}

	private void mockResponse() {
		responseMock.setDados(listaDadosMock);
	}

	private void mockDados(int quantidadeParcelas, BigDecimal valorPrimeiraParcela, BigDecimal valorDemaisParcelas,
			BigDecimal valorParcelamentoTotal) {
		Dado dadoMock = new Dado(quantidadeParcelas, valorPrimeiraParcela, valorDemaisParcelas, valorParcelamentoTotal);
		listaDadosMock.add(dadoMock);
	}

	private List<Cobertura> mockListaCoberturas(Long tipoCobertura, BigDecimal valor) {
		List<Cobertura> listaCobertura = new ArrayList<>();
		Cobertura cobertura = new Cobertura(tipoCobertura, valor);
		listaCobertura.add(cobertura);
		return listaCobertura;
	}
}
