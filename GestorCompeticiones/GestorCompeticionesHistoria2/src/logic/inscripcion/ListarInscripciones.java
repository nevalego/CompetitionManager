package logic.inscripcion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		try (Connection c = Jdbc.getConnection()){
		
			ps = c.prepareStatement(Conf.getInstance().getProperty("SQL_VER_INSCRIPCIONES"));
			ps.setLong(1, Long.valueOf(competicion_id));
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

}
