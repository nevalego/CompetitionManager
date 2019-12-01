package logic.inscripcion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import logic.exception.DataException;
import logic.model.Atleta;
import logic.model.Competicion;
import logic.model.Inscripcion;
import logic.model.Plazo;
import util.Dates;

public class InscripcionPorLote {

	public int[] leerInscripcionesPorLote(Competicion competicion, String file) throws DataException {

		BufferedReader br = null;
		List<Atleta> atletas = new ArrayList<Atleta>();
		try {
			br = new BufferedReader(new FileReader("files/"+file));
			Atleta atleta = null;
			String[] line = null;
			while (br.ready()) {
				line = br.readLine().split("\t");
				atleta = new Atleta();
				atleta.dni = line[0];
				atleta.nombre = line[1];
				atleta.apellidos = line[2];
				atleta.email = line[3];
				atleta.fechaNacimiento = Dates.fromString(line[4]);
				atleta.sexo = line[5];
				atletas.add(atleta);
			}
		} catch (IOException e) {
			throw new DataException("Error al leer fichero inscripcion por lote de atletas");
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				throw new DataException("Error al cerrar fichero inscripcion por lote de atletas");
			}
		}

		int[] res = new int[2];
		int oks = 0;
		int totales = 0;

		HacerInscripcion ins = new HacerInscripcion();
		HacerRegistro reg = new HacerRegistro();
		PagoInscripcion pag = new PagoInscripcion();
		Atleta auxA = null;
		Inscripcion auxI = null;

		for (Atleta atleta : atletas) {
			auxA = ins.getAtletaByDNI(atleta.dni);
			if (auxA == null) { // si no esta registrado
				// registrar atleta
				reg.registrar(atleta);
			}
			auxA = ins.getAtletaByDNI(atleta.dni);
			auxI = ins.getInscripcion(auxA.id, competicion.id);
			
			if(auxI == null) { // si no esta inscrito
				ins.inscribirse(auxA.id, competicion.id); // inscribir
			}
			
			auxI = ins.getInscripcion(auxA.id, competicion.id);
			if( auxI.estado.equals("PENDIENTE DE PAGO") ) {// si no esta pagada
				auxI.fechaPago= Dates.now();
				auxI.fechaModificacion = auxI.fechaPago;
				auxI.medioPago = "CLUB";
				Plazo plazo = pag.obtenerPlazo(auxI);
				auxI.cantidad = plazo.cuota;
				auxI.estado = "ABONADA";
				pag.pagarInscripcion(auxI);// pagar la inscripcion
				oks++;
			}
			totales++;
		}
		res[0] = oks;
		res[1] = totales-oks;
		return res;
	}

}
