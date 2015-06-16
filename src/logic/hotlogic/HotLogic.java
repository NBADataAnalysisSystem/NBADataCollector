package logic.hotlogic;

import java.net.URL;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;

import data.DataJdbcImp;
import entity.hotentity.HotPlayerInfo;
import logicservice.hotlogicservice.HotLogicService;

public class HotLogic implements HotLogicService {
	
	private final static Logger log = Logger.getRootLogger();
	final String URL_PREFIX = "http://china.nba.com/wap/static/data/league/playerwhoshot";
	final String URL_POSTFIX = ".json";
	
	public void getHotInfo() {
		log.info("开始采集热点信息");
		
		WebClient webClient = new WebClient();
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);
		
		ArrayList<HotPlayerInfo> hotPlayerInfoList = new ArrayList<HotPlayerInfo>();
		
		WebRequest request;
		try {
			request = new WebRequest(new URL(URL_PREFIX + URL_POSTFIX));
			WebResponse response = webClient.loadWebResponse(request);
			String json = new String(response.getContentAsString().getBytes("iso-8859-1"),"utf-8");
			JSONObject jsonObject = new JSONObject(json);
			jsonObject = new JSONObject(jsonObject.getString("payload"));
			
			JSONArray pointPlayers = new JSONArray(jsonObject.getString("pointHotPlayers"));
			for (int i = 0; i < pointPlayers.length(); i++) {
				JSONObject pointPlayer = new JSONObject(pointPlayers.get(i).toString());
				
				HotPlayerInfo hotPlayerInfo = new HotPlayerInfo();
				
				JSONObject profileJson = new JSONObject(pointPlayer.getString("playerProfile"));
				hotPlayerInfo.setName(profileJson.getString("displayNameEn"));
				
				hotPlayerInfo.setScore(pointPlayer.getString("last5"));
				hotPlayerInfo.setScoreRate(pointPlayer.getString("differential"));
				
				hotPlayerInfoList.add(hotPlayerInfo);
			}
			
			JSONArray reboundPlayers = new JSONArray(jsonObject.getString("reboundHotPlayers"));
			for (int i = 0; i < reboundPlayers.length(); i++) {
				JSONObject reboundPlayer = new JSONObject(reboundPlayers.get(i).toString());
				
				HotPlayerInfo hotPlayerInfo = new HotPlayerInfo();
				
				JSONObject profileJson = new JSONObject(reboundPlayer.getString("playerProfile"));
				hotPlayerInfo.setName(profileJson.getString("displayNameEn"));
				
				hotPlayerInfo.setRebound(reboundPlayer.getString("last5"));
				hotPlayerInfo.setReboundRate(reboundPlayer.getString("differential"));
				
				hotPlayerInfoList.add(hotPlayerInfo);
			}
			
			JSONArray assistPlayers = new JSONArray(jsonObject.getString("assistHotPlayers"));
			for (int i = 0; i < assistPlayers.length(); i++) {
				JSONObject assistPlayer = new JSONObject(assistPlayers.get(i).toString());
				
				HotPlayerInfo hotPlayerInfo = new HotPlayerInfo();
				
				JSONObject profileJson = new JSONObject(assistPlayer.getString("playerProfile"));
				hotPlayerInfo.setName(profileJson.getString("displayNameEn"));
				
				hotPlayerInfo.setAssist(assistPlayer.getString("last5"));
				hotPlayerInfo.setAssistRate(assistPlayer.getString("differential"));
				
				hotPlayerInfoList.add(hotPlayerInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		DataJdbcImp dataJdbcImp = new DataJdbcImp();
		dataJdbcImp.storeLiftRate(hotPlayerInfoList);
		
		webClient.close();
		
		log.info("热点信息采集完成");
	}

}
