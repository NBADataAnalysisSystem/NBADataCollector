package logic.teamlogic;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import data.DataJdbcImp;
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
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 3; j++) {
					for (int k=1; k < 6; k++) {
						TeamBasicInfo teamBasicInfo = new TeamBasicInfo();
						teamBasicInfo.setId(Integer.toString(i*15+j*5+k));
						teamBasicInfo.setDivision(document.getElementsByClass("t1").get(i).text());
						teamBasicInfo.setSection(document.getElementsByClass("t2").get(j).text());
						String name = document.getElementsByClass("teamLabel").get(
								Integer.parseInt(teamBasicInfo.getId())-1).text();
						teamBasicInfo.setLocation(name.split(" ")[0]);
						teamBasicInfo.setFullName(name.split(" ")[1]);
						teamBasicInfo.setAbbreviation(document.getElementsByClass("teamLogo").get(
								Integer.parseInt(
										teamBasicInfo.getId())-1).html().split("  ")[1].substring(0, 3));
						list.add(teamBasicInfo);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DataJdbcImp dataJdbcImp = new DataJdbcImp();
		dataJdbcImp.storeTeam(list);
		dataJdbcImp.close();
		
		webClient.close();
	}

}
