package br.com.cwi.fabianopinto.agiplan.desafio.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

@Service
public class ConsoleServiceImpl implements ConsoleService {

	private Scanner scanner = new Scanner(System.in);

	@Override
	public String lerLinha() {
		System.out.print("> ");
		return scanner.nextLine();
	}

	@Override
	public void escreverLinha(String linha, Object... args) {
		System.out.printf(linha, args).println();
	}

}
