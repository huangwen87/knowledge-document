package com.gw.ps.listener;

import javax.annotation.Resource;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;

import com.gw.ps.dao.INewsDuplicateRemovalDao;
import com.gw.ps.model.NewsShingling;
import com.gw.ps.pbbean.PbSignalShingle;
import com.gw.ps.utils.ByteUtil;
import com.gw.ps.utils.DateUtil;
import com.gw.ps.utils.LogUtil;

public class NdrShinglingListener implements MessageListener {

	private final Logger logger = LogUtil.getInstance(this.getClass());

	@Resource(name = "ndrDao")
	private INewsDuplicateRemovalDao ndrDao;

	@Override
	public void onMessage(Message message) {
		if (message instanceof MapMessage) {
			MapMessage map = (MapMessage) message;
			NewsShingling newsShingling = new NewsShingling();
			try {

				PbSignalShingle.ShinglingMessage shingling = PbSignalShingle.ShinglingMessage.parseFrom(map.getBytes("shingling-queue"));

				newsShingling.setId(ByteUtil.long2HL(shingling.getID()));
				newsShingling.setWordnum(shingling.getWordnum());
				newsShingling.setShingling(shingling.getShingle());
				newsShingling.setTime(DateUtil.getCurrentDateStr());
				logger.debug(newsShingling.toString());
				ndrDao.addNewsClass(newsShingling);

			} catch (Exception e) {
				logger.error(newsShingling.toString());
				e.printStackTrace();
			}

		}
	}

}
