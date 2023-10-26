package com.influence.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.influence.domain.post.entity.PostReview;

public interface PostReviewRepository extends JpaRepository<PostReview, Long> {
	

}
