package logic.inscripcion;

import java.sql.*;

import java.util.Date;

import logic.exception.DataException;
import logic.model.Atleta;
import logic.model.Categoria;
import logic.model.Inscripcion;
import util.Conf;
import util.Dates;
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
	public void inscribirse(long atletaId, Long id) throws DataException {
		try (Connection c = Jdbc.getConnection()) {
			if (comprobarNoInscrito(atletaId, id)) {
				if (comprobarFecha(id)) {
					if (comprobarPlazas(id)) {
						String insertar = "INSERT INTO INSCRIPCION (atleta_id,competicion_id,categoria_id,fecha,estado) VALUES(?,?,?,?,?)";
						PreparedStatement s = c.prepareStatement(insertar);
						// HACER SETS
						s.setLong(1, atletaId);
						s.setLong(2, id);
						// TO DO Miguel
						//s.setString(3, calcularCategoria(id, new java.sql.Date(System.currentTimeMillis())));
						s.setLong(3, 1); // Provisional
						s.setDate(4,new java.sql.Date(Dates.now().getTime()));
						s.setString(5, "PENDIENTE DE PAGO");
						s.executeUpdate();
						
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
			throw new DataException("Fallo en la conexion");
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
	private boolean comprobarNoInscrito(long atletaId, Long competicionId) throws DataException {

		try (Connection c = Jdbc.getConnection()) {
			String query = "SELECT * FROM INSCRIPCION WHERE atleta_id = ? AND competicion_id = ? ";
			PreparedStatement s = null;

			s = c.prepareStatement(query);
			s.setLong(1, atletaId);
			s.setLong(2, competicionId);
			ResultSet rs = s.executeQuery();
			if( rs.next())
				return false;
			
		} catch (SQLException e) {
			throw new DataException("Problema con la conexion");
		}
		return true;
	}

	private String calcularCategoria(long competicionId, long atletaId) throws DataException {
		// En esta versi�n la competici�n no importa porque todas tiene las mismas
		// categor�as
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
			if (age >= categoria.getMinEdad() && age <= categoria.getMaxEdad()) {
				return categoria.getNombre();
			}
		}
		throw new DataException("Age " + age + " is not valid for this competition");
	}

	public Inscripcion getInscripcion(long atletaId, long competicionId) throws DataException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Inscripcion inscripcion = null;
		try (Connection c = Jdbc.getConnection()) {

			ps = c.prepareStatement(Conf.getInstance().getProperty("SQL_COMPRUEBA_NO_INSCRITO"));
			ps.setLong(1, atletaId);
			ps.setLong(2, competicionId);
			rs = ps.executeQuery();

			if (rs.next()) {
				inscripcion = new Inscripcion();
				inscripcion.id = rs.getLong("id");
				inscripcion.fecha = rs.getDate("fecha");
				inscripcion.atletaId = rs.getLong("atleta_id");
				inscripcion.competicionId = rs.getLong("competicion_id");
				inscripcion.categoriaId = rs.getLong("categoria_id");
				inscripcion.estado = rs.getString("estado");
			}
		} catch (SQLException e) {
			throw new DataException("Error en la conexi�n");
		}
		return inscripcion;
	}

	public Atleta getAtleta(String email) throws DataException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Atleta atleta = null;
		try (Connection c = Jdbc.getConnection()) {

			ps = c.prepareStatement(Conf.getInstance().getProperty("SQL_SELECT_ATLETA_EMAIL"));
			ps.setString(1, email);
			rs = ps.executeQuery();

			if (rs.next()) {
				atleta = new Atleta();
				atleta.id = rs.getLong("id");
				atleta.dni = rs.getString("dni");
				atleta.nombre = rs.getString("nombre");
				atleta.apellidos = rs.getString("apellidos");
				atleta.email = rs.getString("email");
				atleta.sexo = rs.getString("sexo");
				atleta.fechaNacimiento = rs.getDate("fechanacimiento");
			}
		} catch (SQLException e) {
			throw new DataException("Error en la conexi�n");
		}
		return atleta;
	}

	private String calcularCategoria(Long id, Date date) throws DataException {
		// En esta versi�n la competici�n no importa porque todas tiene las mismas
		// categor�as
		return calculoCategoria(date);
	}

	@SuppressWarnings("deprecation")
	private String calculoCategoria(Date date) throws DataException {
		int age = date.getYear() - new Date().getYear();
		for (Categoria categoria : Parser.parseCategorias(FileUtil.cargarArchivo("categories.properties"))) {
			if (age >= categoria.getMinEdad() && age <= categoria.getMaxEdad()) {
				return categoria.getNombre();
			}
		}
		throw new DataException("Age " + age + " is not valid for this competition");
	}

	
	public Atleta getAtleta(long atletaId) {
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
				result.apellidos = rs.getString("apellidos");
				result.email = rs.getString("email");
				result.sexo = rs.getString("sexo");
				result.fechaNacimiento = rs.getDate("fechaNacimiento");
			}
		} catch (SQLException e) {
			return null;
		}
		return result;
	}

	public long getIdAtleta(String email) {
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
	@SuppressWarnings("deprecation")
	private boolean comprobarFecha(Long id) throws SQLException {
		Date date = new Date();
		String query = "SELECT fecha FROM INSCRIPCION WHERE competicion_id = ?";
		PreparedStatement s = null;
		ResultSet rs = null;
		try (Connection c = Jdbc.getConnection()) {
			s = c.prepareStatement(query);
			s.setLong(1, id);
			rs = s.executeQuery();
			String[] dateToParse = rs.getString(1).split("/");
			Date dateCompetition = new Date(Integer.parseInt(dateToParse[0]), Integer.parseInt(dateToParse[1]),
					Integer.parseInt(dateToParse[2]));
			if (!dateCompetition.before(date)) {
				return false;
			}

		} catch (SQLException e) {
			System.out.println("Problema con la conexion");
		}
		return true;
	}


	/**
	 * Metodo PROVISIONAL que comprueba las plazas de la tabla
	 * 
	 * @param idtabla, el id de la competicion para ver si hay plazas. DUDA: �EL
	 *                 NUMERO DE PLAZAS ESTA EN COMPETICION?
	 * @return true si se puede proceder false si no
	 * @throws SQLException
	 */
	private boolean comprobarPlazas(Long id) throws DataException {
		String query = "SELECT SUM(atleta_id) FROM INSCRIPCION WHERE competicion_id = ?";
		PreparedStatement ps = null;
		int plazasOcupadas = 0;
		try (Connection c = Jdbc.getConnection()) {
			ps = c.prepareStatement(query);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				plazasOcupadas = rs.getInt(1);
		} catch (SQLException e) {
			throw new DataException("Error en la conexi�n");
		}
		// ESTO ES UN EJEMPLO AL NO TENER LA BASE DE DATOS
		String getTotalPlazas = "SELECT PLAZAS FROM COMPETICION WHERE id= ?";
		PreparedStatement ps2 = null;

		try (Connection c = Jdbc.getConnection()) {
			ps2 = c.prepareStatement(getTotalPlazas);
			ps2.setLong(1, id);
			ResultSet rs2 = ps2.executeQuery();
			int plazasTotales =0;
			
			if(rs2.next())
				plazasTotales = rs2.getInt(1);

			if (plazasOcupadas == plazasTotales) {
				return false;
			}
		} catch (SQLException e) {
			throw new DataException("Error en la conexi�n");
		}
		return true;

	}
}
