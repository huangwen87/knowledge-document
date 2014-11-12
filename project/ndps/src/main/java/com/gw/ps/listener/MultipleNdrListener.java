package com.gw.ps.listener;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.gw.ps.dao.INewsDuplicateRemovalDao;
import com.gw.ps.model.NewsMatch;
import com.gw.ps.pbbean.PbNewsRes;
import com.gw.ps.utils.ByteUtil;
import com.gw.ps.utils.DateUtil;
import com.gw.ps.utils.LogUtil;

public class MultipleNdrListener {
	private final Logger logger = LogUtil.getInstance(this.getClass());

	@Resource(name = "ndrDao")
	private INewsDuplicateRemovalDao ndrDao;

	public void receiveMatchMsg(Map<String, byte[]> map) {
		String info = "";
		try {
			PbNewsRes.NewsIndRes newindRes = PbNewsRes.NewsIndRes.parseFrom(map.get("news-newsIndRes-queue"));
			List<PbNewsRes.NewsIndResDetail> detailList = newindRes.getNewsindentifydetailList();
			for (int i = 0; i < detailList.size(); i++) {
				NewsMatch newsMatch = new NewsMatch();
				newsMatch.setMatchId(ByteUtil.long2HL(newindRes.getNewsCode()));
				newsMatch.setContent(detailList.get(i).getITNAME());
				newsMatch.setOrganCode(detailList.get(i).getITCODE());
				newsMatch.setTimestamp(DateUtil.getCurrentDateStr());
				info = newsMatch.toString();
				logger.debug(info);
				ndrDao.addNewsClass(newsMatch);
			}
		} catch (Exception e) {
			logger.error(info);
			e.printStackTrace();
		}

	}

}
