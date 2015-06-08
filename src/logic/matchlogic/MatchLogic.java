package logic.matchlogic;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;

import entity.matchentity.MatchBasicInfo;
import entity.matchentity.OverTime;
import logicservice.matchlogicservice.MatchLogicService;

public class MatchLogic implements MatchLogicService {
	
	final String URL_PREFIX = "http://china.nba.com/wap/static/data/scores/daily_";
	final String URL_POSTFIX = ".json";
	
	public void getMatchBasicInfo() {
		WebClient webClient = new WebClient();
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);

		ArrayList<MatchBasicInfo> list = new ArrayList<MatchBasicInfo>();
		
		Calendar to = Calendar.getInstance();
		Calendar from = Calendar.getInstance();
		//2014-10-29 14-15赛季第一次常规赛
		from.set(2014, 9, 1);
		//from.set(2015, 5, 8);
		for (Calendar calendar = from; calendar.compareTo(to)<=0; calendar.add(Calendar.DAY_OF_MONTH, 1)) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = simpleDateFormat.format(calendar.getTime());
			try {
				WebRequest request = new WebRequest(new URL(URL_PREFIX + date + URL_POSTFIX));
				WebResponse response = webClient.loadWebResponse(request);
				if (response.getStatusCode() == 404) {
					continue;
				}
				String json = new String(response.getContentAsString().getBytes("iso-8859-1"),"utf-8");
				JSONObject jsonObject = new JSONObject(json);
				jsonObject = new JSONObject(jsonObject.getString("payload"));
				try {
					jsonObject = new JSONObject(jsonObject.getString("date"));
				} catch (JSONException e) {
					continue;
				}
				JSONArray jsonArray = new JSONArray(jsonObject.getString("games"));
				for (int i = 0; i < jsonArray.length(); i++) {
					jsonObject = new JSONObject(jsonArray.get(i).toString());
					
					MatchBasicInfo matchBasicInfo = new MatchBasicInfo();
					matchBasicInfo.setDate(date);
					
					JSONObject profileJson = new JSONObject(jsonObject.getString("profile"));
					matchBasicInfo.setId(profileJson.getString("gameId"));
					
					JSONObject homeTeamJson = new JSONObject(jsonObject.getString("homeTeam"));
					JSONObject homeTeamProfileJson = new JSONObject(homeTeamJson.getString("profile"));
					matchBasicInfo.setHomeTeam(homeTeamProfileJson.getString("abbr"));
					JSONObject homeTeamScoreJson = new JSONObject(homeTeamJson.getString("score"));
					matchBasicInfo.setHomeScore(homeTeamScoreJson.getString("score"));
					matchBasicInfo.setHomeScore1(homeTeamScoreJson.getString("q1Score"));
					matchBasicInfo.setHomeScore2(homeTeamScoreJson.getString("q2Score"));
					matchBasicInfo.setHomeScore3(homeTeamScoreJson.getString("q3Score"));
					matchBasicInfo.setHomeScore4(homeTeamScoreJson.getString("q4Score"));
					
					JSONObject awayTeamJson = new JSONObject(jsonObject.getString("awayTeam"));
					JSONObject awayTeamProfileJson = new JSONObject(awayTeamJson.getString("profile"));
					matchBasicInfo.setAwayTeam(awayTeamProfileJson.getString("abbr"));
					JSONObject awayTeamScoreJson = new JSONObject(awayTeamJson.getString("score"));
					matchBasicInfo.setAwayScore(awayTeamScoreJson.getString("score"));
					matchBasicInfo.setAwayScore1(awayTeamScoreJson.getString("q1Score"));
					matchBasicInfo.setAwayScore2(awayTeamScoreJson.getString("q2Score"));
					matchBasicInfo.setAwayScore3(awayTeamScoreJson.getString("q3Score"));
					matchBasicInfo.setAwayScore4(awayTeamScoreJson.getString("q4Score"));
					
					if (homeTeamScoreJson.getString("ot1Score").equals("0")
							&& awayTeamScoreJson.getString("ot1Score").equals("0")) {
						matchBasicInfo.setOverTime(false);
					} else {
						matchBasicInfo.setOverTime(true);
						ArrayList<OverTime> overTimeList = new ArrayList<OverTime>();
						for (int j = 1; j < 11; j++) {
							if (homeTeamScoreJson.getString("ot"+j+"Score").equals("0")
									&& awayTeamScoreJson.getString("ot"+j+"Score").equals("0")) {
								break;
							} else {
								OverTime overTime = new OverTime();
								overTime.setSerial(Integer.toString(j));
								overTime.setHomeScore(homeTeamScoreJson.getString("ot"+j+"Score"));
								overTime.setAwayScore(awayTeamScoreJson.getString("ot"+j+"Score"));
								overTimeList.add(overTime);
							}
						}
						matchBasicInfo.setOverTimeList(overTimeList);
					}
					
					list.add(matchBasicInfo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//TODO 在这里调用data层对应方法储存list里的内容
		
		webClient.close();
	}

}
