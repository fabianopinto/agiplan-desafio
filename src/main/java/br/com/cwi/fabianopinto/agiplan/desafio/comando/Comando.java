package br.com.cwi.fabianopinto.agiplan.desafio.comando;

import br.com.cwi.fabianopinto.agiplan.desafio.service.ArquivoService;

public interface Comando {

	boolean isValido(String comando);

	String executar(String comando, ArquivoService arquivoService);

}
