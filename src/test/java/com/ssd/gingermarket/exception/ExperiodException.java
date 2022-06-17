package com.ssd.gingermarket.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ExperiodException extends RuntimeException {
	private final ErrorResult errorResult;
}
