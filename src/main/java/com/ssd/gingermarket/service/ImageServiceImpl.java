package com.ssd.gingermarket.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssd.gingermarket.domain.Image;
import com.ssd.gingermarket.repository.ImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

	private final FileHandler fileHandler;
	private final ImageRepository imageRepository;
	
	@Override
	public Image uploadFile(MultipartFile image) {
		String fileName = fileHandler.uploadFile(image);
		
		Image img = new Image();
		img.updateImage(fileName);
		
		imageRepository.saveAndFlush(img);
		
		return img;
	}

	@Override
	public Image getImageById(Long imageIdx) {
		// TODO Auto-generated method stub
		Optional<Image> imageOptional = imageRepository.findById(imageIdx);
		
		if(imageOptional.isPresent()) {
			return imageOptional.get();
		}
		
		return null;
	}

	@Override
	public void removeImage(Long imageIdx) {
		// TODO Auto-generated method stub
		imageRepository.deleteById(imageIdx);
	}

}
