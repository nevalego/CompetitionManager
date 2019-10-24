package logic.inscripcion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logic.model.Competicion;
import logic.model.Inscripcion;
import util.Conf;
import util.Dates;
import util.Jdbc;

public class ListarInscripciones {

	public List<Inscripcion> verInscripciones(String string) {
		List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
		Inscripcion inscripcion = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try (Connection c = Jdbc.getConnection()){
		
			ps = c.prepareStatement(Conf.getInstance().getProperty("SQL_VER_COMPETICIONES_ABIERTAS"));
			Date now = Dates.now();
			ps.setDate(1, new java.sql.Date(now.getTime()));
			rs = ps.executeQuery();

			while (rs.next()) {
				inscripcion = new Inscripcion();
				inscripcion.id = rs.getLong("id");
				inscripcion.fecha = rs.getDate("fecha");
				inscripcion.categoria = rs.getString("categoria");
				inscripcion.estado = rs.getString("estado");
				inscripcion.atletaId = rs.getLong("atletaId");
				inscripcion.competicionId = rs.getLong("competicionId");
				inscripciones.add(inscripcion);
			}
		} catch (SQLException e) {
			System.out.println("Fallo en la conexion");
		}
		return inscripciones;
	}

}
