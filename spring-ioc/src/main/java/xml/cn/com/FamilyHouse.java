package xml.cn.com;

public class FamilyHouse {

	private String id;
	private String name;
	private House house;
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
	public House getHouse() {
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}
	@Override
	public String toString() {
		return "FamilyHouse [id=" + id + ", name=" + name + ", house=" + house + "]";
	}
	
}
