package logic.inscripcion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import logic.exception.DataException;
import logic.model.Atleta;
import logic.model.Competicion;
import logic.model.Inscripcion;
import logic.model.Pago;
import logic.model.Plazo;
import util.Conf;
import util.Dates;
import util.Jdbc;

public class PagoInscripcion {

	public Competicion getCompeticion(long inscripcionId) throws DataException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Competicion competicion = null;
		try (Connection c = Jdbc.getConnection()) {

			ps = c.prepareStatement(Conf.getInstance().getProperty("SQL_COMPETICION_INSCRIPCION"));
			ps.setLong(1, inscripcionId);
			rs = ps.executeQuery();

			if (rs.next()) {
				competicion = new Competicion();
				competicion.id = rs.getLong("id");
				competicion.fecha = rs.getDate("fecha");
				competicion.nombre = rs.getString("name");
				competicion.km = rs.getInt("km");
				competicion.plazas = rs.getInt("plazas");
				competicion.tipo = rs.getString("tipo");
			}
		} catch (SQLException e) {
			throw new DataException("Error en la conexi�n");
		}
		return competicion;
	}

	public Inscripcion obtenerInscripcion(long atletaId, long competicionId) throws DataException {
		PreparedStatement ps = null;
		Inscripcion ins = null;
		try (Connection c = Jdbc.getConnection()) {
			ps = c.prepareStatement(Conf.getInstance().getProperty("SQL_INSCRIPCION_ATLETA"));
			ps.setLong(1, atletaId);
			ps.setLong(2, competicionId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				ins = new Inscripcion();
				ins.id = rs.getLong("id");
				ins.fecha = rs.getDate("fecha");
				ins.estado = rs.getString("estado");
				ins.categoriaId = rs.getLong("categoria_id");
				ins.fechaModificacion = rs.getDate("fechamodificacion");
				ins.tiempo = rs.getString("tiempo");
				ins.atletaId = rs.getLong("atleta_id");
				ins.competicionId = rs.getLong("competicion_id");
				ins.medioPago = rs.getString("mediopago");
				ins.fechaPago = rs.getDate("fechapago");
				ins.cantidad = rs.getInt("cantidad");
			}
			return ins;
		} catch (SQLException e) {
			throw new DataException("Error en la conexion");
		}
	}

	public void pagarInscripcion(Inscripcion inscripcion) throws DataException {
		PreparedStatement ps = null;
		try (Connection c = Jdbc.getConnection()) {

			ps = c.prepareStatement(Conf.getInstance().getProperty("SQL_PAGAR_INSCRIPCION"));
			ps.setString(1, inscripcion.estado);
			ps.setInt(2, inscripcion.cantidad);
			ps.setDate(3, new java.sql.Date(inscripcion.fechaPago.getTime())); // Fecha pago
			ps.setDate(4, new java.sql.Date(inscripcion.fechaPago.getTime()));
			ps.setString(5, inscripcion.medioPago);
			ps.setLong(6, inscripcion.id);

			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("Error en la conexion");
		}
	}

	public Plazo obtenerPlazo(Inscripcion ins) throws DataException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Plazo plazo = null;
		try (Connection c = Jdbc.getConnection()) {

			ps = c.prepareStatement(Conf.getInstance().getProperty("SQL_PLAZO_INSCRIPCION"));
			ps.setLong(1, ins.competicionId);
			ps.setDate(2, new java.sql.Date(Dates.now().getTime()));

			rs = ps.executeQuery();
			if (rs.next()) {
				plazo = new Plazo();
				plazo.id = rs.getLong("id");
				plazo.fechaInicio = rs.getDate("fechainicio");
				plazo.fechaFin = rs.getDate("fechafin");
				plazo.cuota = rs.getInt("cuota");
				plazo.competicionId = rs.getLong("competicion_id");
			}

		} catch (SQLException e) {
			throw new DataException("Error en la conexi�n");
		}
		return plazo;
	}

	public void generarJustificante(Inscripcion ins) throws DataException {

		// competicion, atleta, fecha pago, medio pago y cantidad
		String fileName = String.valueOf(ins.competicionId) + String.valueOf(ins.atletaId);

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("files/" + fileName)))) {
			String text = "** JUSTIFICANTE DE PAGO **\n" + "Nombre Competicion: " + ins.nombreCompeticion + "\n"
					+ "Nombre Atleta: " + ins.nombreAtleta + "\n" + "Fecha pago: " + ins.fechaPago + "\n"
					+ "Medio pago: " + ins.medioPago + "\n" + "Cantidad abonada: " + ins.cantidad + " €";

			bw.write(text);

		} catch (IOException e) {
			throw new DataException("Error al generar justificante de pago");
		}
	}

	public int[] leerPagos(Competicion competicion, String file) throws DataException {

		int oks = 0;
		int kos = 0;
		String[] linea = null;
		Pago pago = null;
		HashMap<Pago, String> noCompletados = new HashMap<>();
		String why = "";
		HacerInscripcion ins = new HacerInscripcion();

		try (BufferedReader br = new BufferedReader(new FileReader( file))) {

			while (br.ready()) {
				pago = new Pago();
				linea = br.readLine().split("\t");
				pago.dni = linea[0];
				pago.fechaPago = Dates.fromString(linea[1]);
				pago.cantidad = Integer.parseInt(linea[2]);
				pago.nombreCompeticion = competicion.nombre;
				pago.competicionId = competicion.id;

				why = comprobarPago(pago);

				if (why.equals("")) {
					oks++;
					Atleta atleta = ins.getAtletaByDNI(pago.dni);
					Inscripcion inscripcion = ins.getInscripcion(atleta.id, competicion.id);
					inscripcion.medioPago = "Transferencia";
					inscripcion.fechaPago = pago.fechaPago;
					inscripcion.cantidad = pago.cantidad;
					inscripcion.estado = "ABONADA";
					inscripcion.nombreAtleta = atleta.nombre + " " + atleta.apellidos;
					inscripcion.fechaModificacion = pago.fechaPago;
					inscripcion.nombreCompeticion = competicion.nombre;
					
					if(inscripcion.cantidad < pago.cantidad)
						inscripcion.estado="PENDIENTE DE DEVOLUCION";
					
					pagarInscripcion(inscripcion);

				} else {
					kos++;
					noCompletados.put(pago, why);
				}
			}

		} catch (FileNotFoundException e) {
			throw new DataException("Error: Fichero no encontrado");
		} catch (IOException e) {
			throw new DataException("Error en la lectura del fichero de pagos");
		}

		generarFicheroPagosNoCompletados(noCompletados);
		int[] datos = new int[2];
		datos[0] = oks;
		datos[1] = kos;
		return datos;
	}

	private void generarFicheroPagosNoCompletados(HashMap<Pago, String> noCompletados) throws DataException {

		String fileName = "pagosErroneos";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("files/" + fileName)))) {
			String text = "** PAGOS NO COMPLETADOS **\n";

			for (Pago pago : noCompletados.keySet()) {
				text += "** Competicion: " + pago.nombreCompeticion + " - Atleta: " + pago.nombreAtleta
						+ " -  Motivo de Pago Incompleto: " + noCompletados.get(pago) + "\n";
			}
			bw.write(text);

		} catch (IOException e) {
			throw new DataException("Error al generar fichero pagos no completados");
		}
	}

	private String comprobarPago(Pago pago) throws DataException {
		String motivo = "";

		HacerInscripcion ins = new HacerInscripcion();
		Atleta atleta = ins.getAtletaByDNI(pago.dni);

		if (atleta == null) {
			motivo = "El dni no corresponde con ningun atleta";
		} else {

			Inscripcion inscripcion = ins.getInscripcion(atleta.id, pago.competicionId);
			pago.atletaId = atleta.id;
			pago.nombreAtleta = atleta.nombre + " " + atleta.apellidos;

			if (Dates.isAfter(pago.fechaPago, Dates.addDays(inscripcion.fecha, 2)))
				motivo = "La fecha de pago está fuera del periodo de pago de 2 dias tras la inscripcion";
			else {
				
				if( pago.cantidad < inscripcion.cantidad)
					motivo = "La cantidad abonada es inferior a la necesaria";
			}
		}
		return motivo;
	}
}
