package logic.model;

import java.util.Date;

public class Competicion {

	public Long id;
	public Date fecha;
	public String nombre;
	public int km;
	public String tipo;
	public double cuota;
	public int plazas;
	public Date inicioInscripcion;
	public Date finInscripcion;
	@Override
	public String toString() {
		return "Competicion id=" + id + ", fecha=" + fecha + ", nombre=" + nombre + ", km=" + km + ", tipo=" + tipo
				+ ", cuota=" + cuota + ", plazas=" + plazas + ", inicioInscripcion=" + inicioInscripcion
				+ ", finInscripcion=" + finInscripcion ;
	}
	
	
}
