package logic.inscripcion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			resultadosAbsolutos = Parser.parseResultados(FileUtil.cargarArchivo(fileName));
		}catch(DataException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		resultadosMujer =  (List<Resultados>) resultadosAbsolutos.stream().filter(s -> s.getSexo() == "Mujer");
		resultadosHombre = (List<Resultados>) resultadosAbsolutos.stream().filter(s -> s.getSexo() == "Hombre");
		uploadResults();
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
	
	/**
	 * Este metodo envia los resultados a la base de datos
	 */
	public void uploadResults() {
		resultadosAbsolutos.forEach(r -> upload(r));
	}
	
	private void upload(Resultados r)  {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try(Connection c=  Jdbc.getConnection()){
			ps = c.prepareStatement(Conf.getInstance().getProperty("SQL_UPDATE_RESULT"));
			ps.setString(1,r.getTiempo());
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public List<Resultados> obtenerResultado(String nombreCompeticion){
		List<Resultados> r = new ArrayList<Resultados>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try(Connection c=  Jdbc.getConnection()){
			ps = c.prepareStatement(Conf.getInstance().getProperty("SQL_UPDATE_RESULT"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
}
