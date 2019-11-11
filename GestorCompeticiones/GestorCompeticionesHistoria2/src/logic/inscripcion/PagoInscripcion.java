package logic.inscripcion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import logic.exception.DataException;
import logic.model.Competicion;
import logic.model.Inscripcion;
import logic.model.Plazo;
import util.Conf;
import util.Dates;
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
				competicion.km = rs.getInt("km");
				competicion.plazas = rs.getInt("plazas");
				competicion.tipo = rs.getString("tipo");
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
				ins.categoriaId= rs.getLong("categoria_id");
				ins.fechaModificacion = rs.getDate("fechamodificacion");
				ins.tiempo = rs.getDouble("tiempo");
				ins.atletaId = rs.getLong("atleta_id");
				ins.competicionId = rs.getLong("competicion_id");
				ins.medioPago = rs.getString("mediopago");
				ins.fechaPago = rs.getDate("fechapago");
				ins.cantidad = rs.getDouble("cantidad");
			}
			return ins;
		} catch (SQLException e) {
			throw new DataException("Error en la conexi�n");
		}
	}

	public void pagarInscripcion(Inscripcion inscripcion, double cantidad, Date fecha) throws DataException {
		PreparedStatement ps = null;
		try (Connection c = Jdbc.getConnection()){
			
			ps = c.prepareStatement(Conf.getInstance().getProperty("SQL_PAGAR_INSCRIPCION"));
			ps.setString(1, "ABONADA");
			ps.setDouble(2, cantidad);
			ps.setDate(3, new java.sql.Date(fecha.getTime()));
			ps.setDate(4, new java.sql.Date(Dates.now().getTime()));// Ultima modificacion estado 
			ps.setLong(5, inscripcion.id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataException("Error en la conexi�n");
		}
	}



	public Plazo obtenerPlazo(Inscripcion ins) throws DataException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Plazo plazo = null;
		try (Connection c = Jdbc.getConnection()){
			
			ps = c.prepareStatement(Conf.getInstance().getProperty("SQL_PLAZO_INSCRIPCION"));
			ps.setLong(1, ins.competicionId);
			ps.setDate(2, new java.sql.Date(Dates.now().getTime()));
			
			rs = ps.executeQuery();
			if(rs.next()) {
				plazo = new Plazo();
				plazo.id = rs.getLong("id");
				plazo.fechaInicio = rs.getDate("fechainicio");
				plazo.fechaFin = rs.getDate("fechafin");
				plazo.cuota = rs.getDouble("cuota");
				plazo.competicionId = rs.getLong("competicion_id");
			}
			
		} catch (SQLException e) {
			throw new DataException("Error en la conexi�n");
		}
		return plazo;
	}
}
