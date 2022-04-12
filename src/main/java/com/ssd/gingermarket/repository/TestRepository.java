package com.ssd.gingermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssd.gingermarket.domain.TestEntity;

// <Entity 타입, PK 타입>
@Repository
public interface TestRepository extends JpaRepository<TestEntity, Integer>{
	// 자동으로 CRUD 메소드가 생성된다.
	// Entity 클래스와 기본 Entity Repository는 함께 위치해야 한다.
}
