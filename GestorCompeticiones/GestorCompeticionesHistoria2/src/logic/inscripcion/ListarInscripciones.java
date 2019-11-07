package logic.inscripcion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import logic.model.AtletaInscripcion;
import logic.model.Inscripcion;
import util.Conf;
import util.Jdbc;

public class ListarInscripciones {

	public List<Inscripcion> verInscripcionesAtleta(long atleta_id) {
		List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
		Inscripcion inscripcion = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try (Connection c = Jdbc.getConnection()){
		
			ps = c.prepareStatement(Conf.getInstance().getProperty("SQL_VER_INSCRIPCIONES_ATLETA"));
			ps.setLong(1, Long.valueOf(atleta_id));
			rs = ps.executeQuery();

			while (rs.next()) {
				inscripcion = new Inscripcion();
				inscripcion.id = rs.getLong("id");
				inscripcion.fecha = rs.getDate("fecha");
				inscripcion.categoria = rs.getString("categoria");
				inscripcion.estado = rs.getString("estado");
				inscripcion.atletaId = rs.getLong("atleta_id");
				inscripcion.competicionId = rs.getLong("competicion_id");
				inscripciones.add(inscripcion);
			}
		} catch (SQLException e) {
			System.out.println("Fallo en la conexion");
		}
		return inscripciones;
	}
	
	public List<Inscripcion> verInscripciones(String competicion_id) {
		List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
		Inscripcion inscripcion = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try (Connection c = Jdbc.getConnection()) {

			ps = c.prepareStatement(Conf.getInstance().getProperty("SQL_ATLETA_INSCRIPCION_POR_COMPETICION"));
			ps.setLong(1, Long.valueOf(competicion_id));
			rs = ps.executeQuery();

			while (rs.next()) {
				inscripcion = new Inscripcion();
				inscripcion.id = rs.getLong("id");
				inscripcion.categoria = rs.getString("categoria");
				inscripcion.fecha = new Date(rs.getDate("fecha").getTime());
				inscripcion.estado = rs.getString("estado");
				inscripcion.competicionId = rs.getLong("atleta_id");
				inscripcion.atletaId = rs.getLong("atleta_id");
				inscripciones.add(inscripcion);
			}
		} catch (SQLException e) {
			System.out.println("Fallo en la conexion");
		}
		return inscripciones;
	}
	public List<AtletaInscripcion> verAtletasEInscripciones(String competicion_id) {
		List<AtletaInscripcion> inscripciones = new ArrayList<AtletaInscripcion>();
		AtletaInscripcion inscripcion = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try (Connection c = Jdbc.getConnection()) {

			ps = c.prepareStatement(Conf.getInstance().getProperty("SQL_ATLETA_INSCRIPCION_POR_COMPETICION"));
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
			System.out.println("Fallo en la conexion");
		}
		Collections.sort(inscripciones);
		return inscripciones;
	}

}
