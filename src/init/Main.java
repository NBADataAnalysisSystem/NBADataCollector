package init;

import org.apache.log4j.Logger;

import logic.hotlogic.HotLogic;
import logicservice.hotlogicservice.HotLogicService;

public class Main {
	
	private final static Logger log = Logger.getRootLogger();
	
	public static void main(String[] args) {
		log.info("开始采集数据");
		
		//PlayerLogicService playerLogic = new PlayerLogic();
		//playerLogic.getPlayer();
		
		//TeamLogicService teamLogic = new TeamLogic();
		//teamLogic.getTeam();

		//MatchLogicService matchLogic = new MatchLogic();
		//matchLogic.getMatchBasicInfo();
		
		HotLogicService hotLogic = new HotLogic();
		hotLogic.getHotInfo();
		
		log.info("采集数据完成");
	}

}
