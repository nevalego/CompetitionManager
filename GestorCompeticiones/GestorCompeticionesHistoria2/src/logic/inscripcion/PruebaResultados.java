package logic.inscripcion;

import logic.exception.DataException;

public class PruebaResultados {
	public static void main(String[] args) throws DataException {
		VerResultados v = new VerResultados();
		System.out.println("********** CARRERA NARANCO**************");
		try {
			v.generaResultados("files/Carrera Naranco");
			System.out.println("**********SAN SILVESTRE**************");
			v.generaResultados("files/San Silvestre");
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
