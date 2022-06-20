package com.ssd.gingermarket.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssd.gingermarket.domain.Experiod;
import com.ssd.gingermarket.domain.ExperiodType;
import com.ssd.gingermarket.domain.User;
import com.ssd.gingermarket.dto.ExperiodDto;
import com.ssd.gingermarket.dto.ExperiodDto.Info;
import com.ssd.gingermarket.repository.ExperiodRepository;
import com.ssd.gingermarket.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //final이 붙은 필드 생성 
@Service
public class ExperiodServiceImpl implements ExperiodService {

	private final ExperiodRepository experiodRepository;
	private final UserRepository userRepository;
	
	// SchedulerConfig에 설정된 TaskScheduler 빈을 주입 받음
	private final TaskScheduler scheduler;
		
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void addExperiod(Long authorIdx, String category, int period) {
		// TODO Auto-generated method stub
		User author = userRepository.findById(authorIdx).orElseThrow();
		
		Experiod experiod = Experiod.experiodBuilder()
				.author(author)
				.category(category)
				.status(ExperiodType.OPEN)
				.build();
		
		author.getExperiodList().add(experiod);
		
		experiodRepository.save(experiod);
		experiod.caculateEndDate(period);
		
		// d-day 스케줄러 등록
		Runnable updateDdayRunner = new Runnable() { // anonymous class 정의
			@Override
			public void run() {   // 스케쥴러에 의해 미래의 특정 시점에 실행될 작업을 정의				
				LocalDate curTime = LocalDate.now();
				// dDay를 1씩 줄임
				experiodRepository.updateStatus(curTime, ExperiodType.CLOSE_DEADLINE);
				System.out.println("updateDdayRunner is executed at " + curTime);
			}
		};
		
		Runnable todayIsDdayRunner = new Runnable() { // anonymous class 정의
			@Override
			public void run() {   // 스케쥴러에 의해 미래의 특정 시점에 실행될 작업을 정의				
				LocalDate curTime = LocalDate.now();
				// dDay를 1씩 줄임
				experiodRepository.updateStatus(curTime, ExperiodType.DEADLINE);
				System.out.println("todayIsDdayRunner is executed at " + curTime);
			}
		};
		
		  // task schedule 생성: 마감 3일전 updateDdayRunner.run() 메소드가 자동 실행됨
		scheduler.schedule(updateDdayRunner, Date.valueOf(experiod.getEndDate().minusDays(3)));
		System.out.println("updateDdayRunner has been scheduled to execute at " + experiod.getEndDate().minusDays(3));

		// task schedule 생성: 마감날 todayIsDdayRunner.run() 메소드가 자동 실행됨
		scheduler.schedule(todayIsDdayRunner, Date.valueOf(experiod.getEndDate()));	
		System.out.println("todayIsDdayRunner has been scheduled to execute at " + experiod.getEndDate());

	}

	@Override
	@Transactional(readOnly = true)
	public List<ExperiodDto.Info> getAllExperiod(Long userId) {
		// TODO Auto-generated method stub
		
		User author = userRepository.findById(userId).orElseThrow();
		List<Experiod> experiodList = experiodRepository.findAllByAuthor(author);
		
		// entity -> dto 변환
		List<ExperiodDto.Info> dto = experiodList.stream().map(ex -> new ExperiodDto.Info(ex.getExperiodIdx(), ex.getCategory(), ex.getEndDate(), getPeriod(ex.getStatus(), ex.getEndDate()))).collect(Collectors.toList());
		
		return dto;
	}

	@Override
	@Transactional(readOnly = true)
	public Experiod getExperiod(Long experiodIdx) {
		Experiod experiod = experiodRepository.findById(experiodIdx).orElseThrow();
		
		return experiod;
	}
		
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void removeExperiod(Long experiodIdx) {
		// TODO Auto-generated method stub
		experiodRepository.deleteById(experiodIdx);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Info> getExperiodByDate(Long authorIdx, String date) {
		// TODO Auto-generated method stub
		
		// String -> LocalDate
		String[] dateList = date.split("\\.");
		int year = Integer.parseInt(dateList[0]);
		int month = Integer.parseInt(dateList[1]);
		int day = Integer.parseInt(dateList[2]);
		
		LocalDate targetDate = LocalDate.of(year, month, day);
		
		// 오늘 날짜로부터의 차이
		int dDay = Period.between(LocalDate.now(), targetDate).getDays();
		
		List<Experiod> experiodList = experiodRepository.findAllByUserIdAndEndDate(authorIdx, targetDate);
		
		// entity -> dto 변환
		List<ExperiodDto.Info> dto = experiodList.stream().map(ex -> new ExperiodDto.Info(ex.getExperiodIdx(), ex.getCategory(), ex.getEndDate(), getPeriod(ex.getStatus(), ex.getEndDate()))).collect(Collectors.toList());
				
		return dto;
	}
	
	// 오늘 날짜와 타겟 날짜와의 차이
	public long getPeriod(ExperiodType status, LocalDate targetDate) {
		if(status == ExperiodType.OPEN || status == ExperiodType.CLOSE_DEADLINE) {
			System.out.println(LocalDate.now());
			return ChronoUnit.DAYS.between(LocalDate.now(), targetDate);
		}
		else if(status == ExperiodType.DEADLINE) {
			return 0;
		} 
		else {
			return -1;
		}
	}

}
