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

public class PagoInscripción {

	public String mostrarDatosPago( Inscripcion inscripcion){

		String datos = "INFORMACIÓN PAGO INSCRIPCIÓN\n"
				+ "Método de pago: Transferencia Bancaria"
				+ "Número de cuenta ES2900 4453 2930 4543 0754 3729\n"
				+ "Cantidad a abonar: "+ inscripcion.cantidad+ "€";
		return datos;
	}
	
	public boolean comprobarFechaPago( Inscripcion inscripcion, Date fechaPago ) {
		boolean enRegla = false;// Pago valido 2 dias despues de la fecha de inscripcion
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		try (Connection c = Jdbc.getConnection()){
		
			ps = c.prepareStatement(Conf.getInstance().getProperty("SQL_CHECK_FECHA_PAGO"));
			Date now = Dates.now();
			ps.setDate(1, new java.sql.Date(now.getTime()));
			rs = ps.executeQuery();

			if (rs.next()) {
				
			}
		} catch (SQLException e) {
			System.out.println("Fallo en la conexion");
		}
		return enRegla;
	}
}
