package com.fulldive.back.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Data
public class CategoryEntity {
	private String categoryId;
	private String categoryCode;
	private String categoryNameKr;
	private String categoryNameEn;
	private Integer categorySequence;
	private Timestamp createTimestamp;
}
