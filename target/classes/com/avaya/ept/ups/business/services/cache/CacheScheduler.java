package com.avaya.ept.ups.business.services.cache;

import java.util.Date;

import org.openspaces.core.GigaSpace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.avaya.ept.ups.rest.domain.CacheLandingPadNumber;
import com.avaya.ept.ups.rest.domain.LPNState;
import com.gigaspaces.client.WriteModifiers;
import com.j_spaces.core.client.SQLQuery;

public class CacheScheduler {

	private static Logger logger = LoggerFactory.getLogger(CacheScheduler.class);

	@Autowired
	private GigaSpace gigaSpace;

	/*
	 * @Autowired
	 * 
	 * @Resource(name="restConfig") private Properties restConfig;
	 * 
	 * private final int EXP_TIME =
	 * BackendUtil.getExpiredTimeInMilliSecFromConfig(restConfig);
	 * 
	 * @Scheduled(fixedRate = 123)
	 */

	public void runScheduler() {
		updateExpiredLPN();
		removeDeletedStateLPN();
	}

	public void updateExpiredLPN() {
		logger.info("BEGIN updateExpiredLPN");

		try {
			logger.info("updateExpiredLPN : gigaSpace query building");
			SQLQuery<CacheLandingPadNumber> query = new SQLQuery<CacheLandingPadNumber>(
					CacheLandingPadNumber.class,
					"lpnState = ? AND expTimeStamp < ?");
			long currentTimeStamp = new Date().getTime();
			query.setParameters(LPNState.IN_USE, currentTimeStamp);

			if (gigaSpace != null) {
				CacheLandingPadNumber[] result = gigaSpace.readMultiple(query);
				if (result != null && result.length > 0) {
					logger.info("updateExpiredLPN : totalFetches = "
							+ result.length);
					for (CacheLandingPadNumber cacheLPN : result) {
						cacheLPN.setCallId(null);
						cacheLPN.setTransferNumber(null);
						cacheLPN.setVersion(null);
						cacheLPN.setExpTimeStamp(-1);
						cacheLPN.setLpnState(LPNState.AVAILABLE);

						gigaSpace.write(cacheLPN,
								WriteModifiers.UPDATE_OR_WRITE);
					}
				} else {
					logger.info("updateExpiredLPN : totalFetches = " + 0);
				}
			} else {
				logger.warn("gigaSpace obj null");
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		logger.info("END updateExpiredLPN");
	}

	public void removeDeletedStateLPN() {
		logger.info("BEGIN removeDeletedStateLPN");

		try {
			logger.info("removeDeletedStateLPN : gigaSpace query building");
			SQLQuery<CacheLandingPadNumber> query = new SQLQuery<CacheLandingPadNumber>(
					CacheLandingPadNumber.class, "lpnState = ?");
			query.setParameters(LPNState.DELETED);

			if (gigaSpace != null) {
				CacheLandingPadNumber[] result = gigaSpace.readMultiple(query);
				if (result != null && result.length > 0) {
					logger.info("removeDeletedStateLPN : totalFetches = "
							+ result.length);
					for (CacheLandingPadNumber cacheLPN : result) {
						gigaSpace.clear(cacheLPN);
					}
				} else {
					logger.info("removeDeletedStateLPN : totalFetches = " + 0);
				}
			} else {
				logger.warn("gigaSpace obj null");
			}

		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		logger.info("END removeDeletedStateLPN");
	}

}
