package br.com.confitec.teste.facade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.confitec.teste.entity.Cobertura;
import br.com.confitec.teste.entity.Dado;
import br.com.confitec.teste.entity.OpcaoParcelamento;
import br.com.confitec.teste.request.RequestTeste;
import br.com.confitec.teste.response.ResponseTeste;

/**
 * 
 * @author Danilo Nelder
 *
 */

@Service
public class TesteServiceFacadeImpl implements TesteServiceFacade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ResponseTeste calculoDeParcelasSobreJuros(RequestTeste request) {
		ResponseTeste response = new ResponseTeste();
		List<Dado> dados = new ArrayList<>();
		double valorTotal = 0.0;
		int posicaoParcela = 1;
		int primeiraParcela = 0;

		for (Cobertura cobertura : request.getListCobertura()) {
			valorTotal += cobertura.getValor().doubleValue();
		}

		for (OpcaoParcelamento opcaoParcelamento : request.getListOpcaoParcelamento()) {
			double juros = opcaoParcelamento.getJuros().doubleValue();
			primeiraParcela = opcaoParcelamento.getQuantidadeMinimaParcelas().intValue();
			while (primeiraParcela < opcaoParcelamento.getQuantidadeMaximaParcelas() + 1) {
				Dado dado = new Dado();

				double multiplicador = Math.pow(1.0 + juros, primeiraParcela) - 1.0;

				double valorComposto = valorTotal + multiplicador * valorTotal;

				BigDecimal valorBig = new BigDecimal(valorComposto).setScale(2, RoundingMode.HALF_EVEN);

				int valorTotalEmCentavos = (int) (valorBig.doubleValue() * 100);
				int valorParcela = valorTotalEmCentavos / (primeiraParcela);
				int parcela1 = valorTotalEmCentavos - valorParcela * (primeiraParcela - 1);

				dado.setValorParcelamentoTotal(valorBig);

				dado.setValorPrimeiraParcela(BigDecimal.valueOf(parcela1 / 100.0));
				if (primeiraParcela > 1)
					dado.setValorDemaisParcelas(BigDecimal.valueOf(valorParcela / 100.0));

				dado.setQuantidadeParcelas(posicaoParcela++);
				dados.add(dado);
				primeiraParcela++;
			}
		}
		response.setDados(dados);
		return response;
	}

}
