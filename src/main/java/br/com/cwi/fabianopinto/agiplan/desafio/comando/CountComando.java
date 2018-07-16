package br.com.cwi.fabianopinto.agiplan.desafio.comando;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import br.com.cwi.fabianopinto.agiplan.desafio.service.ArquivoService;

@Component
public class CountComando implements Comando {

	private static final Pattern COMANDO = Pattern.compile("count\\s+\\*", Pattern.CASE_INSENSITIVE);

	@Override
	public boolean isValido(String comando) {
		return COMANDO.matcher(comando).matches();
	}

	@Override
	public String executar(String comando, ArquivoService arquivoService) {
		return String.valueOf(arquivoService.getRegistros().size());
	}

}
