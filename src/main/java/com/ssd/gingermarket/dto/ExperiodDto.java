package com.ssd.gingermarket.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ExperiodDto {
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Info {
		private Long experiodIdx;
		private String category;
		private LocalDate endDate;
		private int dDay;
	}

}
