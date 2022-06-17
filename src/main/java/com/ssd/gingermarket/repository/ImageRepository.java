package com.ssd.gingermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssd.gingermarket.domain.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
