package logic.inscripcion;

import logic.exception.DataException;
import logic.model.Resultados;

public class PruebaResultados {
public static void main(String[] args) throws DataException {
	VerResultados v = new VerResultados();
	System.out.println("********** CARRERA Google**************");
	System.out.println("BIEN: ");
		v.generaResultados(5,"files/Carrera Amazon");
	System.out.println("MAL: ");
	for(Resultados r: v.getErroneos()) {
		System.out.println(r);
	}
	
}
}
