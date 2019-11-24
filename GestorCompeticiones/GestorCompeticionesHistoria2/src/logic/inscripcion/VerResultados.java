package logic.inscripcion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import logic.exception.DataException;
import logic.model.Resultados;
import util.Conf;
import util.FileUtil;
import util.Jdbc;
import util.Parser;

public class VerResultados {

	private List<Resultados> resultadosAbsolutos = new ArrayList<Resultados>();
	private List<Resultados> resultadosErroneos = new ArrayList<Resultados>();
	private long competicionId;

	/**
	 * Metodo que calcula los resultados de una competicion en base a un fichero
	 * 
	 * @param competicion_id, el id de la competicion seleccionada
	 * @param fileName,       el fichero que contiene los resultados
	 */
	public void generaResultados(long competicion_id, String fileName) {
		this.competicionId = competicion_id;
		try {
			resultadosAbsolutos = Parser
					.parseResultados(FileUtil.cargarArchivo(fileName));
			isAllowed(resultadosAbsolutos);
			asignadorSexos();
			asignaNombre();
			ponerPosicion();
			resultadosAbsolutos.forEach(r -> System.out.println(r));
		} catch (DataException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		uploadResults();
		updateStatus();
	}

	private void isAllowed(List<Resultados> provisionales) {
		for (Resultados result : provisionales) {
			if (!removeWrongs(result)) {
				provisionales.remove(result);
				resultadosErroneos.add(result);
			}
		}
	}

	private boolean removeWrongs(Resultados resultado) {
		ResultSet rsGetId = null;
		PreparedStatement psGetId = null;

		try (Connection c = Jdbc.getConnection()) {

			psGetId = getConsulta(c, "SQL_GET_ID_FROM_DORSAL_COMPETICION");
			psGetId.setInt(1, resultado.getDorsal());
			psGetId.setLong(2, competicionId);
			rsGetId = psGetId.executeQuery();
			return rsGetId.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void asignadorSexos() {
		resultadosAbsolutos.forEach(r -> sexWorker(r));
	}

	private void sexWorker(Resultados r) {
		ResultSet rsGetId = null;
		PreparedStatement psGetId = null;
		ResultSet rsGetSexo = null;
		PreparedStatement psGetSexo = null;
		try (Connection c = Jdbc.getConnection()) {

			psGetId = getConsulta(c, "SQL_GET_ID_FROM_DORSAL_COMPETICION");
			psGetId.setInt(1, r.getDorsal());
			psGetId.setLong(2, competicionId);
			rsGetId = psGetId.executeQuery();
			int id = 0;
			if (rsGetId.next())
				id = rsGetId.getInt("ATLETA_ID");

			psGetSexo = getConsulta(c, "SQL_GET_SEXO_FROM_ID");
			psGetSexo.setInt(1, id);
			rsGetSexo = psGetSexo.executeQuery();
			if (rsGetSexo.next())
				r.setSexo(rsGetSexo.getString("SEXO"));
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public List<Resultados> getResultadosAbsolutos() {
		return resultadosAbsolutos;
	}

	private void asignaNombre() {
		resultadosAbsolutos.forEach(r -> asignadorNombres(r));
	}

	private void asignadorNombres(Resultados r) {
		ResultSet rsGetNombre = null;
		PreparedStatement psGetNombre = null;
		ResultSet rsGetId = null;
		PreparedStatement psGetId = null;
		try (Connection c = Jdbc.getConnection()) {
			psGetId = getConsulta(c, "SQL_GET_ID_FROM_DORSAL_COMPETICION");
			psGetId.setInt(1, r.getDorsal());
			psGetId.setLong(2, competicionId);
			rsGetId = psGetId.executeQuery();
			int id = 0;
			if (rsGetId.next())
				id = rsGetId.getInt("ATLETA_ID");

			psGetNombre = getConsulta(c, "SQL_GET_NOMBRE_FROM_ID");
			psGetNombre.setInt(1, id);
			rsGetNombre = psGetNombre.executeQuery();
			if (rsGetNombre.next())
				r.setNombreCompetidor(rsGetNombre.getString("NOMBRE") + " "
						+ rsGetNombre.getString("APELLIDOS"));
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
		PreparedStatement psGetId = null;
		ResultSet rsGetID = null;
		try (Connection c = Jdbc.getConnection()) {
			psGetId = getConsulta(c, "SQL_GET_ID_FROM_DORSAL");
			psGetId.setInt(1, r.getDorsal());
			rsGetID = psGetId.executeQuery();
			int id = 0;
			if (rsGetID.next())
				id = rsGetID.getInt("ATLETA_ID");
			else
				resultadosErroneos.add(r);
			// System.out.println("LLEGUE AQUI, ID: " + id + " dorsal?: " +
			// r.getDorsal());
			ps = getConsulta(c, "SQL_UPDATE_RESULT");
			ps.setString(1, r.getTiempo());
			ps.setInt(2, r.getPosicion());
			ps.setInt(3, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updateStatus() {
		PreparedStatement psSetStatus = null;
		try (Connection c = Jdbc.getConnection()) {
			psSetStatus = getConsulta(c, "SQL_UPDATE_STATUS_FINISHED");
			psSetStatus.setLong(1, competicionId);
			psSetStatus.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	List<Resultados> getErroneos() {
		return resultadosErroneos;
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
			ps2 = getConsulta(c, "SQL_GET_ID_FROM_NAME");
			rs2 = ps2.executeQuery();
			rs2.next();
			int cid = rs2.getInt("ID");
			ps = getConsulta(c, "SQL_GET_TIEMPOS");
			ps.setInt(1, cid);
			rs = ps.executeQuery();
			while (rs.next()) {
				ps3 = getConsulta(c, "SQL_GET_EMAIL_FROM_ID");
				ps3.setInt(1, rs.getInt("ATLETA_ID"));
				rs3 = ps3.executeQuery();
				rs.next();
				String email = rs3.getString("EMAIL");
				Resultados res = new Resultados();
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
			if (o1.getTiempo().equals("DNS") || o1.getTiempo().equals("DNE"))
				return -2;
			if (o2.getTiempo().equals("DNS") || o2.getTiempo().equals("DNE"))
				return -2;
			LocalTime l1 = LocalTime.parse(o1.getTiempo());
			LocalTime l2 = LocalTime.parse(o2.getTiempo());

			if (l1.isAfter(l2))
				return 1;
			else if (l1.isBefore(l2))
				return -1;
			return 0;
		}

	}

	private void ponerPosicion() {
		Collections.sort(resultadosAbsolutos, new ComparaResultados());
		for (int i = 0; i < resultadosAbsolutos.size(); i++) {
			resultadosAbsolutos.get(i).setPosicion(i + 1);
		}
	}

	private PreparedStatement getConsulta(Connection c, String consultaName)
			throws SQLException {
		return c.prepareStatement(Conf.getInstance().getProperty(consultaName));
	}

	public List<Resultados> generaHistorialAtleta(String email) {
		List<Resultados> historial = new ArrayList<Resultados>();
		PreparedStatement psAtletaId = null;
		PreparedStatement psNombreCompeticion = null;
		PreparedStatement psResultado = null;
		ResultSet rsAtletaId = null;
		ResultSet rsNombreCompeticion = null;
		ResultSet rsResultado = null;
		try (Connection c = Jdbc.getConnection()) {
			psAtletaId = getConsulta(c, "SQL_GET_ID_FROM_EMAIL");
			psAtletaId.setString(1, email);
			rsAtletaId = psAtletaId.executeQuery();
			long idatleta = 0;
			if (rsAtletaId.next())
				idatleta = rsAtletaId.getLong("ID");
			psResultado = getConsulta(c, "SQL_GET_HISTORIAL");
			psResultado.setLong(1, idatleta);
			rsResultado = psResultado.executeQuery();
			while (rsResultado.next()) {
				psNombreCompeticion = getConsulta(c, "SQL_GET_NAME_LISTAR");
				psNombreCompeticion.setLong(1,
						rsResultado.getLong("COMPETICION_ID"));
				rsNombreCompeticion = psNombreCompeticion.executeQuery();
				String nombrecompeticion = "";
				if (rsNombreCompeticion.next())
					nombrecompeticion = rsNombreCompeticion.getString("NOMBRE");
				Resultados r = new Resultados();
				r.setFecha(rsResultado.getDate("FECHA"));
				r.setNombreCompeticion(nombrecompeticion);
				r.setPosicion(rsResultado.getInt("POSICION"));
				r.setTiempo(rsResultado.getString("TIEMPO"));
				historial.add(r);
			}

		} catch (SQLException e) {
			System.out.println("Fallo en la generacion de historiales"); // For
																			// Debug
			e.printStackTrace();
		}

		return historial;
	}

	/* Deprecated */
	/*
	 * public void generaResultados(String fileName) { try { resultadosAbsolutos
	 * = Parser .parseResultados(FileUtil.cargarArchivo(fileName));
	 * asignaSexos(); //asignaNombre(); ponerPosicion();
	 * resultadosAbsolutos.forEach(r -> System.out.println(r)); } catch
	 * (DataException e) { System.out.println(e.getMessage());
	 * e.printStackTrace(); } uploadResults(); }
	 * 
	 * public List<Resultados> generateResultByCategory(String categoria) {
	 * List<Resultados> r = new ArrayList<Resultados>(); PreparedStatement ps =
	 * null; PreparedStatement ps2 = null; ResultSet rs = null; ResultSet rs2 =
	 * null; try (Connection c = Jdbc.getConnection()) { ps = getConsulta(c,
	 * "SQL_GET_ATLETAS_BY_COMPETITION"); ps2 = getConsulta(c,
	 * "SQL_GET_RESULT_BY_CATEGRY"); rs2 = ps2.executeQuery(); while (rs.next())
	 * { Resultados res = new Resultados();
	 * res.setNombreCompetidor(rs.getString("DORSAL"));
	 * res.setTiempo(rs.getString("TIEMPO")); r.add(res); } } catch
	 * (SQLException e) { e.printStackTrace(); } // asignarPosiciones(res);
	 * return r; }
	 * 
	 * private void asignaSexos() { resultadosAbsolutos.forEach(r ->
	 * asignador(r)); }
	 * 
	 * private void asignador(Resultados r) { ResultSet rs = null;
	 * PreparedStatement ps = null; try (Connection c = Jdbc.getConnection()) {
	 * 
	 * ps = c.prepareStatement(
	 * Conf.getInstance().getProperty("SQL_GET_SEXO_FROM_EMAIL"));
	 * ps.setString(1, r.getNombreCompetidor()); rs = ps.executeQuery(); if
	 * (rs.next()) r.setSexo(rs.getString("SEXO")); } catch (SQLException e) {
	 * e.printStackTrace();
	 * 
	 * } } private void asignaNombre() { resultadosAbsolutos.forEach(r ->
	 * asignadorNombres(r)); } private void asignadoritoNombres(Resultados r) {
	 * ResultSet rs = null; PreparedStatement ps = null; try (Connection c =
	 * Jdbc.getConnection()) {
	 * 
	 * ps = c.prepareStatement(Conf.getInstance()
	 * .getProperty("SQL_GET_NOMBRE_FROM_EMAIL")); ps.setString(1,
	 * r.getNombreCompetidor()); rs = ps.executeQuery(); if (rs.next())
	 * r.setNombreCompetidor(rs.getString("NOMBRE") + " " +
	 * rs.getString("APELLIDOS")); } catch (SQLException e) {
	 * e.printStackTrace();
	 * 
	 * } }
	 */
}
