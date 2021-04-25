package lernen_Lambda;

public class Zutat {

	public String name;
	
	public Zutat() {
	};

	public Zutat(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean zubereiten(int anz, ZutatVerarbeiten z) {
		return z.schneiden(anz);
	}

}
