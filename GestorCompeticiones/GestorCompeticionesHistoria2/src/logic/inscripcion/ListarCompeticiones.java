package logic.inscripcion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logic.exception.DataException;
import logic.model.Competicion;
import util.Conf;
import util.Dates;
import util.Jdbc;

/**
 * Clase que ejecutara el listado de competiciones
 * 
 * @author Nerea
 *
 */
public class ListarCompeticiones {
	/*
	 * Parametros de conexion
	 */
	//private final String URL = "jdbc:oracle:thin:@156.35.94.99:1521:DESA";
	//private final String user = "UO264476";
	//private final String pass = "PASSWORD";
	
	public List<Competicion> verCompeticiones(String email) throws DataException {

		List<Competicion> competiciones = new ArrayList<Competicion>();
		Competicion competicion = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try (Connection c = Jdbc.getConnection()){
		
			ps = c.prepareStatement(Conf.getInstance().getProperty("SQL_VER_COMPETICIONES_ABIERTAS"));
			Date now = Dates.now();
			ps.setDate(1, new java.sql.Date(now.getTime()));
			rs = ps.executeQuery();

			while (rs.next()) {
				competicion = new Competicion();
				competicion.id = rs.getLong("id");
				competicion.fecha = rs.getDate("fecha");
				competicion.nombre= rs.getString("nombre");
				competicion.km = rs.getInt("km");
				competicion.plazas = rs.getInt("plazas");
				competicion.tipo = rs.getString("tipo");
				competiciones.add(competicion);
			}
		} catch (SQLException e) {
			throw new DataException("Fallo en la conexion");
		}
		return competiciones;
	}

}
