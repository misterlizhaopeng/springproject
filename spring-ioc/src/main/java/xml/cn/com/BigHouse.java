package xml.cn.com;

public class BigHouse {
	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigHouse() {
	}

	@Override
	public String toString() {
		return "BigHouse [id=" + id + ", name=" + name + "]";
	}
}
