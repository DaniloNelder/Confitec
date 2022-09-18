package br.com.confitec.teste.utils;

import java.util.List;

/**
 * 
 * @author Danilo Nelder
 *
 */

public class Utils {

	public static boolean vazio(String campo) {
		return campo == null || "".equals(campo.trim()) || "null".equals(campo.trim());
	}

	public static boolean vazio(List<?> valorAtributo) {
		boolean vazio = false;

		if ((valorAtributo == null) || (valorAtributo.isEmpty()))
			vazio = true;
		else
			for (Object obj : valorAtributo) {
				if (obj != null) {
					vazio = true;
					break;
				}

			}
		return vazio;
	}
}
