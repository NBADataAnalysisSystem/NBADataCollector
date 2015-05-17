package entity.playerentity;

public class PlayerBasicInfo {
	
	private String path;
	private String englishName;
	private String chineseName;
	private String team;
	private String position;
	private String height;
	private String weight;
	private String exp;

	public PlayerBasicInfo() {
		path = "";
		englishName = "";
		chineseName = "";
		team = "";
		position = "";
		height = "";
		weight = "";
		exp ="";
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	@Override
	public String toString() {
		return "PlayerBasicInfo [path=" + path + ", englishName=" + englishName
				+ ", chineseName=" + chineseName + ", team=" + team
				+ ", position=" + position + ", height=" + height + ", weight="
				+ weight + ", exp=" + exp + "]";
	}
}
