package logic.model;

import java.util.Date;

public class Inscripcion {

	public long id;
	public Date fecha;
	public String estado;
	public long categoriaId;
	public Date fechaModificacion;
	public double tiempo;
	public String medioPago;
	public Date fechaPago;
	public double cantidad;
	public long atletaId;
	public long competicionId;

	
	
	
	@Override
	public String toString() {
		return "Inscripcion " + id + "con fecha " + fecha + ", " + estado + ", categoria " + categoriaId
				+ ", fecha modificacion estado" + fechaModificacion + ", " + tiempo + " s, medioPago=" + medioPago
				+ ", fecha de pago " + fechaPago + ", cantidad " + cantidad + "€ , atleta " + atletaId + ", competicion "
				+ competicionId;
	}




	public long getId() {
		return id;
	}
	
}
