package logic.playerlogic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
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
		
		webClient.close();
		
		//TODO 处理list
		for (PlayerBasicInfo playerBasicInfo : list) {
			getPlayerHeadPicture(playerBasicInfo, DATA_PATH + "/player/head/");
		}
	}
	
	private void getPlayerHeadPicture(PlayerBasicInfo player, String path) {
		try {
			String playerPath = player.getPath();
			int i = playerPath.lastIndexOf("/");
			String id = playerPath.substring(i+1, playerPath.lastIndexOf("_"));
			String url = "http://china.nba.com/media/img/players/head/230x185/"
					+ id + ".png";
			BufferedInputStream in;
			try {
				in = new BufferedInputStream(new URL(url).openStream());
			} catch (FileNotFoundException e) {
				in = new BufferedInputStream(
						new URL("http://china.nba.com/media/img/players/head/230x185/"
								+ "not_found.png").openStream());
			}
			
			BufferedOutputStream out = new BufferedOutputStream(
					new FileOutputStream(new File(path + player.getEnglishName() + ".png")));
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
