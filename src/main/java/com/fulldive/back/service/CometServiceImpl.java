package com.fulldive.back.service;

import com.fulldive.back.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

public interface CometServiceImpl {

	int cometInsert(Map<String, Object> params);
	int cometUpdate(Map<String, Object> params);
}
