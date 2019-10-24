package logic.model;

import java.util.Date;

public class Competicion {

	public Long id;
	public String nombre;
	public String tipo;
	public int km;
	public double cuota;
	public Date fecha;
	public int plazas;
	public Date inicioInscripcion;
	public Date finInscripcion;
	
	@Override
	public String toString() {
		return "Competicion "+nombre +" " + id + " el dia " + fecha + " "+ km + "km , de tipo " + tipo
				+ ", cuota " + cuota + ", " + plazas + " plazas, periodo inscripcion " + inicioInscripcion
				+ " - " + finInscripcion ;
	}
	
	
}
