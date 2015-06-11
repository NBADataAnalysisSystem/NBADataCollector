package init;

import org.apache.log4j.Logger;

import logic.matchlogic.MatchLogic;
import logicservice.matchlogicservice.MatchLogicService;

public class Main {
	
	private final static Logger log = Logger.getRootLogger();
	
	public static void main(String[] args) {
		log.info("开始采集数据");
		
		//PlayerLogicService playerLogic = new PlayerLogic();
		//playerLogic.getPlayer();
		
		//log.info("开始采集球队基本信息");
		//TeamLogicService teamLogic = new TeamLogic();
		//teamLogic.getTeamBasicInfo();
		//log.info("球队基本信息采集完成");

		MatchLogicService matchLogic = new MatchLogic();
		matchLogic.getMatchBasicInfo();
		
		log.info("采集数据完成");
	}

}
