package logic.model;

public class Categoria {
	
	private long id;
	private String name;
	private int minAge;
	private int maxAge;

	public Categoria(String name, int minAge, int maxAge) {
		this.name = name;
		this.minAge = minAge;
		this.maxAge = maxAge;
	}

	public int getMinAge() {
		return minAge;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public String getName() {
		return name;
	}

}
