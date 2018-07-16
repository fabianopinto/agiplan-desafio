package br.com.cwi.fabianopinto.agiplan.desafio.service;

import java.util.List;

public interface ArquivoService {

	public List<String> getCabecalhos();

	public List<List<String>> getRegistros();

	public List<String> getRegistro(int index);

}
