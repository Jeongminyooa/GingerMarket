package com.ssd.gingermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssd.gingermarket.domain.SharePost;
import com.ssd.gingermarket.dto.SharePostDto;
import com.ssd.gingermarket.dto.SharePostDto.viewResponse;

//<Entity 타입, PK 타입>
@Repository
public interface SharePostRepository extends JpaRepository<SharePost, Long>{
}
