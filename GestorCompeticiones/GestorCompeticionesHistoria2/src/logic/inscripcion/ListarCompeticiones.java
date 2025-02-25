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
import logic.model.Inscripcion;
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

	
	public List<Competicion> verCompeticionesDisponibles() throws DataException {

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
			throw new DataException("Fallo en la conexion al listar competiciones abiertas");
		}
		return competiciones;
	}
	
	public String verCompeticionInscripcion(Inscripcion ins) throws DataException {

		ResultSet rs = null;
		String name="";
		PreparedStatement ps = null;
		try (Connection c = Jdbc.getConnection()){
		
			ps = c.prepareStatement(Conf.getInstance().getProperty("SQL_GET_NAME_LISTAR"));
			ps.setLong(1, ins.competicionId);
			rs = ps.executeQuery();

			if (rs.next()) {
				name = rs.getString("nombre");
			}
		} catch (SQLException e) {
			throw new DataException("Fallo al ver el nombre de la competicion");
		}
		return name;
	}

	public List<Competicion> verCompeticiones() throws DataException {
		List<Competicion> competiciones = new ArrayList<Competicion>();
		Competicion competicion = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try (Connection c = Jdbc.getConnection()){
		
			ps = c.prepareStatement(Conf.getInstance().getProperty("SQL_VER_COMPETICIONES"));
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
			e.printStackTrace();
			throw new DataException("Fallo en la conexion al listar todas las competiciones");
		}
		return competiciones;
	}

}
