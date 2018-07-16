package br.com.cwi.fabianopinto.agiplan.desafio.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ArquivoServiceImpl implements ArquivoService {

	@Value("${arquivo.nome}")
	private String arquivoNome;

	@Value("${arquivo.separador:,}")
	private String arquivoSeparador;

	private List<String> cabecalhos;

	private List<List<String>> registros = new ArrayList<>();

	@PostConstruct
	private void lerArquivo() {
		try (BufferedReader reader = new BufferedReader(new FileReader(arquivoNome))) {
			cabecalhos = lerLinha(reader);
			List<String> registro;
			while ((registro = lerLinha(reader)) != null) {
				registros.add(registro);
			}
		} catch (FileNotFoundException e) {
			throw new IllegalStateException(String.format("Arquivo '%s' não encontrado", arquivoNome), e);
		} catch (IOException e) {
			throw new IllegalStateException(String.format("Erro de leitura no arquivo '%s'", arquivoNome), e);
		}
	}

	private List<String> lerLinha(BufferedReader reader) {
		String linha;
		try {
			linha = reader.readLine();
			return linha != null ? Arrays.asList(linha.split(arquivoSeparador)) : null;
		} catch (IOException e) {
			throw new IllegalStateException(String.format("Erro de leitura no arquivo '%s'", arquivoNome), e);
		}
	}

	public List<String> getCabecalhos() {
		return cabecalhos;
	}

	public List<List<String>> getRegistros() {
		return registros;
	}

	public List<String> getRegistro(int index) {
		return registros != null && registros.size() > index ? registros.get(index) : null;
	}

}
