package logic.model;

import java.util.Date;

public class Inscripcion {

	public long id;
	public Date fecha;
	public String estado;
	public long categoriaId;
	public Date fechaModificacion;
	public String tiempo;
	public String medioPago;
	public Date fechaPago;
	public int cantidad;
	public long atletaId;
	public long competicionId;
	public int posicion;
	public int dorsal;
	public String nombreCompeticion;
	public String nombreAtleta;
	
	
	
	/*
	 * @Override public String toString() { return "Inscripcion " + id +
	 * "con fecha " + fecha + ", " + estado + ", categoria " + categoriaId +
	 * ", fecha modificacion estado" + fechaModificacion + ", " + tiempo +
	 * " s, medioPago=" + medioPago + ", fecha de pago " + fechaPago +
	 * ", cantidad " + cantidad + "€ , atleta " + atletaId + ", competicion " +
	 * competicionId; }
	 */
	
	public String toString() {
		return "Nombre Competicion: " + nombreCompeticion + " Estado: " + estado
				+ " Ultima Modificacion: " + fechaModificacion;
	}




	public long getId() {
		return id;
	}
	
}
