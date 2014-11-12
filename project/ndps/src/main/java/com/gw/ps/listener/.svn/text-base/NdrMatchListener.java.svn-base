package com.gw.ps.listener;

import java.util.List;

import javax.annotation.Resource;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;

import com.gw.ps.dao.INewsDuplicateRemovalDao;
import com.gw.ps.model.NewsMatch;
import com.gw.ps.pbbean.PbNewsRes;
import com.gw.ps.utils.ByteUtil;
import com.gw.ps.utils.DateUtil;
import com.gw.ps.utils.LogUtil;

public class NdrMatchListener implements MessageListener {

	private final Logger logger = LogUtil.getInstance(this.getClass());

	@Resource(name = "ndrDao")
	private INewsDuplicateRemovalDao ndrDao;

	@Override
	public void onMessage(Message message) {

		if (message instanceof MapMessage) {
			MapMessage map = (MapMessage) message;
			String info = "";
			try {

				PbNewsRes.NewsIndRes newindRes = PbNewsRes.NewsIndRes.parseFrom(map.getBytes("news-newsIndRes-queue"));
				List<PbNewsRes.NewsIndResDetail> detailList = newindRes.getNewsindentifydetailList();

				NewsMatch newsMatch = new NewsMatch();
				newsMatch.setMatchId(ByteUtil.long2HL(newindRes.getNewsCode()));
				newsMatch.setTimestamp(DateUtil.getCurrentDateStr());
				DateUtil.timestampToDateStr(ByteUtil.long2HL(newindRes.getNewsCode()));
				newsMatch.setTimeIn(DateUtil.timestampToDateStr(ByteUtil.long2HL(newindRes.getTimeIn())));
				newsMatch.setTimeOut(DateUtil.timestampToDateStr(ByteUtil.long2HL(newindRes.getTimeOut())));

				for (int i = 0; i < detailList.size(); i++) {
					newsMatch.setContent(detailList.get(i).getITNAME());
					newsMatch.setOrganCode(detailList.get(i).getITCODE());
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

}
