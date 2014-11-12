package com.gw.ps.listener;

import javax.annotation.Resource;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;

import com.gw.ps.dao.INewsDuplicateRemovalDao;
import com.gw.ps.model.NewsShingle;
import com.gw.ps.pbbean.PbSignalShingle;
import com.gw.ps.utils.ByteUtil;
import com.gw.ps.utils.DateUtil;
import com.gw.ps.utils.LogUtil;

public class NdrShingleListener implements MessageListener {

	private final Logger logger = LogUtil.getInstance(this.getClass());

	@Resource(name = "ndrDao")
	private INewsDuplicateRemovalDao ndrDao;

	@Override
	public void onMessage(Message message) {
		if (message instanceof MapMessage) {
			MapMessage map = (MapMessage) message;
			NewsShingle newShingle = new NewsShingle();
			try {

				PbSignalShingle.ShingleMessage shingle = PbSignalShingle.ShingleMessage.parseFrom(map.getBytes("shingle-queue"));

				newShingle.setId(ByteUtil.long2HL(shingle.getID()));
				newShingle.setShingle(shingle.getShingle());
				newShingle.setTime(DateUtil.getCurrentDateStr());

				logger.debug(newShingle.toString());
				ndrDao.addNewsClass(newShingle);

			} catch (Exception e) {
				logger.error(newShingle.toString());
				e.printStackTrace();
			}

		}
	}

}
