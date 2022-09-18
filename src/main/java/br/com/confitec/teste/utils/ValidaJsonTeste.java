package br.com.confitec.teste.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.confitec.teste.constantes.Constantes;
import br.com.confitec.teste.request.RequestTeste;

/**
 * 
 * @author Danilo Nelder
 *
 */

@Service
public class ValidaJsonTeste {

	private static Logger logger = LoggerFactory.getLogger(ValidaJsonTeste.class);

	public String validaEntraJSON(final RequestTeste request) {
		logger.info("inicio da validacao JSON");

		StringBuffer msgsException = new StringBuffer(100);

		msgsException.append(validaCamposLista(request, "listCobertura", true));
		msgsException.append(validaCamposLista(request, "listOpcaoParcelamento", true));

		logger.info("final da validacao JSON");
		return msgsException.toString();
	}

	public String validaCamposLista(final Object obj, final String nomeAtributo, final boolean obrigatorio) {
		return validaCamposLista(obj, nomeAtributo, null, obrigatorio, 0, null, false);
	}

	public String validaCamposLista(final Object obj, final String nomeAtributoLista, String nomeAtributo,
			final boolean obrigatorio, final int tamMaxAtributo, final List<String> valoresPossiveis,
			final boolean validaDigitos) {
		StringBuffer sbValidacao = new StringBuffer();

		try {
			if (obj != null) {
				Object valorAtributoLista = null;

				Class<?> clsObjLista = obj.getClass();

				StringBuffer nomeMetodoGetLista = new StringBuffer(Constantes.GET);
				nomeMetodoGetLista.append(StringUtils.capitalize(nomeAtributoLista));

				Method getMethodObjLista = clsObjLista.getMethod(nomeMetodoGetLista.toString(), null);
				valorAtributoLista = getMethodObjLista.invoke(obj, (Object[]) null);

				if (valorAtributoLista instanceof Long[]) {
					Long[] arrayLong = (Long[]) valorAtributoLista;

					for (int i = 0; i < arrayLong.length; i++) {
						Long codigo = arrayLong[i];
						if (codigo.toString().length() > tamMaxAtributo) {
							sbValidacao.append(Constantes.CADA_ITEM_DE);
							sbValidacao.append(nomeAtributoLista);
							sbValidacao.append(Constantes.DEVE_TER_NO_MAXIMO);
							sbValidacao.append(tamMaxAtributo);
							sbValidacao.append(Constantes.CARACTERES_DIGITOS);

							break;
						}
					}

				} else if (valorAtributoLista instanceof List) {
					List<?> lista = (List<?>) valorAtributoLista;

					boolean possuiValoresNaoPermitidos = false;

					if (Utils.vazio(lista)) {
						sbValidacao.append(nomeAtributoLista);
						sbValidacao.append(Constantes.DEVE_SER_PREENCHIDO);

					} else if (!Utils.vazio(nomeAtributo)) {
						for (int j = 0; j < lista.size(); j++) {
							Class<?> clsObj = lista.get(j).getClass();

							StringBuffer nomeMetodoGet = new StringBuffer(Constantes.GET);
							nomeMetodoGet.append(StringUtils.capitalize(nomeAtributo));

							Method getMethodObj = clsObj.getMethod(nomeMetodoGet.toString(), null);
							Object valorAtributo = getMethodObj.invoke(lista.get(j), (Object[]) null);

							boolean estaVazio = verificaEstaVazio(valorAtributo);

							if (obrigatorio && estaVazio) {
								sbValidacao.append(Constantes.UM_OU_MAIS);
								sbValidacao.append(nomeAtributo);
								sbValidacao.append(Constantes.DA_LISTA_DE);
								sbValidacao.append(nomeAtributoLista);
								sbValidacao.append(Constantes.DEVEM_SER_PREENCHIDOS);
							}

							if (!estaVazio) {
								if (!possuiValoresNaoPermitidos) {
									possuiValoresNaoPermitidos = validaValoresNaoPermitidos(valoresPossiveis,
											valorAtributo);

									if (possuiValoresNaoPermitidos) {
										sbValidacao.append(Constantes.UM_OU_MAIS);
										sbValidacao.append(nomeAtributo);
										sbValidacao.append(Constantes.DA_LISTA_DE);
										sbValidacao.append(nomeAtributoLista);
										sbValidacao.append(Constantes.POSSUEM_VALORES_DIFERENTES_DE);
										sbValidacao.append(valoresPossiveis);
										sbValidacao.append(Constantes.PONTO_E_VIRGULA);
									}
								}
							}
						}
					}
				}
			}

		} catch (SecurityException e) {
			logger.error(Constantes.SECURITY_EXCEPTION, e);

		} catch (IllegalArgumentException e) {
			logger.error(Constantes.ILLEGAL_ARGUMENT_EXCEPTION, e);

		} catch (IllegalAccessException e) {
			logger.error(Constantes.ILLEGAL_ACCESS_EXCEPTION, e);

		} catch (InvocationTargetException e) {
			logger.error(Constantes.INVOCATION_TARGET_EXCEPTION, e);

		} catch (NoSuchMethodException e) {
			logger.error(Constantes.NOSUCH_METHOD_EXCEPTION, e);
		}

		return sbValidacao.toString();
	}

	private boolean verificaEstaVazio(final Object valorAtributo) {
		boolean estaVazio = false;

		if (valorAtributo == null)
			estaVazio = true;
		else if ((valorAtributo instanceof String) && (Utils.vazio((String) valorAtributo)))
			estaVazio = true;
		else if ((valorAtributo instanceof List) && (Utils.vazio((List<?>) valorAtributo)))
			estaVazio = true;

		return estaVazio;
	}

	private static boolean validaValoresNaoPermitidos(final List<String> valoresPossiveis, final Object valorAtributo) {

		boolean possuiValoresNaoPermitidos = false;

		if (valoresPossiveis != null && valorAtributo != null) {
			String conteudoAtributoString = null;
			Long conteudoAtributoLong = null;

			if (valorAtributo instanceof String) {
				conteudoAtributoString = (String) valorAtributo;
			} else if (valorAtributo instanceof Long) {
				conteudoAtributoLong = (Long) valorAtributo;
			} else if (valorAtributo instanceof Integer) {
				conteudoAtributoLong = Long.valueOf((Integer) valorAtributo);
			}

			if (conteudoAtributoString != null || conteudoAtributoLong != null) {

				if ((conteudoAtributoString != null)
						&& (!valoresPossiveis.contains(conteudoAtributoString.toUpperCase()))) {

					possuiValoresNaoPermitidos = true;

				} else if ((conteudoAtributoLong != null)
						&& (!valoresPossiveis.contains(String.valueOf(conteudoAtributoLong)))) {

					possuiValoresNaoPermitidos = true;
				}
			}
		}

		return possuiValoresNaoPermitidos;
	}
}
