package logic.inscripcion;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.exception.DataException;
import logic.model.Competicion;
import logic.model.Inscripcion;
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
				ins.tiempo = rs.getDouble("tiempo");
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
			ps.setString(1, "ABONADA");
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
				plazo.cuota = rs.getDouble("cuota");
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

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("files/"+fileName)))) {
			String text = "** JUSTIFICANTE DE PAGO **\n" + "Nombre Competicion: " + ins.nombreCompeticion + "\n"
					+ "Nombre Atleta: " + ins.nombreAtleta + "\n" + "Fecha pago: " + ins.fechaPago + "\n" + "Medio pago: "
					+ ins.medioPago + "\n" + "Cantidad abonada: " + ins.cantidad + " €";

			bw.write(text);

		} catch (IOException e) {
			throw new DataException("Error al generar justificante de pago");
		}
	}
}
