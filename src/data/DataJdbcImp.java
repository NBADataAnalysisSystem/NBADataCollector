package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.matchentity.MatchBasicInfo;
import entity.matchentity.OverTime;
import entity.playerentity.PlayerBasicInfo;
import entity.teamentity.TeamBasicInfo;
import entity.teamentity.TeamSeasonInfo;

public class DataJdbcImp  implements DataInterface{
	Connection connection = null;
	
	//常量
	String[] SEASON ={"20122013","20132014","20142015"};
	
	public DataJdbcImp(){
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:Database.db");
			connection.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void checkSeason(String season){
		Statement stat;
		boolean tableExists = true;
		try {
			stat = connection.createStatement();
			stat.execute("select * from Match"+season+"Season");
		} catch (SQLException e1) {
			tableExists = false;
		}
		
		String MatchSql = "create table Match"+season+"Season"+" "+"(MatchID varchar(25) primary key,HomeCourtTeamAbb varchar(5),"
				+ "AwayTeamAbb varchar(5),DateOfMatch varchar(10),HomeScore1 Integer,"
				+ "HomeScore2 Integer,HomeScore3 Integer,HomeScore4 Integer,AwayScore1 Integer,"
				+ "AwayScore2 Integer,AwayScore3 Integer,AwayScore4 Integer,HomeScore Integer,"
				+ "AwayScore Integer,Overtime Integer);";
		String OvertimeSql ="create table OverTime"+season+"Season(MatchID varchar(25) ,SerialNumber Integer,HomeScore Integer,AwayScore Integer,primary key(MatchID,SerialNumber));";
		String PlayerSql="create table Player"+season+"Season(PlayerName varchar(10) primary key,NumOfMatch Integer,NumOfStart Integer,Rebounds Integer,"
				+ "Assists Integer,PresenceTime Integer,Shootings Integer,Shots Integer,ShootingPersentage Double,ThreePointShootings Integer,"
				+ "ThreePointShots Integer,ThreePointPersentage Double,FreeThrowShootings Integer,FreeThrowShots Integer,FreeThrowPersentage Double,"
				+ "OffensiveRebounds Integer,DefensiveRebounds Integer,Steals Integer,BlockShots Integer,TurnOvers Integer,Fouls Integer,Score Integer,Efficiency Double,"
				+ "GmSc Double,RealShootingPersentage Double,ShootingEfficiency Double,ReboundRate Double,OffensiveReboundRate Double,DefensiveReboundRate Double,"
				+ "AssistRate Double,StealRate Double,BlockShotRate Double,TurnOverRate Double,UseRate Double );";
		String PlayerMatchSql="create table PlayerMatch"+season+"Season(MatchID varchar(25),TeamAbb varchar(5),PlayerName varchar(15)"
				+ ",Pos varchar(4),PresenceTime Integer,Shootings Integer,"
				+ "Shots Integer,ThreePointShootings Integer,ThreePointShots Integer,FreeThrowShootings Integer,"
				+ "FreeThrowShots Integer,OffensiveRebounds Integer,DefensiveRebounds Integer,Rebounds Integer,"
				+ "Assists Integer,Steals Integer,BlockShots Integer,TurnOvers Integer,Fouls Integer,Score Integer,primary key (MatchID,PlayerName));";
		String TeamSql ="create table Team"+season+"Season(TeamAbb varchar(5) primary key,"
				+ "NumOfMatch Integer,Shootings Integer,Shots Integer,ThreePointShootings Integer,"
				+ "ThreePointShots Integer,FreeThrowShootings Integer,FreeThrowShots Integer,OffensiveRebounds Integer,"
				+ "DefensiveRebounds Integer,Rebounds Integer,Assists Integer,Steals Integer,BlockShots Integer,TurnOvers Integer,Fouls Integer,"
				+ "Score Integer,ShootingPersentage Double,ThreePointPersentage Double,FreeThrowPersentage Double,WinRate Double,"
				+ "AttackRound Double,DefendRound Double,OffensiveEfficiency Double,DefensiveEfficiency Double,"
				+ "OffensiveReboundEfficiency Double,DefensiveReboundEfficiency Double,"
				+ "StealEfficiency Double,AssistEfficiency Double); ";
		String TeamMatchSql="create table TeamMatch"+season+"Season(TeamAbb varchar(5),MatchID varchar(14),Shootings integer,Shots integer,"
				+ "ThreePointShootings integer,ThreePointShots integer,FreeThrowShootings integer,FreeThrowShots integer,"
				+ "OffensiveRebounds integer,DefensiveRebounds integer,Rebounds integer,Assists integer,Steals integer,BlockShots integer,"
				+ "TurnOvers integer,Fouls integer,Score integer,primary key(TeamAbb,MatchID))";
		if(!tableExists){
			try {
				stat = connection.createStatement();
				stat.execute(MatchSql);
				stat.execute(OvertimeSql);
				stat.execute(PlayerSql);
				stat.execute(PlayerMatchSql);
				stat.execute(TeamSql);
				stat.execute(TeamMatchSql);
				connection.commit();
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void storePlayerBasicInfo(ArrayList<PlayerBasicInfo> list){
		PreparedStatement prep =null;
		try{
			prep = connection.prepareStatement("insert into Player values("
					+ "?,?,?,?,?,?,?,?,?)");
			for(int i =0 ; i < list.size() ; i ++){
				prep.setString(1,list.get(i).getName());
				prep.setString(2,list.get(i).getJerseyNo());
				prep.setString(3,list.get(i).getPosition());
				prep.setString(4,list.get(i).getTeam());
				prep.setString(5,list.get(i).getHeight());
				prep.setString(6,list.get(i).getWeight());
				prep.setString(7,list.get(i).getBirthday());
				prep.setString(8,list.get(i).getExp());
				prep.setString(9,list.get(i).getSchool());
				prep.addBatch();
			}
			prep.executeBatch();
			connection.commit();
			prep.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void storeTeamBasicInfo(ArrayList<TeamBasicInfo> list){
		PreparedStatement prep =null;
		try{
			prep = connection.prepareStatement("insert into Team values("
					+ "?,?,?,?,?,?)");
			for(int i =0 ; i < list.size() ; i ++){
				prep.setString(1,list.get(i).getFullName());
				prep.setString(2,list.get(i).getAbbreviation());
				prep.setString(3,list.get(i).getLocation());
				prep.setString(4,list.get(i).getDivision());
				prep.setString(5,list.get(i).getSection());
				prep.setString(6,list.get(i).getSetUpTime());
				prep.addBatch();
			}
			prep.executeBatch();
			connection.commit();
			prep.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void storeMatchBasicInfo(ArrayList<MatchBasicInfo> originalList){
		PreparedStatement MatchPrep = null;
		PreparedStatement OvertimePrep = null;
		ArrayList<ArrayList<MatchBasicInfo>> classifiedList =new ArrayList<ArrayList<MatchBasicInfo>>();
		for(int i = 0;i <SEASON.length ;i++){
			ArrayList<MatchBasicInfo> tempList=new ArrayList<MatchBasicInfo>();
			classifiedList.add(tempList);
		}
		for(int i=0;i<originalList.size();i++){
			for(int j=0;j<SEASON.length;j++){
				if(originalList.get(i).getSeason().equals(SEASON[j])){
					classifiedList.get(j).add(originalList.get(i));
				}
			}
		}
		for(int m=0;m<classifiedList.size();m++){
			ArrayList<MatchBasicInfo> list = classifiedList.get(m);
			checkSeason(SEASON[m]);
		try{
			MatchPrep = connection.prepareStatement("insert into Match"+SEASON[m]+"Season values("
					+ "?,?,?,?,?,?,?,?,?,?,"
					+ "?,?,?,?,?)");
			OvertimePrep = connection.prepareStatement("insert into Overtime"+SEASON[m]+"Season values("
					+ "?,?,?,?)");
			for(int i = 0;i <list.size();i ++ ){
				MatchPrep.setString(1,list.get(i).getId());
				MatchPrep.setString(2,list.get(i).getHomeTeam());
				MatchPrep.setString(3,list.get(i).getAwayTeam());
				MatchPrep.setString(4, list.get(i).getDate());
				MatchPrep.setString(5,list.get(i).getHomeScore1());
				MatchPrep.setString(6,list.get(i).getHomeScore2());
				MatchPrep.setString(7,list.get(i).getHomeScore3());
				MatchPrep.setString(8,list.get(i).getHomeScore4());
				MatchPrep.setString(9,list.get(i).getAwayScore1());
				MatchPrep.setString(10,list.get(i).getAwayScore2());
				MatchPrep.setString(11,list.get(i).getAwayScore3());
				MatchPrep.setString(12,list.get(i).getAwayScore4());
				MatchPrep.setString(13,list.get(i).getHomeScore());
				MatchPrep.setString(14,list.get(i).getAwayScore());
				if(list.get(i).isOverTime()){
					MatchPrep.setString(15,"1");
					MatchPrep.addBatch();
					ArrayList<OverTime> overtimeList=null;
					overtimeList = list.get(i).getOverTimeList();
					for(int j = 0;j < overtimeList.size();j ++){
						OvertimePrep.setString(1,list.get(i).getId());
						OvertimePrep.setString(2,overtimeList.get(j).getSerial());
						OvertimePrep.setString(3,overtimeList.get(j).getHomeScore());
						OvertimePrep.setString(4,overtimeList.get(j).getAwayScore());
						OvertimePrep.addBatch();
					}	
				}else{
					MatchPrep.setString(15,"0");
					MatchPrep.addBatch();
				}
			}
			MatchPrep.executeBatch();
			OvertimePrep.executeBatch();
			connection.commit();
		}catch (Exception e){
			e.printStackTrace();
		}
		}
	}
	
	public void storeTeamSeasonInfo(ArrayList<TeamSeasonInfo> list){
		// TODO 
		ArrayList<ArrayList<TeamSeasonInfo>> orderdList = new ArrayList<ArrayList<TeamSeasonInfo>>();
		for(int i = 0 ; i < SEASON.length; i ++ ){
		ArrayList<TeamSeasonInfo> tempList = new ArrayList<TeamSeasonInfo>();
		orderdList.add(tempList);
		}
		for(int i = 0;i < list.size();i++){
			for(int j = 0;j<SEASON.length;j++){
				if(list.get(i).getSeason().equals(SEASON[j])){
					orderdList.get(j).add(list.get(i));
				}
			}
		}
		PreparedStatement prep = null;
		try{
			for(int i = 0 ;i < SEASON.length;i++ ){
				prep = connection.prepareStatement("Insert into Team"+SEASON[i]+"Season values("
						+ "?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?)");
				for(int j =0;j<orderdList.get(i).size();j++){
					prep.setString(1,orderdList.get(i).get(j).getName());
					prep.setString(2,orderdList.get(i).get(j).getNumOfMatch());
					prep.setString(3,orderdList.get(i).get(j).getShootings());
					prep.setString(4,orderdList.get(i).get(j).getShots());
					prep.setString(5,orderdList.get(i).get(j).getThreePointShootings());
					prep.setString(6,orderdList.get(i).get(j).getThreePointShots());
					prep.setString(7,orderdList.get(i).get(j).getFreeThrowShootings());
					prep.setString(8,orderdList.get(i).get(j).getFreeThrowShots());
					prep.setString(9,orderdList.get(i).get(j).getOffensiveRebounds());
					prep.setString(10,orderdList.get(i).get(j).getDefensiveRebounds());
					prep.setString(11,orderdList.get(i).get(j).getRebounds());
					prep.setString(12,orderdList.get(i).get(j).getAssists());
					prep.setString(13,orderdList.get(i).get(j).getSteals());
					prep.setString(14,orderdList.get(i).get(j).getBlockShots());
					prep.setString(15,orderdList.get(i).get(j).getTurnOvers());
					prep.setString(16,orderdList.get(i).get(j).getFouls());
					prep.setString(17,orderdList.get(i).get(j).getScore());
					prep.setString(18,orderdList.get(i).get(j).getShootingPersentage());
					prep.setString(19,orderdList.get(i).get(j).getThreePointShootingPersentage());
					prep.setString(20,orderdList.get(i).get(j).getFreeThrowShootingPersentage());
					prep.setString(21,orderdList.get(i).get(j).getWinRate());
					prep.setString(22,orderdList.get(i).get(j).getAttackRound());
					prep.setString(23,orderdList.get(i).get(j).getDefendRound());
					prep.setString(24,orderdList.get(i).get(j).getOffensiveEfficiency());
					prep.setString(25,orderdList.get(i).get(j).getDefensiveEfficiency());
					prep.setString(26,orderdList.get(i).get(j).getOffensiveReboundEfficiency());
					prep.setString(27,orderdList.get(i).get(j).getDefensiveReboundEfficiency());
					prep.setString(28,orderdList.get(i).get(j).getStealEfficiency());
					prep.setString(29,orderdList.get(i).get(j).getAssistEfficiency());
					prep.addBatch();
				}
				prep.executeBatch();
				connection.commit();
				prep.close();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void storeTeamMatch(ArrayList<String[]> list){
		// TODO
	}
	

	

	public void close(){
		try{
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String [] args){
		DataJdbcImp data = new DataJdbcImp();
		data.checkSeason("20132014");
	}
	

}
