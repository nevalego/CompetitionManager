package logic.inscripcion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logic.exception.DataException;
import logic.model.Competicion;
import util.Conf;

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
	private final String URL = "jdbc:oracle:thin:@156.35.94.99:1521:DESA";
	private final String user = "UO264476";
	private final String pass = "PASSWORD";


	public List<Competicion> verCompeticiones(String email) throws DataException {

		List<Competicion> competiciones = new ArrayList<Competicion>();
		Competicion competicion = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try (Connection c = DriverManager.getConnection(URL, user, pass)){
		
			ps = c.prepareStatement(Conf.getInstance().getProperty("SQL_VER_COMPETICIONES_ABIERTAS"));
			Date now = new Date();

			ps.setDate(1, new java.sql.Date(now.getTime()));
			rs = ps.executeQuery();

			while (rs.next()) {
				competicion = new Competicion();
				competicion.id = rs.getLong("id");
				competicion.nombre = rs.getString("nombre");
				competicion.tipo = rs.getString("tipo");
				competicion.km = rs.getInt("km");
				competicion.cuota = rs.getDouble("cuota");
				competicion.fecha = rs.getDate("fecha");
				competicion.plazas = rs.getInt("plazas");
				competicion.inicioInscripcion = rs.getDate("inicioinscripcion");
				competicion.finInscripcion = rs.getDate("fininscripcion");
				competiciones.add(competicion);
			}
		} catch (SQLException e) {
			System.out.println("Fallo en la conexion");
		}
		return competiciones;
	}

}
