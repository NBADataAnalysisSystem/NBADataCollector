package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataJdbcImp {
	Connection connection = null;
	DataJdbcImp(){
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:Database.db");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void checkSeason(String season){
		String MatchSql = "drop table if exists Match"+season+"Season"+";create table Match"+season+"Season"+" "+"(MatchID varchar(25) primary key,HomeCourtTeamAbb varchar(5),"
				+ "AwayTeamAbb varchar(5),DateOfMatch varchar(10),HomeScore1 Integer,"
				+ "HomeScore2 Integer,HomeScore3 Integer,HomeScore4 Integer,AwayScore1 Integer,"
				+ "AwayScore2 Integer,AwayScore3 Integer,AwayScore4 Integer,HomeScore Integer,"
				+ "AwayScore Integer,Overtime Integer) ";
		Statement stat;
		try {
			stat = connection.createStatement();
			stat.execute(MatchSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String [] args){
		DataJdbcImp data = new DataJdbcImp();
		data.checkSeason("20142015");
	}
	

}
