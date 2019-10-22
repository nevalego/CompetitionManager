package logic.inscripcion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import logic.exception.DataException;
import logic.model.Atleta;

public class ObtenerInscripciones {

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
	public List<Atleta> obtener(long idCompeticion) throws DataException {
		List<Atleta> atletas = new ArrayList<Atleta>();
		
		String query = "SELECT a.* FROM ATLETA a, INSCRIPCION i WHERE a.IDATLETA = i.IDATLETA AND i.IDCOMPETICION = ?";
		try {
			c = DriverManager.getConnection(URL, user, pass);
		} catch (SQLException e) {
			System.out.println("Fallo al obtener la conexion");
		}
		try {
			PreparedStatement s = c.prepareStatement(query);
			s.setLong(1, idCompeticion);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				Atleta atleta = new Atleta();
				atleta.id = rs.getLong("idcompeticion");
				atleta.nombre = rs.getString("nombre");
				atleta.surname = rs.getString("apellidos");
				atleta.dni=rs.getString("dni");
				atleta.email= rs.getString("email");
				atletas.add(atleta);
			}
		} catch (SQLException e) {
			throw new DataException("Email o dni ya registrados");
		}
		return order(atletas);
	}

	private List<Atleta> order(List<Atleta> atletas) {
		try {
			c = DriverManager.getConnection(URL, user, pass);
			String query = "SELECT fechaInscripcion from inscripcion where idatleta = ?";
			atletas.sort(new Comparator<Atleta>() {
				@Override
				public int compare(Atleta o1, Atleta o2) {
					Date date1 = null;
					Date date2 = null;
					try {
						PreparedStatement s = c.prepareStatement(query);
						s.setLong(1, o1.id);
						ResultSet rs = s.executeQuery();
						if (rs.next()) {
							date1 = rs.getDate(1);
						}
						s = c.prepareStatement(query);
						s.setLong(1, o2.id);
						rs = s.executeQuery();
						if (rs.next()) {
							date2 = rs.getDate(1);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return date1.compareTo(date2);
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return atletas;
	}
	
}
