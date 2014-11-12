package com.gw.ps.listener;

import javax.annotation.Resource;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;

import com.gw.ps.dao.INewsDuplicateRemovalDao;
import com.gw.ps.model.NewsFilter;
import com.gw.ps.pbbean.PbNewsRes;
import com.gw.ps.utils.ByteUtil;
import com.gw.ps.utils.DateUtil;
import com.gw.ps.utils.LogUtil;

public class NdrFilterListener implements MessageListener {

	private final Logger logger = LogUtil.getInstance(this.getClass());

	@Resource(name = "ndrDao")
	private INewsDuplicateRemovalDao ndrDao;

	@Override
	public void onMessage(Message message) {
		if (message instanceof MapMessage) {
			MapMessage map = (MapMessage) message;
			NewsFilter newFilter = new NewsFilter();
			try {

				PbNewsRes.NewsDupRes newindDupRes = PbNewsRes.NewsDupRes.parseFrom(map.getBytes("news-newsDupRes-queue"));

				newFilter.setDupflag(newindDupRes.getDupflag());
				newFilter.setFilterId(ByteUtil.long2HL(newindDupRes.getNewsCode()));
				newFilter.setContent(newindDupRes.getText());
				newFilter.setIscd(newindDupRes.getIscd());
				newFilter.setIsdup(newindDupRes.getIsdup());
				newFilter.setIstrash(newindDupRes.getHasdust());
				newFilter.setOriginId(ByteUtil.long2HL(newindDupRes.getDupNewsCode()));
				newFilter.setSignal(ByteUtil.long2HL(newindDupRes.getSignal()));
				newFilter.setTimestamp(DateUtil.getCurrentDateStr());
				newFilter.setTitle(newindDupRes.getTitle());
				newFilter.setTimeIn(DateUtil.timestampToDateStr(ByteUtil.long2HL(newindDupRes.getTimeIn())));
				newFilter.setTimeOut(DateUtil.timestampToDateStr(ByteUtil.long2HL(newindDupRes.getTimeOut())));
				newFilter.setWebgrab(String.valueOf(newindDupRes.getWebgrab()));
				newFilter.setTrssource(String.valueOf(newindDupRes.getTrssource()));
				newFilter.setDustflag(String.valueOf(newindDupRes.getDustflag()));
				newFilter.setSlevel(String.valueOf(newindDupRes.getSlevel()));
				// newFilter.setTimeSimhash(ByteUtil.long2HL(newindDupRes.getTimeSimhash()));
				// newFilter.setTimeShingle(ByteUtil.long2HL(newindDupRes.getTimeShingle()));
				// newFilter.setTimeShingling(ByteUtil.long2HL(newindDupRes.getTimeShingling()));

				newFilter.setGroupName(newindDupRes.getGroupName());
				newFilter.setChannel(newindDupRes.getChannel());
				newFilter.setSiteName(newindDupRes.getSiteName());
				
				StringBuffer sb = new StringBuffer();
				for (String s : newindDupRes.getDustList()) {
					sb.append(s);
				}

				StringBuffer sb1 = new StringBuffer();
				sb1.append("Simhash:").append(ByteUtil.long2HL(newindDupRes.getTimeSimhash())).append("ms,");
				sb1.append("Shingle:").append(ByteUtil.long2HL(newindDupRes.getTimeShingle())).append("ms,");
				sb1.append("Shingling:").append(ByteUtil.long2HL(newindDupRes.getTimeShingling())).append("ms,\r");
				sb1.append("SpoutBoltA:").append(ByteUtil.long2HL(newindDupRes.getTimeSpoutBoltA())).append("ms,");
				sb1.append("BoltABoltB:").append(ByteUtil.long2HL(newindDupRes.getTimeBoltABoltB())).append("ms,");
				sb1.append("BoltBBoltC:").append(ByteUtil.long2HL(newindDupRes.getTimeBoltBBoltC())).append("ms,\r");
				sb1.append("BoltCBoltD:").append(ByteUtil.long2HL(newindDupRes.getTimeBoltCBoltD())).append("ms,");
				sb1.append("BoltHBoltE:").append(ByteUtil.long2HL(newindDupRes.getTimeBoltHBoltE())).append("ms,");
				sb1.append("SpoutBoltF:").append(ByteUtil.long2HL(newindDupRes.getTimeSpoutBoltF())).append("ms,\r");
				sb1.append("BoltDBoltH:").append(ByteUtil.long2HL(newindDupRes.getTimeBoltDBoltH())).append("ms");

				newFilter.setTimedesc(sb1.toString());

				newFilter.setDust(sb.toString());
				logger.debug(sb.toString());
				ndrDao.addNewsClass(newFilter);

			} catch (Exception e) {
				logger.error(newFilter.toString());
				e.printStackTrace();
			}

		}
	}
}
