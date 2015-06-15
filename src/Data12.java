import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;


public class Data12 {
	Connection connection = null;
	
	public Data12(){
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/cross/Documents/Tencent Files/1304138563/FileRecv/data12.db");
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
		for(int i =0;i<result.size();i++){
			System.out.println(Arrays.asList(result.get(i)));
		}
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
		for(int i =0;i<result.size();i++){
			System.out.println(Arrays.asList(result.get(i)));
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
		for(int i =0;i<result.size();i++){
			System.out.println(Arrays.asList(result.get(i)));
		}
		return result;
	}
	
	public static void main(String[] args){
		Data12 d = new Data12();
		//d.getGamePlayers();
		d.getTeam();
	}

}
