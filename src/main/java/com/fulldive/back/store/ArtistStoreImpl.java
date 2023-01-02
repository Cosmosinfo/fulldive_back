package com.fulldive.back.store;

import com.fulldive.back.entity.ArtistEntity;

import java.util.List;
import java.util.Map;

public interface ArtistStoreImpl {
	
	int artistInsert(Map<String, Object> params);
	int artistUpdate(Map<String, Object> params);
	int artistDelete(Map<String, Object> params);

	List<ArtistEntity> newArtistList();
	
}
