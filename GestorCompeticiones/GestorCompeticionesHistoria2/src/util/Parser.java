package util;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import logic.exception.DataException;
import logic.model.Categoria;
import logic.model.Resultados;
import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.SECONDS;

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
	 * @throws DataException
	 */
	public static List<Resultados> parseResultados(List<String> line)
			throws DataException {
		List<Resultados> parseada = new ArrayList<Resultados>();
		// String infoCompeticion = line.get(0);
		// line.remove(0);
		for (String s : line) {

			parseada.add(parseResultado(s));

		}
		return parseada;
	}

	private static Resultados parseResultado(String res) throws DataException {
		Resultados r = new Resultados();
		String[] part = res.split("\t");
		try {
			r.setDorsal(Integer.parseInt(part[0]));
		} catch (NumberFormatException e) {
			r.setDorsal(0);
			r.setMotivo("Dorsal no numerico");
		}
		r.setTiempo(parseTiempo(part[1], part[2]));
		return r;
	}

	private static String parseTiempo(String tiempoSalida, String tiempoEntrada)
			throws DataException {
		String tiempoFinal = null;
		if (tiempoSalida != null && !tiempoSalida.equals("---")) {
			if (tiempoEntrada != null && !tiempoEntrada.equals("---")) {
				try {
					LocalTime tiempoSalidaTiempo = LocalTime
							.parse(tiempoSalida);
					LocalTime tiempoEntradaTiempo = LocalTime
							.parse(tiempoEntrada);
					Duration duration = Duration.between(tiempoSalidaTiempo,
							tiempoEntradaTiempo);
					int horaFinal = (int) (duration.getSeconds() / 3600);
					int minFinal = (int) ((duration.getSeconds()
							- horaFinal * 3600) / 60);
					int secsFinal = (int) (duration.getSeconds()
							- (horaFinal * 3600 + minFinal * 60));

					String stringHoraFinal = "" + horaFinal;
					String stringMinFinal = "" + minFinal;
					String stringSegFinal = "" + secsFinal;
					if (horaFinal < 10) {
						stringHoraFinal = "0" + horaFinal;
					}
					if (minFinal < 10) {
						stringMinFinal = "0" + minFinal;
					}
					if (secsFinal < 10) {
						stringSegFinal = "0" + secsFinal;
					}
					tiempoFinal = stringHoraFinal + ":" + stringMinFinal + ":"
							+ stringSegFinal;
				} catch (Exception e) {
					throw new DataException(
							"El fichero esta mal formateado en los tiempos");
				}
			} else {
				tiempoFinal = "DNE";
			}
		} else
			tiempoFinal = "DNS";

		return tiempoFinal;
	}

}
