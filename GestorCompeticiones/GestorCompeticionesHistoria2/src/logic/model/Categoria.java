package logic.model;

public class Categoria {
	
	private long id;
	private String nombre;
	private int minEdad;
	private int maxEdad;
	private long competicionId;

	public Categoria(String name, int minAge, int maxAge) {
		this.nombre = name;
		this.minEdad = minAge;
		this.maxEdad = maxAge;
	}

	public long getId() {
		return id;
	}

	public int getMinEdad() {
		return minEdad;
	}

	public int getMaxEdad() {
		return maxEdad;
	}

	public long getCompeticionId() {
		return competicionId;
	}

	public String getNombre() {
		return nombre;
	}

}
