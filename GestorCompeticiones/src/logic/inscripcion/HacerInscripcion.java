package logic.inscripcion;

import java.sql.*;

import java.util.Date;

import logic.exception.DataException;
import logic.model.Categoria;
import util.FileUtil;
import util.Parser;

/**
 * Clase que ejecutara la inscripcion del atleta
 * 
 * @author Aitor
 *
 */
public class HacerInscripcion {
	/*
	 * Parametros de conexion
	 */
	private final String URL = "jdbc:oracle:thin:@156.35.94.99:1521:DESA";
	private final String user = "UO264476";
	private final String pass = "PASSWORD";
	private Connection c; // Guardamos la conexion para no crearla en todas las comprobaciones

	/**
	 * Metodo principal de la inscripcion
	 * 
	 * @param datos,
	 *            los datos del atleta, es provisional
	 * @throws SQLException,
	 *             si hace algun fallo, aunque se modificara por una excepcion
	 *             creada por nosotros igual
	 * @throws DataException
	 */
	public void inscribirse(String email,int idcompeticion) throws  DataException {
		try {
			c = DriverManager.getConnection(URL, user, pass);
			if (comprobarNoInscrito(email, idcompeticion)) {
				int idAtleta=0;		//PONER IDATLETA AQUI
				if (comprobarFecha(idcompeticion)) {
					if (comprobarPlazas(idcompeticion)) { 
						String insertar = "INSERT INTO INSCRIPCION VALUES(?,?,?,?,?)";
						PreparedStatement s = c.prepareStatement(insertar);
						//HACER SETS
						s.setInt(1, idAtleta);
						s.setInt(2, idcompeticion);
						
						//COGER LA CATEGORIA DE MIGUEL
						s.setString(3,"categoria");
						s.setString(4,""+ new java.sql.Date(System.currentTimeMillis()));
						s.setString(5, "PRE-INSCRITO");
						s.setString(5, calcularCategoria(idcompeticion, new java.sql.Date(System.currentTimeMillis()));
						s.execute(insertar);
						s.close();
						c.close();
					} else {
						throw new DataException("Plazas Acabadas");
					}
				} else {
					throw new DataException("Fecha Pasada");
				}
			} else {
				throw new DataException("Ya estas inscrito");
			}
		} catch (SQLException e) {
			System.out.println("Fallo en la conexion"); // Imprimirlo en un JDialog
		}
	}


	private String calcularCategoria(int idcompeticion, Date date) throws DataException {
		// En esta versión la competición no importa porque todas tiene las mismas categorías
		return calculoCategoria(date);
	}

	private String calculoCategoria(Date date) throws DataException {
		int age = date.getYear() - new Date().getYear();
		for (Categoria categoria : Parser.parseCategorias(FileUtil.cargarArchivo(CATEGORIAS))) {
			if (age >= categoria.getMinAge() && age <= categoria.getMaxAge()) {
				return categoria.getName();
			}
		}
		throw new DataException("Age " + age + " is not valid for this competition");
	}
	/**
	 * Metodo que comprueba si el atleta esta ya inscrito o no
	 * 
	 * @param EL
	 *            id, dni o primary key de la clase atleta para comprobar en la
	 *            tabla inscripcion
	 * @param EL
	 *            id, dni o primary key de la clase competicion para comprobar en la
	 *            tabla inscripcion
	 * @return true si se puede proceder, false si no
	 * @throws SQLException,
	 *             es una estandarizacion por si falla algo, imprime mensaje
	 *             habitualmente
	 */
	private boolean comprobarNoInscrito(String email, int idTabla) throws SQLException {
		String querySacaId = "SELECT IDATLETA FROM ATLETA WHERE EMAIL = ?";
		PreparedStatement ps = c.prepareStatement(querySacaId);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		int idatleta;
		if(rs.next()) {idatleta = rs.getInt(1);}else {return false;}
		String query = "SELECT * FROM INSCRIPCION WHERE IDATLETA = ? AND IDCOMPETICION = ? ";
		PreparedStatement s = null;
		try {
			s = c.prepareStatement(query);
			s.setInt(1, idatleta);
			s.setInt(2, idTabla);
		} catch (SQLException e) {
			System.out.println("Problema con la conexion");
		}
		try {
			s.execute();
		} catch (SQLException e) {
			return false;
		} finally {
			s.close();
			c.close();
		}
		return true;
	}

	/**
	 * Metodo PROVISIONAL que comprueba la fecha de la tabla
	 * 
	 * @param deberian
	 *            pasarse, idatleta, idtabla, fecha actual
	 * @return true si se puede proceder false si no
	 * @throws SQLException,
	 *             cualquier tipo de exception si falla es un ejemplo
	 */
	private boolean comprobarFecha(int idTabla) throws SQLException {
		Date date = new Date();
		String query = "SELECT FECHAINSCRIPCION FROM INSCRIPCION WHERE IDCOMPETICION = ?";
		PreparedStatement s = null;
		try {
			s = c.prepareStatement(query);
			s.setInt(1, idTabla);
		} catch (SQLException e) {
			System.out.println("Problema con la conexion");
		}
		ResultSet rs = s.executeQuery();
		String[] dateToParse = rs.getString(1).split("/");
		Date dateCompetition = new Date(Integer.parseInt(dateToParse[0]),Integer.parseInt(dateToParse[1]),Integer.parseInt(dateToParse[2]));
		if (!dateCompetition.before(date)){ 
			s.close();
			c.close();
			return false;
		}
		rs.close();
		s.close();
		c.close();

		return true;
	}

	/**
	 * Metodo PROVISIONAL que comprueba las plazas de la tabla
	 * 
	 * @param idtabla,
	 *            el id de la competicion para ver si hay plazas. DUDA: ¿EL NUMERO
	 *            DE PLAZAS ESTA EN COMPETICION?
	 * @return true si se puede proceder false si no
	 * @throws SQLException
	 */
	private boolean comprobarPlazas(int idtabla) throws SQLException {
		String query = "SELECT SUM(DISTINCT IDCOMPETICION) FROM INSCRIPCION WHERE IDCOMPETICION = ?";
		PreparedStatement ps = null;

		ps = c.prepareStatement(query);
		ps.setInt(1, idtabla);
		ResultSet rs = ps.executeQuery();
		int plazasActuales = rs.getInt(1);

		// ESTO ES UN EJEMPLO AL NO TENER LA BASE DE DATOS
		String getTotalPlazas = "SELECT PLAZAS FROM COMPETICION WHERE IDTABLA = ?";
		PreparedStatement ps2 = null;
		ps2 = c.prepareStatement(getTotalPlazas);
		ps2.setInt(1, idtabla);
		ResultSet rs2 = ps2.executeQuery();
		int plazasTotales = rs2.getInt(1);

		if (plazasActuales >= plazasTotales) {
			rs.close();
			rs2.close();
			ps.close();
			ps2.close();
			c.close();
			return false;
		}
		rs.close();
		rs2.close();
		ps.close();
		ps2.close();
		c.close();
		return true;

	}
}

