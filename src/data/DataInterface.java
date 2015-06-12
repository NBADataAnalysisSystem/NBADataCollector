package data;

import java.util.ArrayList;

import entity.matchentity.MatchBasicInfo;
import entity.playerentity.PlayerBasicInfo;
import entity.teamentity.TeamBasicInfo;
import entity.teamentity.TeamSeasonInfo;

public interface DataInterface {
	
	public void checkSeason(String season);
	
	public void storePlayerBasicInfo(ArrayList<PlayerBasicInfo> list);
	
	public void storeTeamBasicInfo(ArrayList<TeamBasicInfo> list);
	
	public void storeTeamSeasonInfo(ArrayList<TeamSeasonInfo> list);
	
	//public void storeTeamMatchInfo(ArrayList<String[]> lsit);
	
	//public void storePlayerSeasonInfo(ArrayList<String[]> list);
	
	//public void storePlayerMatchInfo(ArrayList<String[]> list);
	
	public void storeMatchBasicInfo(ArrayList<MatchBasicInfo> list);
	
	public void close();

}
