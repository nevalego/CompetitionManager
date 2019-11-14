package util;

import java.util.ArrayList;
import java.util.List;

import logic.model.Categoria;
import logic.model.Resultados;

public class Parser {

	public static List<Categoria> parseCategorias(List<String> lines) {
		List<Categoria> categorias = new ArrayList<Categoria>();
		for (String line : lines) {
			categorias.add(parseLine(line));
		}

		// MENSAJE BONITO Y CARIÑOSO CON MUCHO AMOR DE AITOR <3 =>
		// IGUAL ERA MEJOR CAMBIARLO POR
		// lines.forEach(c -> categorias.add(parseLine(c)));
		return categorias;
	}

	private static Categoria parseLine(String line) {
		String[] parts = line.split(":");
		String name = parts[0];
		parts = parts[1].split("-");
		int minAge = Integer.parseInt(parts[0]);
		int maxAge = Integer.parseInt(parts[1]);
		// return new
		// Categoria(parts[1].split("-"),Integer.parseInt(parts[0]),Integer.parseInt(parts[1]));
		// Menos codigo¿? Ya lo mirais =D
		return new Categoria(name, minAge, maxAge);
	}

	/**
	 * Metodo que hara el parser del fichero de los resultados
	 * 
	 * @param line, el fichero leido por lineas
	 * @return una lista con todos los resultados
	 */
	public static List<Resultados> parseResultados(List<String> line) {
		List<Resultados> parseada = new ArrayList<Resultados>();
		// String infoCompeticion = line.get(0);
		// line.remove(0);
		line.forEach(s -> parseada.add(parseResultado(s)));
		return parseada;
	}

	private static Resultados parseResultado(String res) {
		Resultados r = new Resultados();
		String[] part = res.split("\t");
		r.setNombreCompetidor(part[0]);
		r.setTiempo(parseTiempo(part[1], part[2]));
		return r;
	}

	private static String parseTiempo(String tiempoSalida,
			String tiempoEntrada) {
		String tiempoFinal = null;
		if (tiempoSalida != null && !tiempoSalida.equals("---")) {
			if (tiempoEntrada != null && !tiempoEntrada.equals("---")) {
				String[] tiempoSalidaCalculo = tiempoSalida.split(":");
				String[] tiempoEntradaCalculo = tiempoEntrada.split(":");

				int horaFinal = Integer.parseInt(tiempoEntradaCalculo[0])
						- Integer.parseInt(tiempoSalidaCalculo[0]);
				int minFinal = Integer.parseInt(tiempoEntradaCalculo[1])
						- Integer.parseInt(tiempoSalidaCalculo[1]);
				int segFinal = Integer.parseInt(tiempoEntradaCalculo[2])
						- Integer.parseInt(tiempoSalidaCalculo[2]);
				String stringHoraFinal = "" + horaFinal;
				String stringMinFinal = "" + minFinal;
				String stringSegFinal = "" + segFinal;
				if (horaFinal < 10) {
					stringHoraFinal = "0" + horaFinal;
				}
				if (minFinal < 10) {
					stringMinFinal = "0" + minFinal;
				}
				if (segFinal < 10) {
					stringSegFinal = "0" + segFinal;
				}
				tiempoFinal = stringHoraFinal + ":" + stringMinFinal + ":"
						+ stringSegFinal;
			} else {
				tiempoFinal = "DNE";
			}
		} else
			tiempoFinal = "DNS";

		return tiempoFinal;
	}
}
