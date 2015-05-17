package logic.playerlogic;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import entity.playerentity.PlayerBasicInfo;
import logicservice.playerlogicservice.PlayerLogicService;

public class PlayerLogic implements PlayerLogicService {

	final String INDEX_URL = "http://china.nba.com";
	
	public void getPlayerBasicInfo() {
		WebClient webClient = new WebClient();
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);
		
		ArrayList<PlayerBasicInfo> list = new ArrayList<PlayerBasicInfo>();
		
		try {
			HtmlPage indexPage = webClient.getPage(INDEX_URL);
			HtmlAnchor playerBtn = indexPage.getAnchorByText("球员");
			HtmlPage playerPage = playerBtn.click();
			Document document = Jsoup.parse(playerPage.asXml(), "UTF-8");
			Elements playerList = document.select("tr");
			for (int i = 1; i < playerList.size()-1; i++) {
				Element player = playerList.get(i);
				if (!player.text().contains("team.")) {
					Elements playerInfoList = player.select("td");
					PlayerBasicInfo playerBasicInfo = new PlayerBasicInfo();
					playerBasicInfo.setPath(INDEX_URL + playerInfoList.get(0).select("a").attr("href"));
					String name = playerInfoList.get(0).text().replace(" ", "");
					playerBasicInfo.setEnglishName(name.split(" ")[0]);
					playerBasicInfo.setChineseName(name.split(" ")[1]);
					playerBasicInfo.setTeam(playerInfoList.get(1).text());
					playerBasicInfo.setPosition(playerInfoList.get(2).text());
					playerBasicInfo.setHeight(playerInfoList.get(3).text());
					playerBasicInfo.setWeight(playerInfoList.get(4).text());
					playerBasicInfo.setExp(playerInfoList.get(5).text());
					list.add(playerBasicInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//TODO 处理list
		//for (PlayerBasicInfo playerBasicInfo : list) {
		//	System.out.println(playerBasicInfo);
		//}
		
		webClient.close();
	}

}
