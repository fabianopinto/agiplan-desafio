package br.com.cwi.fabianopinto.agiplan.desafio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.cwi.fabianopinto.agiplan.desafio.comando.Comando;
import br.com.cwi.fabianopinto.agiplan.desafio.service.ArquivoService;
import br.com.cwi.fabianopinto.agiplan.desafio.service.ConsoleService;
import br.com.cwi.fabianopinto.agiplan.desafio.service.ResultadoService;

@SpringBootApplication
public class DesafioApplication implements CommandLineRunner {

	@Autowired
	private ConsoleService consoleService;

	@Autowired
	private ArquivoService arquivoService;

	@Autowired
	private ResultadoService resultadoService;

	@Autowired
	private List<Comando> comandos;

	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		consoleService.escreverLinha("Desafio Agiplan");
		consoleService.escreverLinha("====================");
		resultadoService.inicializar();

		for (;;) {
			String linha = consoleService.lerLinha();
			if (linha == null || linha.isEmpty()) {
				break;
			}

			boolean ok = false;
			for (Comando comando : comandos) {
				if (comando.isValido(linha)) {
					String resultado = comando.executar(linha, arquivoService);
					consoleService.escreverLinha(resultado);
					resultadoService.escreverLinha(linha);
					resultadoService.escreverLinha(resultado);
					ok = true;
				}
			}
			if (!ok) {
				consoleService.escreverLinha("Comando '%s' inválido", linha);
			}
		}
	}

}
