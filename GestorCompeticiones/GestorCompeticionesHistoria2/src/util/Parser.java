package util;

import java.util.ArrayList;
import java.util.List;

import logic.model.Categoria;

public class Parser {

	public static List<Categoria> parseCategorias(List<String> lines) {
		List<Categoria> categorias = new ArrayList<Categoria>();
		for (String line : lines) {
			categorias.add(parseLine(line));
		}
		return categorias;
	}

	private static Categoria parseLine(String line) {
		String[] parts = line.split(":");
		String name = parts[0];
		parts = parts[1].split("-");
		int minAge = Integer.parseInt(parts[0]);
		int maxAge = Integer.parseInt(parts[1]);
		return new Categoria(name, minAge, maxAge);
	}

}
