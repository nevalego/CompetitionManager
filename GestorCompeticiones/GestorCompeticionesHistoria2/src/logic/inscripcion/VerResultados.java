package logic.inscripcion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import logic.exception.DataException;
import logic.model.Resultados;
import util.Conf;
import util.FileUtil;
import util.Jdbc;
import util.Parser;

public class VerResultados {

	private List<Resultados> resultadosAbsolutos = new ArrayList<Resultados>();
	private List<Resultados> resultadosMujer = new ArrayList<Resultados>();
	private List<Resultados> resultadosHombre = new ArrayList<Resultados>();

	public void generaResultados(String fileName) {
		try {
			resultadosAbsolutos = Parser
					.parseResultados(FileUtil.cargarArchivo(fileName));
			asignaSexos();
			asignaNombre();
			// Collections.sort(resultadosAbsolutos); -> DA ERROR????
			//ponerPosicion(); NO FUNCIONA BIEN
			resultadosAbsolutos.forEach(r -> System.out.println(r));
		} catch (DataException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		//PARA LOS FUTUROS FILTROS
		resultadosMujer = resultadosAbsolutos.stream()
				.filter(s -> s.getSexo() == "Femenino")
				.collect(Collectors.toList());
		resultadosHombre = resultadosAbsolutos.stream()
				.filter(s -> s.getSexo() == "Masculino")
				.collect(Collectors.toList());
		// uploadResults();
	}

	public List<Resultados> getResultadosAbsolutos() {
		return resultadosAbsolutos;
	}

	public List<Resultados> getResultadosMujer() {
		return resultadosMujer;
	}

	public List<Resultados> getResultadosHombre() {
		return resultadosHombre;
	}

	private void asignaSexos() {
		resultadosAbsolutos.forEach(r -> asignador(r));
	}

	private void asignador(Resultados r) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		try (Connection c = Jdbc.getConnection()) {

			ps = c.prepareStatement(
					Conf.getInstance().getProperty("SQL_GET_SEXO_FROM_EMAIL"));
			ps.setString(1, r.getNombreCompetidor());
			rs = ps.executeQuery();
			rs.next();
			r.setSexo(rs.getString("SEXO"));
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
	
	private void asignaNombre() {
		resultadosAbsolutos.forEach(r -> asignadorNombres(r));
	}
	
	private void asignadorNombres(Resultados r) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		try (Connection c = Jdbc.getConnection()) {

			ps = c.prepareStatement(
					Conf.getInstance().getProperty("SQL_GET_NOMBRE_FROM_EMAIL"));
			ps.setString(1, r.getNombreCompetidor());
			rs = ps.executeQuery();
			rs.next();
			r.setNombreCompetidor(rs.getString("NOMBRE") + " " + rs.getString("APELLIDOS"));
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
	

	/**
	 * Este metodo envia los resultados a la base de datos
	 */
	public void uploadResults() {
		resultadosAbsolutos.forEach(r -> upload(r));
	}

	private void upload(Resultados r) {
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		try (Connection c = Jdbc.getConnection()) {
			ps2 = c.prepareStatement(
					Conf.getInstance().getProperty("SQL_GET_ID_FROM_EMAIL"));
			ps2.setString(1, r.getNombreCompetidor());
			rs2 = ps2.executeQuery();
			rs2.next();
			int id = rs2.getInt("ID");
			ps = c.prepareStatement(
					Conf.getInstance().getProperty("SQL_UPDATE_RESULT"));
			ps.setString(1, r.getTiempo());
			ps.setInt(2,id);
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Resultados> obtenerResultado(String nombreCompeticion) {
		List<Resultados> r = new ArrayList<Resultados>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		PreparedStatement ps3 = null;
		ResultSet rs3 = null;
		try (Connection c = Jdbc.getConnection()) {
			ps2 = c.prepareStatement(
					Conf.getInstance().getProperty("SQL_GET_ID_FROM_NAME"));
			rs2 = ps2.executeQuery();
			rs2.next();
			int cid = rs2.getInt("ID");
			ps = c.prepareStatement(
					Conf.getInstance().getProperty("SQL_GET_TIEMPOS"));
			ps.setInt(1,cid);
			rs = ps.executeQuery();
			while(rs.next()) {
				ps3 = c.prepareStatement(
						Conf.getInstance().getProperty("SQL_GET_EMAIL_FROM_ID"));
				ps3.setInt(1,rs.getInt("ATLETA_ID"));
				rs3 = ps3.executeQuery();
				rs.next();
				String email = rs3.getString("EMAIL");
				Resultados res =  new Resultados();
				res.setNombreCompetidor(email);
				res.setTiempo(rs.getString("TIEMPO"));
				res.setPosicion(rs.getInt("POSICION"));
				r.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("FALLO EN LA OBTENCION DE RESULTADOS");
		}
		return r;
	}

	private class ComparaResultados implements Comparator<Resultados> {

		@Override
		public int compare(Resultados o1, Resultados o2) {
			String[] t1 = o1.getTiempo().split(":");
			String[] t2 = o2.getTiempo().split(":");

			return (Integer.parseInt(t1[0]) * 3600
					+ Integer.parseInt(t1[1]) * 60 + Integer.parseInt(t1[2]))
					- (Integer.parseInt(t2[0]) * 3600
							+ Integer.parseInt(t2[1]) * 60
							+ Integer.parseInt(t2[2]));
		}

	}

	private void ponerPosicion() {
		List<Resultados> prov = resultadosAbsolutos;
		for(int i =0;i<prov.size();i++) {
			Resultados rmin = Collections.min(prov,new ComparaResultados());
			rmin.setPosicion(i+1);
			prov.remove(rmin);
		}
	}
}
