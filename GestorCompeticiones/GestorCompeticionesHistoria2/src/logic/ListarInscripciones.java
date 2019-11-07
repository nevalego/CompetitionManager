package logic.inscripcion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.dto.InscripcionDto;
import logic.exception.DataException;
import util.Conf;
import util.Jdbc;

public class ListarInscripciones {

	public List<InscripcionDto> findInscripcion(String email) throws DataException {
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
			rs.next();
			
			PreparedStatement ps2 = c.prepareStatement(
					Conf.getInstance().getProperty("SQL_GET_NAME_FROM_ID"));
			ps2.setLong(1,rs.getLong("COMPETICION_ID"));
			ResultSet rs2 = ps2.executeQuery();
			rs2.next();
			String nombreCompeticion = rs2.getString("NOMBRE");
			rs2.close();
			ps2.close();
			
			while(rs.next()) {
				InscripcionDto incs = new InscripcionDto();
				incs.estado = rs.getString("ESTADO");
				incs.fechaModificacion = rs.getDate("FECHAMODIFICACION");
				incs.nombreCompeticion = nombreCompeticion;
				list.add(incs);
			}
		} catch (SQLException e) {
			System.out.println(
					"Fallo buscando la inscripcion con email: " + email);
			e.printStackTrace();
			throw new DataException("Fallo buscando la inscripcion con email");
			
		}
		return list;
	}
}
