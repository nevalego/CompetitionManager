package logic.model;

import java.util.Date;

public class Competicion {

	public Long id;
	public String nombre;
	public String tipo;
	public int km;
	public Date fecha;
	public int plazas;
	@Override
	public String toString() {
		return nombre +" " + fecha + " "+ km + " km , de tipo " + tipo
				+ ", " + plazas + " plazas, periodo inscripcion ";
	}

	public long getId() {
		return id;
	}
	
	
}
