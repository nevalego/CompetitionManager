package logic.inscripcion;

public class PruebaResultados {
public static void main(String[] args) {
	VerResultados v = new VerResultados();
	System.out.println("********** CARRERA NARANCO**************");
	v.generaResultados("files/Carrera Naranco");
	System.out.println("**********SAN SILVESTRE**************");
	v.generaResultados("files/San Silvestre");
}
}
