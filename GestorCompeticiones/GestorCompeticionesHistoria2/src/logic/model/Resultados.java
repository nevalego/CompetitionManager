package logic.model;

import java.util.Date;

public class Resultados {

	private String nombreCompetidor;
	private String tiempo;
	private String sexo;
	private int posicion;
	private Date fecha;
	private String nombreCompeticion;
	private int dorsal;
	private String motivo;
	public String getNombreCompetidor() {
		return nombreCompetidor;
	}
	public void setNombreCompetidor(String nombreCompetidor) {
		this.nombreCompetidor = nombreCompetidor;
	}
	public String getTiempo() {
		return tiempo;
	}
	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getNombreCompeticion() {
		return nombreCompeticion;
	}
	public void setNombreCompeticion(String nombreCompeticion) {
		this.nombreCompeticion = nombreCompeticion;
	}
	
	public int getDorsal() {
		return dorsal;
	}
	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}
	
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	@Override
	public String toString() {
		return "Resultados [nombreCompetidor=" + nombreCompetidor + ", tiempo="
				+ tiempo + ", sexo=" + sexo + ", posicion=" + posicion + "]";
	}


}
