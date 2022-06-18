package com.ssd.gingermarket.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExperiodType {
	OPEN("교체 마감일로부터 여유로움"),
	CLOSE_DEADLINE("3일 내의 교체가 필요함"),
	DEADLINE("교체가 필요한 날"),
	CLOSE("교체주기가 이미 지남")
	;
	
	private String descr;
}
