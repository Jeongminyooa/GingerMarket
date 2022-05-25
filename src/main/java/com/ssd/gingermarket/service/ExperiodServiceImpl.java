package com.ssd.gingermarket.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssd.gingermarket.domain.Experiod;
import com.ssd.gingermarket.dto.ExperiodDto;
import com.ssd.gingermarket.repository.ExperiodRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //final이 붙은 필드 생성 
@Service
public class ExperiodServiceImpl implements ExperiodService {

	private final ExperiodRepository experiodRepository;
		
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void addExperiod(Long userId, String category, int period) {
		// TODO Auto-generated method stub
		Experiod experiod = Experiod.experiodBuilder()
				.userId(userId)
				.category(category)
				.dDay(period)
				.build();
		
		experiodRepository.save(experiod);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ExperiodDto.Info> getAllExperiod(Long userId) {
		// TODO Auto-generated method stub
		List<Experiod> experiodList = experiodRepository.findAllByUserId(userId);
		
		// entity -> dto 변환
		List<ExperiodDto.Info> dto = experiodList.stream().map(ex -> new ExperiodDto.Info(ex.getId(), ex.getCategory(), ex.getEndDate(), ex.getDDay())).collect(Collectors.toList());
		
		return dto;
	}

	@Override
	@Transactional(readOnly = true)
	public Experiod getExperiod(Long experiodIdx) {
		Optional<Experiod> experiodOptional = experiodRepository.findById(experiodIdx);
		
		if(experiodOptional.isPresent()) {
			return experiodOptional.get();
		}
		return null;
	}
	
	
	@Override
	@Transactional
	public void updateExperiodDday() {
		//experiodRepository.updateDday();
	}
		
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void removeExperiod(Long experiodIdx) {
		// TODO Auto-generated method stub
		experiodRepository.deleteById(experiodIdx);
	}

}
