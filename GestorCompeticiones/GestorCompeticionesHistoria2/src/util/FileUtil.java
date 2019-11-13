package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import logic.exception.DataException;

public class FileUtil {

	public static List<String> cargarArchivo(String fileName) throws DataException {
		List<String> lines = new ArrayList<String>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
			br.close();
		} catch (IOException e) {
			throw new DataException("Error cargando el archivo");
		}
		return lines;
	}
	


}
