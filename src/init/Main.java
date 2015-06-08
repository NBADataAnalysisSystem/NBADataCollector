package init;

import org.apache.log4j.Logger;

import logic.teamlogic.TeamLogic;
import logicservice.teamlogicservice.TeamLogicService;

public class Main {
	
	private final static Logger log = Logger.getRootLogger();
	
	public static void main(String[] args) {
		log.info("开始采集数据");
		
		//log.info("开始采集球员基本信息");
		//PlayerLogicService playerLogic = new PlayerLogic();
		//playerLogic.getPlayerBasicInfo();
		//log.info("球员基本信息采集完成");
		
		log.info("开始采集球队基本信息");
		TeamLogicService teamLogic = new TeamLogic();
		teamLogic.getTeamBasicInfo();
		log.info("球队基本信息采集完成");
		
		log.info("采集数据完成");
	}

}
