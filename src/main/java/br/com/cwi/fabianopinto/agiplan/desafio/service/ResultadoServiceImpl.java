package br.com.cwi.fabianopinto.agiplan.desafio.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ResultadoServiceImpl implements ResultadoService {

	@Value("${arquivo.saida}")
	private String arquivoSaida;

	@Override
	public void inicializar() {
		try {
			Files.deleteIfExists(Paths.get(arquivoSaida));
		} catch (IOException e) {
			throw new IllegalStateException(String.format("Erro na remoção do arquivo '%s'", arquivoSaida), e);
		}
	}

	@Override
	public void escreverLinha(String linha) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoSaida, true))) {
			writer.write(linha);
			writer.newLine();
		} catch (IOException e) {
			throw new IllegalStateException(String.format("Erro de escrita no arquivo '%s'", arquivoSaida), e);
		}
	}

}
