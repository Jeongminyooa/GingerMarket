package com.ssd.gingermarket.domain;

import lombok.*;

import javax.persistence.*;

@Entity //테이블과 링크될 클래스, 절대 Setter를 만들지 않는다. 
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name="testinfo") // 카멜식으로 하면 DB는 _로 인식 
public class TestEntity {
	@Id // PK
	@GeneratedValue // PK 생성 규칙 
	private int testIdx; // 카멜식으로 하면 DB -> test_idx 
	
	// 선언 안해도 필드는 컬럼, 추가 옵션 넣으려면 필
	@Column(unique = true, nullable = false)
	private String userId;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String name;
}
