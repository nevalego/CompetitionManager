package logic.dto;

import java.util.Date;

public class InscripcionDto {
	public long idatleta;
	public long idcompeticion;
	public String nombreCompeticion;
	public String estado;
	public Date fechaModificacion;

	public String toStringPrintInscripciones() {
		return "Nombre Competicion: " + nombreCompeticion + " Estado: " + estado
				+ " Ultima Modificacion: " + fechaModificacion;
	}
}
