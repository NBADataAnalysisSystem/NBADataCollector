package logic.teamlogic;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import entity.teamentity.TeamBasicInfo;
import logicservice.teamlogicservice.TeamLogicService;

public class TeamLogic implements TeamLogicService {

	final String INDEX_URL = "http://china.nba.com";
	
	public void getTeamBasicInfo() {
		WebClient webClient = new WebClient();
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);
		
		ArrayList<TeamBasicInfo> list = new ArrayList<TeamBasicInfo>();
		
		try {
			HtmlPage indexPage = webClient.getPage(INDEX_URL);
			HtmlAnchor teamBtn = indexPage.getAnchorByText("球队");
			HtmlPage teamPage = teamBtn.click();
			Document document = Jsoup.parse(teamPage.asXml(), "UTF-8");
			for (int i = 1; i < 31; i++) {
				TeamBasicInfo teamBasicInfo = new TeamBasicInfo();
				Elements team = document.getElementsByClass("team" + i);
				teamBasicInfo.setPath(INDEX_URL + team.attr("href"));
				teamBasicInfo.setName(team.text());
				list.add(teamBasicInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//TODO 处理list
		//for (TeamBasicInfo teamBasicInfo : list) {
		//	System.out.println(teamBasicInfo);
		//}
		
		webClient.close();
	}

}
