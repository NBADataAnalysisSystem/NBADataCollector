package data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import entity.matchentity.PlayerMatchInfo;
import entity.playerentity.PlayerSeasonInfo;
import entity.teamentity.TeamSeasonInfo;


public class Data12 {
	Connection connection = null;
	
	public Data12(){
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:data12.db");
			connection.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String[]>  getGamePlayers(){
		ArrayList<String[]> result = new ArrayList<String[]>();
		Statement stat = null;
		ResultSet rs = null;
		try{
			stat = connection.createStatement();
			rs = stat.executeQuery("select * from gameplayers");
			while(rs.next()){
				String[] tempList = new String[21];
				for(int i =0;i<21;i++){
					tempList[i] = rs.getString(i+1);
				}
				result.add(tempList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		ArrayList<PlayerMatchInfo> playerMatchInfoList = new ArrayList<PlayerMatchInfo>();
		for(String[] strings : result){
			PlayerMatchInfo playerMatchInfo = new PlayerMatchInfo();
			
			playerMatchInfo.setSeason("20122013");
			playerMatchInfo.setMatchId(strings[1]);
			
			if (strings[1].endsWith("1")) {
				playerMatchInfo.setTeam(strings[1].split("_")[4]);
			} else {
				playerMatchInfo.setTeam(strings[1].split("_")[5].substring(0, 3));
			}
			
			playerMatchInfo.setName(strings[3]);
			
			String position = strings[2];
			position = position.replace("C", "中锋");
			position = position.replace("F", "前锋");
			position = position.replace("G", "后卫");
			position = position.replace("No Position", "");
			playerMatchInfo.setPosition(position);
			
			playerMatchInfo.setPresenceTime(strings[4]);
			playerMatchInfo.setShootings(strings[5]);
			playerMatchInfo.setShots(strings[6]);
			playerMatchInfo.setThreePointShootings(strings[7]);
			playerMatchInfo.setThreePointShots(strings[8]);
			playerMatchInfo.setFreeThrowShootings(strings[9]);
			playerMatchInfo.setFreeThrowShots(strings[10]);
			playerMatchInfo.setOffensiveRebounds(strings[11]);
			playerMatchInfo.setDefensiveRebounds(strings[12]);
			playerMatchInfo.setRebounds(strings[13]);
			playerMatchInfo.setAssists(strings[14]);
			playerMatchInfo.setSteals(strings[15]);
			playerMatchInfo.setBlockShots(strings[16]);
			playerMatchInfo.setTurnOvers(strings[17]);
			playerMatchInfo.setFouls(strings[18]);
			playerMatchInfo.setScore(strings[19]);
			//System.out.println(Arrays.asList(strings));
			//System.out.println(playerMatchInfo);
			
			playerMatchInfoList.add(playerMatchInfo);
		}
		DataJdbcImp dataJdbcImp = new DataJdbcImp();
		dataJdbcImp.storePlayerMatchInfo(playerMatchInfoList);
		return result;
	}
	
	public ArrayList<String[]> getPlayers(){
		ArrayList<String[]> result = new ArrayList<String[]>();
		Statement stat = null;
		ResultSet rs = null;
		try{
			stat = connection.createStatement();
			rs = stat.executeQuery("select * from players");
			while(rs.next()){
				String[] tempList = new String[73];
				for(int i =0;i<73;i++){
					tempList[i] = rs.getString(i+1);
				}
				result.add(tempList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		ArrayList<PlayerSeasonInfo> playerSeasonInfoList = new ArrayList<PlayerSeasonInfo>();
		for(String[] strings : result){
			PlayerSeasonInfo playerSeasonInfo = new PlayerSeasonInfo();
			
			playerSeasonInfo.setSeason("20122013");
			playerSeasonInfo.setName(strings[3]);
			playerSeasonInfo.setNumOfMatch(strings[12]);
			playerSeasonInfo.setNumOfStart(strings[13]);
			playerSeasonInfo.setPresenceTime(strings[20]);
			playerSeasonInfo.setShootingPersentage(strings[22]);
			playerSeasonInfo.setThreePointShootingPersentage(strings[35]);
			playerSeasonInfo.setFreeThrowShootingPersentage(strings[36]);
			playerSeasonInfo.setRebounds(strings[14]);
			playerSeasonInfo.setAssists(strings[15]);
			playerSeasonInfo.setSteals(strings[16]);
			playerSeasonInfo.setBlockshots(strings[17]);
			playerSeasonInfo.setTurnOvers(strings[18]);
			playerSeasonInfo.setFouls(strings[19]);
			playerSeasonInfo.setScore(strings[21]);
			playerSeasonInfo.setEffiency(strings[33]);
			
			//System.out.println(Arrays.asList(strings));
			//System.out.println(playerSeasonInfo);
			
			playerSeasonInfoList.add(playerSeasonInfo);
		}
		return result;
	}
	
	public ArrayList<String[]> getTeam(){
		ArrayList<String[]> result = new ArrayList<String[]>();
		Statement stat = null;
		ResultSet rs = null;
		try{
			stat = connection.createStatement();
			rs = stat.executeQuery("select * from team");
			while(rs.next()){
				String[] tempList = new String[52];
				for(int i =0;i<52;i++){
					tempList[i] = rs.getString(i+1);
				}
				result.add(tempList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		ArrayList<TeamSeasonInfo> teamSeasonInfoList = new ArrayList<TeamSeasonInfo>();
		for(String[] strings : result){
			TeamSeasonInfo teamSeasonInfo = new TeamSeasonInfo();
			teamSeasonInfo.setSeason("20122013");
			
			teamSeasonInfo.setName(strings[2]);
			teamSeasonInfo.setNumOfMatch(strings[8]);
			teamSeasonInfo.setShootings(strings[9]);
			teamSeasonInfo.setShots(strings[10]);
			teamSeasonInfo.setThreePointShootings(strings[11]);
			teamSeasonInfo.setThreePointShots(strings[12]);
			teamSeasonInfo.setFreeThrowShootings(strings[13]);
			teamSeasonInfo.setFreeThrowShots(strings[14]);
			teamSeasonInfo.setOffensiveRebounds(strings[16]);
			teamSeasonInfo.setDefensiveRebounds(strings[17]);
			teamSeasonInfo.setRebounds(strings[15]);
			teamSeasonInfo.setAssists(strings[18]);
			teamSeasonInfo.setSteals(strings[19]);
			teamSeasonInfo.setBlockShots(strings[20]);
			teamSeasonInfo.setTurnOvers(strings[21]);
			teamSeasonInfo.setFouls(strings[22]);
			teamSeasonInfo.setScore(strings[23]);
			teamSeasonInfo.setWinRate(strings[28]);
			teamSeasonInfo.setAttackRound(strings[24]);
			teamSeasonInfo.setDefendRound("DR");
			teamSeasonInfo.setOffensiveEfficiency(strings[29]);
			teamSeasonInfo.setDefensiveEfficiency(strings[30]);
			teamSeasonInfo.setOffensiveReboundEfficiency(strings[31]);
			teamSeasonInfo.setDefensiveReboundEfficiency(strings[32]);
			teamSeasonInfo.setStealEfficiency(strings[33]);
			teamSeasonInfo.setAssistEfficiency(strings[34]);
			
			//System.out.println(Arrays.asList(strings));
			//System.out.println(teamSeasonInfo);
			
			teamSeasonInfoList.add(teamSeasonInfo);
		}
		DataJdbcImp dataJdbcImp = new DataJdbcImp();
		dataJdbcImp.storeTeamSeasonInfo(teamSeasonInfoList);
		return result;
	}
	
	public static void main(String[] args){
		Data12 d = new Data12();
		d.getTeam();
		//d.getGamePlayers();
		//d.getPlayers();
	}

}
