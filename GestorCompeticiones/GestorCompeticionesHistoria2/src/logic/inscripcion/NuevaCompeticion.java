package logic.inscripcion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.exception.DataException;
import logic.model.Competicion;
import logic.model.Plazo;
import util.Conf;
import util.Jdbc;

public class NuevaCompeticion {

	public long obtenerUltimoIdCompeticion() throws DataException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		long id = 0;
		try (Connection c = Jdbc.getConnection()) {
			ps = c.prepareStatement(Conf.getInstance().getProperty("SQL_VER_ULTIMO_ID_COMPETICION"));
			rs = ps.executeQuery();

			if (rs.next()) {
				id = rs.getLong("id")+1;
			}
		} catch (SQLException e) {
			throw new DataException("Fallo al obtener ultimo id de competicion");
		}
		return id;
	}
	
	public void crearCompeticion(Competicion comp) throws DataException{
		PreparedStatement ps = null;

		try (Connection c = Jdbc.getConnection()) {
			ps = c.prepareStatement(Conf.getInstance().getProperty("SQL_ANADIR_COMPETICION"));
			ps.setLong(1, comp.id);
			ps.setString(2, comp.nombre);
			ps.setString(3, comp.tipo);
			ps.setInt(4, comp.km);
			ps.setDate(5, new java.sql.Date(comp.fecha.getTime()));
			ps.setInt(6,comp.plazas);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DataException("Fallo en la conexion de crear competicion");
		}
	}

	public void añadirPlazoCompeticion(Long competicionId, Plazo plazo) throws DataException {
		PreparedStatement ps = null;

		try (Connection c = Jdbc.getConnection()) {
			ps = c.prepareStatement(Conf.getInstance().getProperty("SQL_ANADIR_PLAZO"));
			ps.setDate(1, new java.sql.Date(plazo.fechaInicio.getTime()));
			ps.setDate(2, new java.sql.Date(plazo.fechaFin.getTime()));
			ps.setInt(3,plazo.cuota);
			ps.setLong(4, competicionId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("Fallo al añadir plazo");
		}
	}
}
