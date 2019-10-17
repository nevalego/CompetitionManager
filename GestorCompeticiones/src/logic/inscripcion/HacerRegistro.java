package logic.inscripcion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import logic.exception.DataException;

public class HacerRegistro {
	private final String URL = "";
	private final String user = "sa";
	private final String pass = "";
	private Connection c;

	/**
	 * Metodo que comprueba que si cuando se va a registrar un atleta ya esta
	 * registrado
	 * 
	 * @param email,
	 *            el email del atleta a registrar
	 * @param dni,
	 *            el dni del atleta a registrar
	 * @return true si ya existe algun atleta asi, false si no
	 * @throws DataException,
	 *             si se produce un error en la inserccion, el mensaje sera el
	 *             imprimido por pantalla
	 */
	public boolean comprobarDatos(String email, String dni) throws DataException {
		try {
			c = DriverManager.getConnection(URL, user, pass);
		} catch (SQLException e) {
			System.out.println("Fallo al obtener la conexion");
		}
		try {
			PreparedStatement s = c.prepareStatement("SELECT * FROM ATLETA WHERE EMAIL = ?");
			s.setString(1, email);
			PreparedStatement s2 = c.prepareStatement("SELECT * FROM ATLETA WHERE DNI = ?");
			s.setString(1, dni);
			if (s.execute() && s2.execute())
				return true;
			return false;
		} catch (SQLException e) {
			throw new DataException("Email o dni ya registrados");
		}
	}


	/**
	 * Metodo que inserta al atleta el la base de datos
	 * @param nombre, El nombre del atleta
	 * @param Apellido, el apellido del atleta
	 * @param Email, el email del atleta
	 * @param dni, el dni del atleta
	 * @param genero, el genero del atleta
	 * @param dia, el dia de nacimiento del atleta
	 * @param mes, el mes de nacimiento del atleta
	 * @param año, el año de nacimiento del atleta
	 */
	public void registrar(String nombre, String Apellido, String Email, String dni, String genero, String dia,
			String mes, String año) {
		try {
			c = DriverManager.getConnection(URL, user, pass);
			PreparedStatement s = c.prepareStatement("INSERT INTO ATLETA VALUES(?,?,?,?,?,?,?,?,?)");
			
			//Sacamos el id anterior(mas alto)
			Statement s2 = c.createStatement(); //COMPROBAR SQL
			ResultSet rs = s2.executeQuery("SELECT IDATLETA FROM ATLETA WHERE IDATLETA >= ALL IN (SELECT ID FROM ATLETA)"); //COMPROBAR SQL
			
			int id = rs.getInt(1);
			s.setInt(1, id+1);
			s.setString(2, nombre);
			s.setString(3,Apellido);
			s.setString(4,Email);
			s.setString(5,dni);
			s.setString(6,genero);
			s.setString(7,dia);
			s.setString(8,mes);
			s.setString(9,año);
			s.executeQuery();
		} catch (SQLException e) {
			System.out.println("Fallo al obtener la conexion");
		}

	}

	/**
	 * Metodo para comprobar que es una fecha valida
	 * @param dia, el dia elegido
	 * @param mes, el mes elegido
	 * @param año, el año elegido
	 * @return true si la fecha es valida, false si no
	 */
	public boolean comprobarFecha(String dia, String mes, String año) {
		String[] mesCon31 = new String[] {"Enero","Marzo","Mayo","Julio","Agosto","Octubre","Diciembre"}; 
		for(String meslist: mesCon31) {
			if(dia.equals("31") && mes.equals(meslist))return false; 
		}
		if(dia.equals("30") && mes.equals("Febrero")) return false;
		
		return true;
	}
}
