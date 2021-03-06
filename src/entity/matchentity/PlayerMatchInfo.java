package entity.matchentity;

public class PlayerMatchInfo {

	private String season;
	private String matchId;
	private String team;
	private String name;
	private String position;
	private String presenceTime;
	private String shootings;
	private String shots;
	private String threePointShootings;
	private String threePointShots;
	private String freeThrowShootings;
	private String freeThrowShots;
	private String offensiveRebounds;
	private String defensiveRebounds;
	private String rebounds;
	private String assists;
	private String steals;
	private String blockShots;
	private String turnOvers;
	private String fouls;
	private String score;
	
	public PlayerMatchInfo() {
		this.season = "";
		this.matchId = "";
		this.team = "";
		this.name = "";
		this.position = "";
		this.presenceTime = "";
		this.shootings = "";
		this.shots = "";
		this.threePointShootings = "";
		this.threePointShots = "";
		this.freeThrowShootings = "";
		this.freeThrowShots = "";
		this.offensiveRebounds = "";
		this.defensiveRebounds = "";
		this.rebounds = "";
		this.assists = "";
		this.steals = "";
		this.blockShots = "";
		this.turnOvers = "";
		this.fouls = "";
		this.score = "";
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPresenceTime() {
		return presenceTime;
	}

	public void setPresenceTime(String presenceTime) {
		this.presenceTime = presenceTime;
	}

	public String getShootings() {
		return shootings;
	}

	public void setShootings(String shootings) {
		this.shootings = shootings;
	}

	public String getShots() {
		return shots;
	}

	public void setShots(String shots) {
		this.shots = shots;
	}

	public String getThreePointShootings() {
		return threePointShootings;
	}

	public void setThreePointShootings(String threePointShootings) {
		this.threePointShootings = threePointShootings;
	}

	public String getThreePointShots() {
		return threePointShots;
	}

	public void setThreePointShots(String threePointShots) {
		this.threePointShots = threePointShots;
	}

	public String getFreeThrowShootings() {
		return freeThrowShootings;
	}

	public void setFreeThrowShootings(String freeThrowShootings) {
		this.freeThrowShootings = freeThrowShootings;
	}

	public String getFreeThrowShots() {
		return freeThrowShots;
	}

	public void setFreeThrowShots(String freeThrowShots) {
		this.freeThrowShots = freeThrowShots;
	}

	public String getOffensiveRebounds() {
		return offensiveRebounds;
	}

	public void setOffensiveRebounds(String offensiveRebounds) {
		this.offensiveRebounds = offensiveRebounds;
	}

	public String getDefensiveRebounds() {
		return defensiveRebounds;
	}

	public void setDefensiveRebounds(String defensiveRebounds) {
		this.defensiveRebounds = defensiveRebounds;
	}

	public String getRebounds() {
		return rebounds;
	}

	public void setRebounds(String rebounds) {
		this.rebounds = rebounds;
	}

	public String getAssists() {
		return assists;
	}

	public void setAssists(String assists) {
		this.assists = assists;
	}

	public String getSteals() {
		return steals;
	}

	public void setSteals(String steals) {
		this.steals = steals;
	}

	public String getBlockShots() {
		return blockShots;
	}

	public void setBlockShots(String blockShots) {
		this.blockShots = blockShots;
	}

	public String getTurnOvers() {
		return turnOvers;
	}

	public void setTurnOvers(String turnOvers) {
		this.turnOvers = turnOvers;
	}

	public String getFouls() {
		return fouls;
	}

	public void setFouls(String fouls) {
		this.fouls = fouls;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "PlayerMatchInfo [season=" + season + ", matchId=" + matchId
				+ ", team=" + team + ", name=" + name + ", position="
				+ position + ", presenceTime=" + presenceTime + ", shootings="
				+ shootings + ", shots=" + shots + ", threePointShootings="
				+ threePointShootings + ", threePointShots=" + threePointShots
				+ ", freeThrowShootings=" + freeThrowShootings
				+ ", freeThrowShots=" + freeThrowShots + ", offensiveRebounds="
				+ offensiveRebounds + ", defensiveRebounds="
				+ defensiveRebounds + ", rebounds=" + rebounds + ", assists="
				+ assists + ", steals=" + steals + ", blockShots=" + blockShots
				+ ", turnOvers=" + turnOvers + ", fouls=" + fouls + ", score="
				+ score + "]";
	}
	
}
