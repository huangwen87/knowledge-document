package com.gw.ps.listener;

import javax.annotation.Resource;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;

import com.gw.ps.dao.INewsDuplicateRemovalDao;
import com.gw.ps.model.NewsSignal;
import com.gw.ps.pbbean.PbSignalShingle;
import com.gw.ps.utils.ByteUtil;
import com.gw.ps.utils.DateUtil;
import com.gw.ps.utils.LogUtil;

public class NdrSignalListener implements MessageListener {

	private final Logger logger = LogUtil.getInstance(this.getClass());

	@Resource(name = "ndrDao")
	private INewsDuplicateRemovalDao ndrDao;

	@Override
	public void onMessage(Message message) {
		if (message instanceof MapMessage) {
			MapMessage map = (MapMessage) message;
			NewsSignal newsSignal = new NewsSignal();
			try {

				PbSignalShingle.SignalMessage signal = PbSignalShingle.SignalMessage.parseFrom(map.getBytes("signal-queue"));

				newsSignal.setId(ByteUtil.long2HL(signal.getID()));
				newsSignal.setSignal(ByteUtil.long2HL(signal.getSignal()));
				newsSignal.setTime(DateUtil.getCurrentDateStr());
				newsSignal.setSlevel(String.valueOf(signal.getSlevel()));

				logger.debug(newsSignal.toString());
				ndrDao.addNewsClass(newsSignal);

			} catch (Exception e) {
				//logger.error(newsSignal.toString());
				//e.printStackTrace();
			}

		}
	}

}
