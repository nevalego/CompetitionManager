package logic.model;

public class Resultados {

	private String nombreCompetidor;
	private String tiempo;
	private String sexo;
	private int posicion;
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
	@Override
	public String toString() {
		return "Resultados [nombreCompetidor=" + nombreCompetidor + ", tiempo="
				+ tiempo + ", sexo=" + sexo + ", posicion=" + posicion + "]";
	}

}
