package com.ssd.gingermarket.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorResult {
	NULL_USER_IDX(HttpStatus.BAD_REQUEST, "User idx is null"),
	
	;
	
	private final HttpStatus httpStatus;
    private final String message;
}
