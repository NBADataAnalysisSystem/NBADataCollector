package entity.teamentity;

import java.text.DecimalFormat;

public class TeamSeasonInfo {

	private String name;
	private String season;
	private String numOfMatch;
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
	private String shootingPersentage;
	private String threePointShootingPersentage;
	private String freeThrowShootingPersentage;	
	private String opponentScore;
	private String opponentOffensiveRebounds;
	private String opponentDefensiveRebounds;
	private String winRate;
	
	private String attackRound;
	private String defendRound;
	private String offensiveEfficiency;
	private String defensiveEfficiency;
	private String offensiveReboundEfficiency;
	private String defensiveReboundEfficiency;
	private String stealEfficiency;
	private String assistEfficiency;
	
	public void setAttackRound(String attackRound) {
		this.attackRound = attackRound;
	}

	public void setDefendRound(String defendRound) {
		this.defendRound = defendRound;
	}

	public void setOffensiveEfficiency(String offensiveEfficiency) {
		this.offensiveEfficiency = offensiveEfficiency;
	}

	public void setDefensiveEfficiency(String defensiveEfficiency) {
		this.defensiveEfficiency = defensiveEfficiency;
	}

	public void setOffensiveReboundEfficiency(String offensiveReboundEfficiency) {
		this.offensiveReboundEfficiency = offensiveReboundEfficiency;
	}

	public void setDefensiveReboundEfficiency(String defensiveReboundEfficiency) {
		this.defensiveReboundEfficiency = defensiveReboundEfficiency;
	}

	public void setStealEfficiency(String stealEfficiency) {
		this.stealEfficiency = stealEfficiency;
	}

	public void setAssistEfficiency(String assistEfficiency) {
		this.assistEfficiency = assistEfficiency;
	}

	public TeamSeasonInfo() {
		this.name = "";
		this.season = "";
		this.numOfMatch = "";
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
		this.shootingPersentage = "";
		this.threePointShootingPersentage = "";
		this.freeThrowShootingPersentage = "";
		this.opponentScore = "";
		this.opponentOffensiveRebounds = "";
		this.opponentDefensiveRebounds = "";
		this.winRate = "";
		this.attackRound="";
		this.defendRound="";
		this.offensiveEfficiency="";
		this.defensiveEfficiency="";
		this.offensiveReboundEfficiency="";
		this.defensiveReboundEfficiency="";
		this.assistEfficiency="";
		this.stealEfficiency="";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getNumOfMatch() {
		return numOfMatch;
	}

	public void setNumOfMatch(String numOfMatch) {
		this.numOfMatch = numOfMatch;
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

	public String getShootingPersentage() {
		return shootingPersentage;
	}

	public void setShootingPersentage(String shootingPersentage) {
		this.shootingPersentage = shootingPersentage;
	}

	public String getThreePointShootingPersentage() {
		return threePointShootingPersentage;
	}

	public void setThreePointShootingPersentage(String threePointShootingPersentage) {
		this.threePointShootingPersentage = threePointShootingPersentage;
	}

	public String getFreeThrowShootingPersentage() {
		return freeThrowShootingPersentage;
	}

	public void setFreeThrowShootingPersentage(String freeThrowShootingPersentage) {
		this.freeThrowShootingPersentage = freeThrowShootingPersentage;
	}

	public String getOpponentScore() {
		return opponentScore;
	}

	public void setOpponentScore(String opponentScore) {
		this.opponentScore = opponentScore;
	}

	public String getOpponentOffensiveRebounds() {
		return opponentOffensiveRebounds;
	}

	public void setOpponentOffensiveRebounds(String opponentOffensiveRebounds) {
		this.opponentOffensiveRebounds = opponentOffensiveRebounds;
	}

	public String getOpponentDefensiveRebounds() {
		return opponentDefensiveRebounds;
	}

	public void setOpponentDefensiveRebounds(String opponentDefensiveRebounds) {
		this.opponentDefensiveRebounds = opponentDefensiveRebounds;
	}

	public  String  getWinRate() {
		return winRate;
	}
	
	public String getAttackRound() {
		if(attackRound.equals("")){
			DecimalFormat df = new DecimalFormat("#.00");
			return attackRound=df.format(Integer.parseInt(shots)+0.4*Integer.parseInt(freeThrowShots)-1.07
					*(Double.parseDouble(offensiveRebounds)/(Integer.parseInt(offensiveRebounds)+
							Integer.parseInt(opponentDefensiveRebounds))*(Integer.parseInt(shots)-Integer.parseInt(shootings)))
							+1.07*Integer.parseInt(turnOvers));
		}else return attackRound;
		
	}
	
	public String getDefendRound() {
		if(defendRound.equals("DR")){
			return "0";
		}else{
			DecimalFormat df = new DecimalFormat("#.00");
			return defendRound=df.format(Integer.parseInt(shots)+0.4*Integer.parseInt(freeThrowShots)-1.07
					*(Double.parseDouble(defensiveRebounds)/(Integer.parseInt(defensiveRebounds)+
							Integer.parseInt(opponentOffensiveRebounds))*(Integer.parseInt(shots)-Integer.parseInt(shootings)))
							+1.07*Integer.parseInt(turnOvers));
		}
		
	}
	
	public String getOffensiveEfficiency() {
		if(offensiveEfficiency.equals("")){
			DecimalFormat df = new DecimalFormat("#.00");
			return offensiveEfficiency= df.format(100*Integer.parseInt(score)/Double.parseDouble(getAttackRound()));
		}else return offensiveEfficiency;
		
	}
	
	public String getDefensiveEfficiency() {
		if(defensiveEfficiency.equals("")){
			DecimalFormat df = new DecimalFormat("#.00");
			return  defensiveEfficiency=df.format(100*Integer.parseInt(opponentScore)/Double.parseDouble(getDefendRound()));
		}else return defensiveEfficiency;
		
	}
	
	public String getOffensiveReboundEfficiency() {
		if(offensiveReboundEfficiency.equals("")){
			DecimalFormat df = new DecimalFormat("#.00");
			return offensiveReboundEfficiency=df.format(Double.parseDouble(offensiveRebounds)/((Double.parseDouble(offensiveRebounds)+
					Double.parseDouble(opponentDefensiveRebounds))));
		}else return offensiveReboundEfficiency;
		
	}
	
	public String getDefensiveReboundEfficiency() {
		if(defensiveReboundEfficiency.equals("")){
			DecimalFormat df = new DecimalFormat("#.00");
			return defensiveReboundEfficiency=df.format(Double.parseDouble(defensiveRebounds)/((Double.parseDouble(defensiveRebounds)+
					Double.parseDouble(opponentOffensiveRebounds))));
		}else return defensiveReboundEfficiency;
		
	}
	
	public String getStealEfficiency() {
		if(stealEfficiency.equals("")){
			DecimalFormat df = new DecimalFormat("#.00");
			return stealEfficiency=df.format(100*Double.parseDouble(steals)/Double.parseDouble(getDefendRound()));
		}else return stealEfficiency;
		
	}
	
	public String getAssistEfficiency() {
		if(assistEfficiency.equals("")){
			DecimalFormat df = new DecimalFormat("#.00");
			return assistEfficiency=df.format(100*Double.parseDouble(assists)/Double.parseDouble(getAttackRound()));
		}else return assistEfficiency;
		
	}

	public void setWinRate(String winRate) {
		this.winRate = winRate;
	}

	@Override
	public String toString() {
		return "TeamSeasonInfo [name=" + name + ", season=" + season
				+ ", numOfMatch=" + numOfMatch + ", shootings=" + shootings
				+ ", shots=" + shots + ", threePointShootings="
				+ threePointShootings + ", threePointShots=" + threePointShots
				+ ", freeThrowShootings=" + freeThrowShootings
				+ ", freeThrowShots=" + freeThrowShots + ", offensiveRebounds="
				+ offensiveRebounds + ", defensiveRebounds="
				+ defensiveRebounds + ", rebounds=" + rebounds + ", assists="
				+ assists + ", steals=" + steals + ", blockShots=" + blockShots
				+ ", turnOvers=" + turnOvers + ", fouls=" + fouls + ", score="
				+ score + ", shootingPersentage=" + shootingPersentage
				+ ", threePointShootingPersentage="
				+ threePointShootingPersentage
				+ ", freeThrowShootingPersentage="
				+ freeThrowShootingPersentage + ", opponentScore="
				+ opponentScore + ", opponentOffensiveRebounds="
				+ opponentOffensiveRebounds + ", opponentDefensiveRebounds="
				+ opponentDefensiveRebounds + ", winRate=" + winRate + "]";
	}
	
}
