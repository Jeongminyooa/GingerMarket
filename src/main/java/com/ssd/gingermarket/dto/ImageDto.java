package com.ssd.gingermarket.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ImageDto {

	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Request {
		private MultipartFile imageFile;
	}
}
