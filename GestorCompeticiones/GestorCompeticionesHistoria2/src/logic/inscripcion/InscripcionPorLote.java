package logic.inscripcion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import logic.exception.DataException;
import logic.model.Competicion;

public class InscripcionPorLote {

	public int[] leerInscripcionesPorLote(Competicion competicion, String file) throws DataException {
		
		// Nombre Apellidos DNI Email Fecha nacimiento Sexo

		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(file));

		} catch (IOException e) {
			throw new DataException("Error al leer fichero inscripcion por lote de atletas");
		}

		return null;
	}

}
