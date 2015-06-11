package logic.teamlogic;

import java.net.URL;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import data.DataJdbcImp;
import entity.teamentity.TeamBasicInfo;
import logicservice.teamlogicservice.TeamLogicService;

public class TeamLogic implements TeamLogicService {

	private final static Logger log = Logger.getRootLogger();
	final String INDEX_URL = "http://china.nba.com";
	final String URL_PREFIX = "http://china.nba.com/wap/static/data/team/stats_";
	final String URL_POSTFIX = ".json";
	
	public void getTeam() {
		log.info("开始采集球队信息");
		
		WebClient webClient = new WebClient();
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);
		
		ArrayList<TeamBasicInfo> list = new ArrayList<TeamBasicInfo>();
		
		try {
			HtmlPage indexPage = webClient.getPage(INDEX_URL);
			HtmlAnchor teamBtn = indexPage.getAnchorByText("球队");
			HtmlPage teamPage = teamBtn.click();
			Document document = Jsoup.parse(teamPage.asXml(), "UTF-8");
			
			Elements section = document.getElementsByClass("area");
			for (Element teams : section) {
				for (Element team : teams.getElementsByTag("a")) {
					TeamBasicInfo teamBasicInfo = new TeamBasicInfo();
					teamBasicInfo.setCode(team.attr("href").split("/")[1]);
					
					if (teamBasicInfo.getCode().equals("76ers")) {
						teamBasicInfo.setCode("sixers");
					}
					
					WebRequest request;
					try {
						request = new WebRequest(new URL(
								URL_PREFIX + teamBasicInfo.getCode() + URL_POSTFIX));
						WebResponse response = webClient.loadWebResponse(request);
						String json = new String(response.getContentAsString().getBytes("iso-8859-1"),"utf-8");
						JSONObject jsonObject = new JSONObject(json);
						jsonObject = new JSONObject(jsonObject.getString("payload"));
						jsonObject = new JSONObject(jsonObject.getString("profile"));
						teamBasicInfo.setId(jsonObject.getString("id"));
						teamBasicInfo.setDivision(jsonObject.getString("conference"));
						teamBasicInfo.setSection(jsonObject.getString("division"));
						teamBasicInfo.setLocation(jsonObject.getString("cityEn"));
						teamBasicInfo.setFullName(jsonObject.getString("nameEn"));
						teamBasicInfo.setAbbreviation(jsonObject.getString("abbr"));
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					list.add(teamBasicInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DataJdbcImp dataJdbcImp = new DataJdbcImp();
		dataJdbcImp.storeTeam(list);
		dataJdbcImp.close();
		
		webClient.close();
		
		log.info("球队信息采集完成");
	}

}
