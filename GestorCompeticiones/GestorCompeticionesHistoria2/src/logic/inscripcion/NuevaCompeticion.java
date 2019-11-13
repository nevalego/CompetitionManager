package logic.inscripcion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.exception.DataException;
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
			throw new DataException("Fallo en la conexion");
		}
		return id;
	}
}
