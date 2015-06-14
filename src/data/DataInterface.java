package data;

import java.util.ArrayList;

import entity.matchentity.MatchBasicInfo;
import entity.matchentity.PlayerMatchInfo;
import entity.matchentity.TeamMatchInfo;
import entity.playerentity.PlayerBasicInfo;
import entity.playerentity.PlayerSeasonInfo;
import entity.teamentity.TeamBasicInfo;
import entity.teamentity.TeamSeasonInfo;

public interface DataInterface {
	
	public void checkSeason(String season);
	
	public void storePlayerBasicInfo(ArrayList<PlayerBasicInfo> list);
	
	public void storeTeamBasicInfo(ArrayList<TeamBasicInfo> list);
	
	public void storeTeamSeasonInfo(ArrayList<TeamSeasonInfo> list);
	
	public void storeTeamMatchInfo(ArrayList<TeamMatchInfo> lsit);
	
	public void storePlayerSeasonInfo(ArrayList<PlayerSeasonInfo> list);
	
	public void storePlayerMatchInfo(ArrayList<PlayerMatchInfo> list);
	
	public void storeMatchBasicInfo(ArrayList<MatchBasicInfo> list);
	
	public void close();

}
