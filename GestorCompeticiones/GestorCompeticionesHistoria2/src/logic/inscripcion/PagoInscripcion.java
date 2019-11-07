package logic.inscripcion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.exception.DataException;
import logic.model.Competicion;
import logic.model.Inscripcion;
import util.Conf;
import util.Jdbc;

public class PagoInscripcion {

	public String mostrarDatosPago( Inscripcion inscripcion){

		String datos = "INFORMACI�N PAGO INSCRIPCI�N\n"
				+ "M�todo de pago: Transferencia Bancaria"
				+ "N�mero de cuenta ES2900 4453 2930 4543 0754 3729\n"
				+ "Cantidad a abonar: �";
		return datos;
	}
	

	
	public Competicion getCompeticion(long inscripcionId) throws DataException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Competicion competicion = null;
		try (Connection c = Jdbc.getConnection()){
			
			ps = c.prepareStatement(Conf.getInstance().getProperty("SQL_COMPETICION_INSCRIPCION"));
			ps.setLong(1, inscripcionId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				competicion = new Competicion();
				competicion.id = rs.getLong("id");
				competicion.fecha = rs.getDate("fecha");
				competicion.nombre= rs.getString("name");
				competicion.cuota = rs.getDouble("cuota");
				competicion.km = rs.getInt("km");
				competicion.plazas = rs.getInt("plazas");
				competicion.tipo = rs.getString("tipo");
				competicion.inicioInscripcion = rs.getDate("inicioinscripcion");
				competicion.finInscripcion = rs.getDate("fininscripcion");
			}
		} catch (SQLException e) {
			throw new DataException("Error en la conexi�n");
		}
		return competicion;
	}
	
	public Inscripcion obtenerInscripcion(long atletaId, long competicionId) throws DataException {
		PreparedStatement ps = null;
		Inscripcion ins = null;
		try(Connection c = Jdbc.getConnection()){
			ps = c.prepareStatement(Conf.getInstance().getProperty("SQL_INSCRIPCION_ATLETA_COMPETICION"));
			ps.setLong(1, atletaId);
			ps.setLong(2,competicionId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				ins = new Inscripcion();
				ins.id= rs.getLong("id");
				ins.fecha = rs.getDate("fecha");
				ins.estado = rs.getString("estado");
				ins.categoria = rs.getString("categoria");
				ins.atletaId = rs.getLong("atleta_id");
				ins.competicionId = rs.getLong("competicion_id");
			}
			return ins;
		} catch (SQLException e) {
			throw new DataException("Error en la conexi�n");
		}
	}

	public void pagarInscripcion(Inscripcion inscripcion) throws DataException {
		PreparedStatement ps = null;
		try (Connection c = Jdbc.getConnection()){
			
			ps = c.prepareStatement(Conf.getInstance().getProperty("SQL_PAGAR_INSCRIPCION"));
			ps.setString(1, "ABONADA");
			ps.setLong(2, inscripcion.id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataException("Error en la conexi�n");
		}
	}
}
