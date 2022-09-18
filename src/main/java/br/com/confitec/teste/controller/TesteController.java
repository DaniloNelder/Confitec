package br.com.confitec.teste.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

import br.com.confitec.teste.TesteApplication;
import br.com.confitec.teste.facade.TesteServiceFacade;
import br.com.confitec.teste.request.RequestTeste;
import br.com.confitec.teste.response.ResponseTeste;
import br.com.confitec.teste.utils.Utils;
import br.com.confitec.teste.utils.ValidaJsonTeste;

/**
 * 
 * @author Danilo Nelder
 *
 */

@RestController
@RequestMapping("confitec/teste")
public class TesteController {

	@Autowired
	private TesteServiceFacade testeServiceFacade;

	private static Logger logger = LoggerFactory.getLogger(TesteApplication.class);

	private XStream xstream = new XStream(new JsonHierarchicalStreamDriver());

	@Autowired
	private ValidaJsonTeste validador;

	@RequestMapping(value = "/parcelamento", method = RequestMethod.POST)
	public ResponseEntity<ResponseTeste> calculaParcelamento(@RequestBody RequestTeste request) {
		logger.info("request", request);
		try {
			xstream.setMode(XStream.NO_REFERENCES);
			xstream.alias("Dados", ResponseTeste.class);
			String msgValidacao = validador.validaEntraJSON(request);

			if (Utils.vazio(msgValidacao)) {
				logger.info("msgValidacao ", msgValidacao);
				return new ResponseEntity<ResponseTeste>(HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<ResponseTeste>(testeServiceFacade.calculoDeParcelasSobreJuros(request),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<ResponseTeste>(HttpStatus.BAD_REQUEST);
		}
	}
}
