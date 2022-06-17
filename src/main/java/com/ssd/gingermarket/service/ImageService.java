package com.ssd.gingermarket.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssd.gingermarket.domain.Image;

@Service
public interface ImageService {
		// 이미지 업로드
		public Image uploadFile(MultipartFile image);
		
		// 이미지 가져오기
		public Image getImageById(Long imageIdx);
		
		// 이미지 삭제
		public void removeImage(Long imageIdx);
}
