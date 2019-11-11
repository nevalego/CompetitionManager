package logic.inscripcion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import logic.dto.InscripcionDto;
import logic.exception.DataException;
import logic.model.AtletaInscripcion;
import logic.model.Inscripcion;
import util.Conf;
import util.Jdbc;

public class ListarInscripciones {

	public List<Inscripcion> verInscripcionesAtleta(long atleta_id) throws DataException {
		List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
		Inscripcion ins = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try (Connection c = Jdbc.getConnection()) {

			ps = c.prepareStatement(Conf.getInstance()
					.getProperty("SQL_VER_INSCRIPCIONES_ATLETA"));
			ps.setLong(1, Long.valueOf(atleta_id));
			rs = ps.executeQuery();

			while (rs.next()) {
				ins = new Inscripcion();
				ins.id= rs.getLong("id");
				ins.fecha = rs.getDate("fecha");
				ins.estado = rs.getString("estado");
				ins.categoriaId= rs.getLong("categoria_id");
				ins.fechaModificacion = rs.getDate("fechamodificacion");
				ins.tiempo = rs.getDouble("tiempo");
				ins.atletaId = rs.getLong("atleta_id");
				ins.competicionId = rs.getLong("competicion_id");
				ins.medioPago = rs.getString("mediopago");
				ins.fechaPago = rs.getDate("fechapago");
				ins.cantidad = rs.getDouble("cantidad");
				inscripciones.add(ins);
			}
		} catch (SQLException e) {
			throw new DataException("Fallo en la conexion");
		}
		return inscripciones;
	}

	public List<Inscripcion> verInscripciones(String competicion_id) throws DataException {
		List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
		Inscripcion ins = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try (Connection c = Jdbc.getConnection()) {

			ps = c.prepareStatement(Conf.getInstance()
					.getProperty("SQL_ATLETA_INSCRIPCION_POR_COMPETICION"));
			ps.setLong(1, Long.valueOf(competicion_id));
			rs = ps.executeQuery();

			while (rs.next()) {
				ins = new Inscripcion();
				ins.id= rs.getLong("id");
				ins.fecha = rs.getDate("fecha");
				ins.estado = rs.getString("estado");
				ins.categoriaId= rs.getLong("categoria_id");
				ins.fechaModificacion = rs.getDate("fechamodificacion");
				ins.tiempo = rs.getDouble("tiempo");
				ins.atletaId = rs.getLong("atleta_id");
				ins.competicionId = rs.getLong("competicion_id");
				ins.medioPago = rs.getString("mediopago");
				ins.fechaPago = rs.getDate("fechapago");
				ins.cantidad = rs.getDouble("cantidad");
				inscripciones.add(ins);
			}
		} catch (SQLException e) {
			throw new DataException("Fallo en la conexion");
		}
		return inscripciones;
	}

	public List<AtletaInscripcion> verAtletasEInscripciones(
			String competicion_id) throws DataException {
		List<AtletaInscripcion> inscripciones = new ArrayList<AtletaInscripcion>();
		AtletaInscripcion inscripcion = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try (Connection c = Jdbc.getConnection()) {

			ps = c.prepareStatement(Conf.getInstance()
					.getProperty("SQL_ATLETA_INSCRIPCION_POR_COMPETICION"));
			ps.setLong(1, Long.valueOf(competicion_id));
			rs = ps.executeQuery();

			while (rs.next()) {
				inscripcion = new AtletaInscripcion();
				inscripcion.dni = rs.getString("dni");
				inscripcion.nombre = rs.getString("nombre");
				inscripcion.apellido = rs.getString("apellido");
				inscripcion.categoria = rs.getString("categoria");
				inscripcion.fecha = new Date(rs.getDate("fecha").getTime());
				inscripcion.estado = rs.getString("estado");
				inscripciones.add(inscripcion);
			}
		} catch (SQLException e) {
			throw new DataException("Fallo en la conexion");		
		}
		Collections.sort(inscripciones);
		return inscripciones;
	}

	public List<InscripcionDto> findInscripcion(String email)
			throws DataException {
		List<InscripcionDto> list = new ArrayList<InscripcionDto>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try (Connection c = Jdbc.getConnection()) {

			ps = c.prepareStatement(
					Conf.getInstance().getProperty("SQL_GET_ID_FROM_EMAIL"));
			ps.setString(1, email);
			rs = ps.executeQuery();
			rs.next();
			long idatleta = rs.getLong("ID");
			rs.close();
			ps.close();

			ps = c.prepareStatement(Conf.getInstance()
					.getProperty("SQL_VER_INSCRIPCIONES_POR_EMAIL"));
			ps.setLong(1, idatleta);
			rs = ps.executeQuery();

			while (rs.next()) {
				PreparedStatement ps2 = c.prepareStatement(
						Conf.getInstance().getProperty("SQL_GET_NAME_FROM_ID"));
				ps2.setLong(1, rs.getLong("COMPETICION_ID"));
				ResultSet rs2 = ps2.executeQuery();
				rs2.next();
				String nombreCompeticion = rs2.getString("NOMBRE");
				rs2.close();
				ps2.close();
				InscripcionDto incs = new InscripcionDto();
				incs.estado = rs.getString("ESTADO");
				incs.fechaModificacion = rs.getDate("FECHAMODIFICACION");
				incs.nombreCompeticion = nombreCompeticion;
				list.add(incs);
			}
		} catch (SQLException e) {
			throw new DataException("Fallo buscando la inscripcion con email");

		}
		return list;
	}

}
