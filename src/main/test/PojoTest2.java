

import java.util.ArrayList;
import java.util.List;

public class PojoTest2 {

	public String name;
	public String email;
	protected int zip;
	private List addressList;

	public PojoTest2(String n, String e, int z) {
		this.name = n;
		this.email = e;
		this.zip = z;
		this.addressList = new ArrayList();
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

	public List getList() {
		return addressList;
	}

	public void setList(List list) {
		this.addressList = list;
	}

}
