package com.bootdo.log.service;

import org.springframework.stereotype.Service;
import com.bootdo.common.domain.PageDO;
import com.bootdo.common.utils.Query;
import com.bootdo.log.domain.LogDO;

@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
