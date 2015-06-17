package init;

import logic.matchlogic.MatchLogic;
import logicservice.matchlogicservice.MatchLogicService;

import org.apache.log4j.Logger;

public class Test {
	
	private final static Logger log = Logger.getRootLogger();
	
	public static void main(String[] args) {
		log.info("开始采集数据");

		MatchLogicService matchLogic = new MatchLogic();
		matchLogic.getMatchBasicInfo("2015-6-8", "2015-6-17");
		
		log.info("采集数据完成");
	}

}
