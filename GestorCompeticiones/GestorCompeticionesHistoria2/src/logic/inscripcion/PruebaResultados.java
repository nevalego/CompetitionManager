package logic.inscripcion;

import java.util.List;

import logic.model.Resultados;

public class PruebaResultados {
public static void main(String[] args) {
	VerResultados v = new VerResultados();
	System.out.println("********** CARRERA NARANCO**************");
	System.out.println("BIEN: ");
		v.generaResultados(3,"files/Carrera Naranco");
	System.out.println("MAL: ");
	for(Resultados r: v.getErroneos()) {
		System.out.println(r);
	}
	System.out.println("**********SAN SILVESTRE**************");
	//v.generaResultados("files/San Silvestre");
	
	List<Resultados> r = v.generaHistorialAtleta("luison");
	r.forEach(rs -> System.out.println(rs.getTiempo() + rs.getNombreCompeticion() + rs.getFecha()));
	List<Resultados> r2 = v.generaHistorialAtleta("mara");
	r2.forEach(rs -> System.out.println(rs.getPosicion() + rs.getTiempo() + rs.getNombreCompeticion() + rs.getFecha()));
}
}
