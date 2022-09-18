package br.com.confitec.teste.facade;

import java.io.Serializable;

import br.com.confitec.teste.request.RequestTeste;
import br.com.confitec.teste.response.ResponseTeste;

/**
 * 
 * @author Danilo Nelder
 *
 */

public interface TesteServiceFacade extends Serializable {

	public ResponseTeste calculoDeParcelasSobreJuros(final RequestTeste request);

}
