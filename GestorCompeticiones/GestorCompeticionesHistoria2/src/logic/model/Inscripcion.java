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
		return "Inscripcion [id=" + id + ", fecha=" + fecha + ", atletaId=" + atletaId + ", competicionId="
				+ competicionId + ", estado=" + estado + ", categoria=" + categoria + "]";
	}
	
	
}
