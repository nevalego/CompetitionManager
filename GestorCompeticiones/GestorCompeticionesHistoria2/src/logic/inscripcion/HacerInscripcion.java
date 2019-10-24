package logic.inscripcion;

import java.sql.*;

import java.util.Date;

import logic.exception.DataException;
import logic.model.Atleta;
import logic.model.Categoria;
import util.FileUtil;
import util.Jdbc;
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

	private Connection c; // Guardamos la conexion para no crearla en todas las comprobaciones

	/**
	 * Metodo principal de la inscripcion
	 * 
	 * @param datos, los datos del atleta, es provisional
	 * @throws SQLException, si hace algun fallo, aunque se modificara por una
	 *                       excepcion creada por nosotros igual
	 * @throws DataException
	 */
	public void inscribirse(String email, Long id) throws DataException {
		try {
			c = Jdbc.getConnection();
		} catch (SQLException e) {
			System.out.println("Fallo en la conexion ");
		}
		try {
			if (comprobarNoInscrito(email, id)) {
				long idAtleta = getIdAtleta(email); // PONER IDATLETA AQUI
				if (comprobarFecha(id)) {
					if (comprobarPlazas(id)) {
						String insertar = "INSERT INTO INSCRIPCION VALUES(?,?,?,?,?,?)";
						PreparedStatement s = c.prepareStatement(insertar);
						// HACER SETS
						s.setLong(5, idAtleta);
						System.out.println("ID ATLETA: " + idAtleta + " ID COMPETICION"+ id);
						s.setLong(6, id);
						s.setLong(1, getUltimaCompeticion()+1);
						s.setString(4, calcularCategoria(id, idAtleta));
						s.setString(2, "" + new java.sql.Date(System.currentTimeMillis()));
						s.setString(3, "PRE-INSCRITO");
						s.executeUpdate();
						s.close();
						c.close();
					} else {
						throw new DataException("Plazas Acabadas");
					}
				} else {
					throw new DataException("Fecha Pasada");
				}
			} else {
				throw new DataException("Ya estas inscrito o no esta registrado");
			}
		} catch (SQLException e) {
			System.out.println("Fallo en la query inscribirse");
			e.printStackTrace();
		}
	}

	private String calcularCategoria(long competicionId, long atletaId) throws DataException {
		// En esta versión la competición no importa porque todas tiene las mismas
		// categorías
		return calculoCategoria(atletaId);
	}
	
	private int getUltimaCompeticion() {
		Statement s2;
		try {
			s2 = c.createStatement();
			ResultSet rs = s2.executeQuery("SELECT MAX(ID) FROM INSCRIPCION"); 
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			System.out.println("FALLO EN OBTENCION DEL ID");
		} 
		return -1;
		
	}

	private String calculoCategoria(long atletaId) throws DataException {
		Date hoy = new Date();
		Date fechaNacimiento = getAtleta(atletaId).fechaNacimiento;
		@SuppressWarnings("deprecation")
		int age = hoy.getYear() - fechaNacimiento.getYear();
		for (Categoria categoria : Parser.parseCategorias(FileUtil.cargarArchivo("categories.properties"))) {
			if (age >= categoria.getMinAge() && age <= categoria.getMaxAge()) {
				return categoria.getName();
			}
		}
		throw new DataException("Age " + age + " is not valid for this competition");
	}

	private Atleta getAtleta(long atletaId) {
		PreparedStatement ps;
		Atleta result = null;
		try {
			ps = c.prepareStatement("SELECT * FROM ATLETA WHERE ID = ?");
			ps.setLong(1, atletaId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = new Atleta();
				result.id = rs.getLong("idAtleta");
				result.dni = rs.getString("dni");
				result.nombre = rs.getString("nombre");
				result.apellido = rs.getString("apellido");
				result.email = rs.getString("email");
				result.sexo = rs.getString("sexo");
				result.fechaNacimiento = rs.getDate("fechaNacimiento");
			}
		} catch (SQLException e) {
			return null;
		}
		return result;
	}

	private long getIdAtleta(String email) {
		PreparedStatement ps;
		try {
			ps = c.prepareStatement("SELECT ID FROM ATLETA WHERE EMAIL = ?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getLong("ID");
		} catch (SQLException e) {
			return -5;
		}

	}

	/**
	 * Metodo que comprueba si el atleta esta ya inscrito o no
	 * 
	 * @param EL id, dni o primary key de la clase atleta para comprobar en la tabla
	 *           inscripcion
	 * @param EL id, dni o primary key de la clase competicion para comprobar en la
	 *           tabla inscripcion
	 * @return true si se puede proceder, false si no
	 * @throws SQLException, es una estandarizacion por si falla algo, imprime
	 *                       mensaje habitualmente
	 */
	private boolean comprobarNoInscrito(String email, Long id) throws SQLException {
		String querySacaId = "SELECT ID FROM ATLETA WHERE EMAIL = ?";
		PreparedStatement ps = c.prepareStatement(querySacaId);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		int idatleta = 0;
		if (rs.next()) {
			idatleta = rs.getInt(1);
		} else {
			return false;
		}
		String query = "SELECT * FROM INSCRIPCION WHERE ATLETA_ID = ? AND COMPETICION_ID = ? ";
		PreparedStatement s = null;
		try {
			s = c.prepareStatement(query);
			s.setInt(1, idatleta);
			s.setLong(2, id);
		} catch (SQLException e) {
			System.out.println("Problema con la conexion");
		}
		try {
			s.execute();
		} catch (SQLException e) {
			return false;
		} 
		return true;
	}

	/**
	 * Metodo PROVISIONAL que comprueba la fecha de la tabla
	 * 
	 * @param deberian pasarse, idatleta, idtabla, fecha actual
	 * @return true si se puede proceder false si no
	 * @throws SQLException, cualquier tipo de exception si falla es un ejemplo
	 */
	private boolean comprobarFecha(Long id) throws SQLException {
		Date date = new Date();
		String query = "SELECT INICIOINSCRIPCION FROM COMPETICION WHERE ID = ?";
		PreparedStatement s = null;
		ResultSet rs = null;
		try {
			s = c.prepareStatement(query);
			s.setLong(1, id);

			rs = s.executeQuery();
			rs.next();
			Date dateCompetition = new Date(rs.getDate("INICIOINSCRIPCION").getTime());
			if (!dateCompetition.before(date)) {

				return false;
			}
		} catch (SQLException e) {
			System.out.println("Problema con la query fecha");
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * Metodo PROVISIONAL que comprueba las plazas de la tabla
	 * 
	 * @param idtabla, el id de la competicion para ver si hay plazas. DUDA: ¿EL
	 *                 NUMERO DE PLAZAS ESTA EN COMPETICION?
	 * @return true si se puede proceder false si no
	 * @throws SQLException
	 */
	private boolean comprobarPlazas(Long id) throws SQLException {
		String query = "SELECT SUM(DISTINCT COMPETICION_ID) FROM INSCRIPCION WHERE COMPETICION_ID = ?";
		PreparedStatement ps = null;

		ps = c.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int plazasActuales = rs.getInt(1);

		// ESTO ES UN EJEMPLO AL NO TENER LA BASE DE DATOS
		String getTotalPlazas = "SELECT PLAZAS FROM COMPETICION WHERE ID = ?";
		PreparedStatement ps2 = null;
		ps2 = c.prepareStatement(getTotalPlazas);
		ps2.setLong(1, id);
		ResultSet rs2 = ps2.executeQuery();
		rs2.next();
		int plazasTotales = rs2.getInt(1);

		if (plazasActuales >= plazasTotales) {

			return false;
		}
		return true;

	}
}
