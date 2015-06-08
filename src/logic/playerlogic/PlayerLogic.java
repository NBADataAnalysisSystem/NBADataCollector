package logic.playerlogic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;

import org.apache.log4j.Logger;
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

	private final static Logger log = Logger.getRootLogger();
	final String INDEX_URL = "http://china.nba.com";
	final String DATA_PATH = "./res";
	
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
					
					HtmlAnchor playerPersonalBtn = playerPage.getAnchorByHref(
							playerInfoList.get(0).select("a").attr("href"));
					HtmlPage playerPersonalPage = playerPersonalBtn.click();
					Document playerPersonalDoc = Jsoup.parse(playerPersonalPage.asXml(), "UTF-8");
					String jerseyNo = playerPersonalDoc.getElementsByClass("jersey_no").text();
					playerBasicInfo.setJerseyNo(jerseyNo);
					
					Elements values = playerPersonalDoc.getElementsByClass("value");
					playerBasicInfo.setBirthday(values.get(2).text());
					playerBasicInfo.setSchool(values.get(5).text());
					
					Element teamElement = playerPersonalDoc.getElementsByClass("headerLogoWrapper").get(0);
					playerBasicInfo.setTeam(teamElement.html().split("  ")[1].substring(0, 3));
					
					String playerPath = playerInfoList.get(0).select("a").attr("href");
					int index = playerPath.lastIndexOf("/");
					String id = playerPath.substring(index+1, playerPath.lastIndexOf("_"));
					playerBasicInfo.setId(id);
					
					String name = playerInfoList.get(0).text().replace(" ", "");
					playerBasicInfo.setName(name.split(" ")[0]);
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
		
		webClient.close();
		
		//TODO 在这里调用data层对应方法储存list里的内容
		
		log.info("开始收集球员头像");
		for (PlayerBasicInfo playerBasicInfo : list) {
			getPlayerHeadPicture(playerBasicInfo, DATA_PATH + "/player/head/");
		}
		log.info("球员头像收集完成");
	}
	
	private void getPlayerHeadPicture(PlayerBasicInfo player, String path) {
		try {
			String url = "http://china.nba.com/media/img/players/head/230x185/"
					+ player.getId() + ".png";
			BufferedInputStream in;
			try {
				in = new BufferedInputStream(new URL(url).openStream());
			} catch (FileNotFoundException e) {
				in = new BufferedInputStream(
						new URL("http://china.nba.com/media/img/players/head/230x185/"
								+ "not_found.png").openStream());
			}
			
			BufferedOutputStream out = new BufferedOutputStream(
					new FileOutputStream(new File(path + player.getName() + ".png")));
			byte[] buff = new byte[2048];
			int length = in.read(buff);
			while (length != -1) {
				out.write(buff, 0, length);
				length = in.read(buff);
			}
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
