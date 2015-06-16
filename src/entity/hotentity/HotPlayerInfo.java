package entity.hotentity;

public class HotPlayerInfo {

	private String name;
	private String score;
	private String scoreRate;
	private String rebound;
	private String reboundRate;
	private String assist;
	private String assistRate;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getScore() {
		return score;
	}
	
	public void setScore(String score) {
		this.score = score;
	}
	
	public String getScoreRate() {
		return scoreRate;
	}
	
	public void setScoreRate(String scoreRate) {
		this.scoreRate = scoreRate;
	}
	
	public String getRebound() {
		return rebound;
	}
	
	public void setRebound(String rebound) {
		this.rebound = rebound;
	}
	
	public String getReboundRate() {
		return reboundRate;
	}
	
	public void setReboundRate(String reboundRate) {
		this.reboundRate = reboundRate;
	}
	
	public String getAssist() {
		return assist;
	}
	
	public void setAssist(String assist) {
		this.assist = assist;
	}
	
	public String getAssistRate() {
		return assistRate;
	}
	
	public void setAssistRate(String assistRate) {
		this.assistRate = assistRate;
	}

	@Override
	public String toString() {
		return "HotPlayerInfo [name=" + name + ", score=" + score
				+ ", scoreRate=" + scoreRate + ", rebound=" + rebound
				+ ", reboundRate=" + reboundRate + ", assist=" + assist
				+ ", assistRate=" + assistRate + "]";
	}
	
}
