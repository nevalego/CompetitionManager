package logic.inscripcion;

import java.util.List;

import logic.model.Resultados;

public class PruebaResultados {
public static void main(String[] args) {
	VerResultados v = new VerResultados();
	System.out.println("********** CARRERA NARANCO**************");
	v.generaResultados("files/Carrera Naranco");
	System.out.println("**********SAN SILVESTRE**************");
	v.generaResultados("files/San Silvestre");
	
	List<Resultados> r = v.generaHistorialAtleta("luison");
	r.forEach(rs -> System.out.println(rs.getTiempo() + rs.getNombreCompeticion() + rs.getFecha()));
}
}
