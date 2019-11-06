package logic.model;

import java.util.Date;

public class Inscripcion {

	public long id;
	public Date fecha;
	public long atletaId;
	public long competicionId;
	public String estado;
	public String categoria;
	@Override
	public String toString() {
		return "Inscripcion del atleta "+atletaId +" a competicion "+ competicionId+
				", fecha "+fecha +" " + estado + ", categoria " + categoria;
	}
	public long getId() {
		return id;
	}
	
	
}
