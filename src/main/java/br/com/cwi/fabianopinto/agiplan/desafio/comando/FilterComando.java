package br.com.cwi.fabianopinto.agiplan.desafio.comando;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.cwi.fabianopinto.agiplan.desafio.service.ArquivoService;

@Component
public class FilterComando implements Comando {

	private static final Pattern COMANDO = Pattern.compile("filter\\s+(\\w+)\\s+(.+)", Pattern.CASE_INSENSITIVE);

	@Value("${arquivo.separador:,}")
	private String arquivoSeparador;

	@Override
	public boolean isValido(String comando) {
		return COMANDO.matcher(comando).matches();
	}

	@Override
	public String executar(String comando, ArquivoService arquivoService) {
		Matcher matcher = COMANDO.matcher(comando);
		matcher.matches();
		int propriedade = arquivoService.getCabecalhos().indexOf(matcher.group(1));
		String valor = matcher.group(2);

		StringBuilder sb = new StringBuilder(joinLinha(arquivoService.getCabecalhos()));
		if (propriedade != -1) {
			arquivoService.getRegistros()
					.stream()
					.filter(registro -> registro.size() > propriedade && valor.equals(registro.get(propriedade)))
					.forEach(registro -> sb.append(joinLinha(registro)));
		}
		return sb.toString();
	}

	private String joinLinha(List<String> valores) {
		return String.join(arquivoSeparador, valores).concat("\n");
	}

}
