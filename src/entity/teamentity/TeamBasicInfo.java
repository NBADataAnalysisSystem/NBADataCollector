package entity.teamentity;

public class TeamBasicInfo {

	private String path;
	private String name;
	
	public TeamBasicInfo() {
		path = "";
		name = "";
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "TeamBasicInfo [path=" + path + ", name=" + name + "]";
	}
	
}
