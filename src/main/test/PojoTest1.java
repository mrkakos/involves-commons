

public class PojoTest1 {
	public String name;
	private String email;
	protected int zip;

	public PojoTest1(String n, String e, int z) {
		this.name = n;
		this.email = e;
		this.zip = z;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

}
