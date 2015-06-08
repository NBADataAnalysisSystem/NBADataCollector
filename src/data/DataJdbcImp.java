package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.playerentity.PlayerBasicInfo;
import entity.teamentity.TeamBasicInfo;

public class DataJdbcImp {
	Connection connection = null;
	public DataJdbcImp(){
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:Database.db");
			connection.setAutoCommit(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		String OvertimeSql ="create table OverTime"+season+"Season(MatchID varchar(25) primary key,SerialNumber Integer,HomeScore Integer,AwayScore Integer);";
		String PlayerSql="create table Player"+season+"Season(PlayerName varchar(10) primary key,TeamAbb varchar(4),NumOfMatch Integer,NumOfStart Integer,Rebounds Integer,"
				+ "Assists Integer,PresenceTime Integer,ShootingPersentage Double,ThreePointPersentage Double,FreeThrowPersentage Double,"
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
				+ "AttackRound Double,DefendRound Double,OffensiveEfficiency Double,DefensiveEfficiency Double,ReboundEfficiency Double,"
				+ "StealEfficiency Double,AssistEfficiency Double); ";
		if(!tableExists){
			try {
				stat = connection.createStatement();
				stat.execute(MatchSql);
				stat.execute(OvertimeSql);
				stat.execute(PlayerSql);
				stat.execute(PlayerMatchSql);
				stat.execute(TeamSql);
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void storePlayer(ArrayList<PlayerBasicInfo> list){
		PreparedStatement prep =null;
		try{
			prep = connection.prepareStatement("insert into Player values("
					+ "?,?,?,?,?,?,?,?,?)");
			for(int i =0 ; i < list.size() ; i ++){
				prep.setString(1,list.get(i).getName());
				prep.setString(2,list.get(i).getPosition());
				prep.setString(3,list.get(i).getJerseyNo());
				prep.setString(4,list.get(i).getTeam());
				prep.setString(5,list.get(i).getHeight());
				prep.setString(6,list.get(i).getWeight());
				prep.setString(7,list.get(i).getBirthday());
				prep.setString(8,list.get(i).getExp());
				prep.setString(9,list.get(i).getSchool());
				prep.addBatch();
			}
			prep.executeBatch();
			prep.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void storeTeam(ArrayList<TeamBasicInfo> list){
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
			prep.close();
		}catch(Exception e){
			e.printStackTrace();
		}
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
