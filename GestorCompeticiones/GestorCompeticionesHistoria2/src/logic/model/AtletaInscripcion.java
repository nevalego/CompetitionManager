package logic.model;

import java.util.Date;

public class AtletaInscripcion implements Comparable<AtletaInscripcion> {

	/*
	 * Un tipo añadido para poder crear un modelo fácil de listar en la ventana de
	 * mostrar atletas e inscripciones por categoría - Miguel
	 */

	public String dni;
	public String nombre;
	public String apellido;
	public String categoria;
	public Date fecha;
	public String estado;

	@Override
	public String toString() {
		return dni + " - " + nombre + apellido + " - " + categoria + " - " + fecha + " - " + estado;
	}

	@Override
	public int compareTo(AtletaInscripcion o) {
		int result;
		return (result = this.fecha.compareTo(o.fecha)) == 0 ?
				(this.estado.compareTo(o.estado)) : result;
	}

}
