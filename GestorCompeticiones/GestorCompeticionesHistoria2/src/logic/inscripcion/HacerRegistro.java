package logic.inscripcion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import logic.exception.DataException;
import logic.model.Atleta;
import util.Jdbc;

public class HacerRegistro {

	private Connection c;

	/**
	 * Metodo que comprueba que si cuando se va a registrar un atleta ya esta
	 * registrado
	 * 
	 * @param email, el email del atleta a registrar
	 * @param dni,   el dni del atleta a registrar
	 * @return true si ya existe algun atleta asi, false si no
	 * @throws DataException, si se produce un error en la inserccion, el
	 *                        mensaje sera el imprimido por pantalla
	 */
	public boolean comprobarDatos(String email, String dni)
			throws DataException {
		try {
			c = Jdbc.getConnection();
		} catch (SQLException e) {
			System.out.println("Fallo al obtener la conexion");
		}
		try {
			PreparedStatement s = c.prepareStatement(
					"SELECT * FROM ATLETA WHERE EMAIL = ? OR DNI = ?");
			s.setString(1, email);
			s.setString(2, dni);
			ResultSet rs = null;
			rs = s.executeQuery();
			if (rs.next())
				return false;
			return true;
		} catch (SQLException e) {
			throw new DataException("Email o dni ya registrados");
		}
	}

	/**
	 * Metodo que inserta al atleta el la base de datos
	 * 
	 * @param nombre,   El nombre del atleta
	 * @param Apellido, el apellido del atleta
	 * @param Email,    el email del atleta
	 * @param dni,      el dni del atleta
	 * @param genero,   el genero del atleta
	 * @param dia,      el dia de nacimiento del atleta
	 * @param mes,      el mes de nacimiento del atleta
	 * @param a�o,      el a�o de nacimiento del atleta
	 */
	public void registrar(Atleta adto) {
		try {
			c = Jdbc.getConnection();

		} catch (SQLException e) {
			System.err.println("Fallo al obtener la conexion en el registro");
		}
		try {
			PreparedStatement s = c.prepareStatement(
					"INSERT INTO ATLETA VALUES(?,?,?,?,?,?,?)");
			// Sacamos el id anterior(mas alto)
			Statement s2 = c.createStatement(); // COMPROBAR SQL
			ResultSet rs = s2.executeQuery("SELECT MAX(ID) FROM ATLETA"); // COMPROBAR
																			// SQL
			rs.next();
			int id = rs.getInt(1);
			s.setInt(1, id + 1);
			s.setString(3, adto.nombre);
			s.setString(4, adto.apellidos);
			s.setString(5, adto.email);
			s.setString(2, adto.dni);

			Date fechaNacimiento = new Date(adto.fechaNacimiento.getTime());
			s.setDate(7, fechaNacimiento);
			s.setString(6, adto.sexo);
			s.execute();
		} catch (SQLException e) {
			System.err.println("Fallo con la query de registro");
		}

	}

	/**
	 * Metodo para comprobar que es una fecha valida
	 * 
	 * @param dia, el dia elegido
	 * @param mes, el mes elegido
	 * @param a�o, el a�o elegido
	 * @return true si la fecha es valida, false si no
	 */
	public boolean comprobarFecha(String dia, String mes, String año) {
		String[] mesCon31 = new String[] { "Enero", "Marzo", "Mayo", "Julio",
				"Agosto", "Octubre", "Diciembre" };
		for (String meslist : mesCon31) {
			if (dia.equals("31") && mes.equals(meslist))
				return false;
		}
		if (dia.equals("30") && mes.equals("Febrero"))
			return false;

		return true;
	}
}
