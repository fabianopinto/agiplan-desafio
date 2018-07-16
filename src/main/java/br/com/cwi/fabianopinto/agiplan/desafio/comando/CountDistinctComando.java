package br.com.cwi.fabianopinto.agiplan.desafio.comando;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.cwi.fabianopinto.agiplan.desafio.service.ArquivoService;

@Component
public class CountDistinctComando implements Comando {

	private static final Pattern COMANDO = Pattern.compile("count\\s+distinct\\s+(\\w+)", Pattern.CASE_INSENSITIVE);

	@Override
	public boolean isValido(String comando) {
		return COMANDO.matcher(comando).matches();
	}

	@Override
	public String executar(String comando, ArquivoService arquivoService) {
		Matcher matcher = COMANDO.matcher(comando);
		matcher.matches();
		int propriedade = arquivoService.getCabecalhos().indexOf(matcher.group(1));
		return String.valueOf(contagem(propriedade, arquivoService));
	}

	private int contagem(int propriedade, ArquivoService arquivoService) {
		if (propriedade == -1) {
			return 0;
		}
		Set<String> distintos = arquivoService.getRegistros()
				.stream()
				.filter(registro -> registro.size() > propriedade)
				.map(registro -> registro.get(propriedade))
				.collect(Collectors.toSet());
		return distintos.size();
	}

}
