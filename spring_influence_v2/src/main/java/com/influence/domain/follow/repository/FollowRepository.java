package com.influence.domain.follow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.influence.domain.follow.entity.Follow;



public interface FollowRepository extends JpaRepository<Follow, Long> {

	Follow findByFollowerIdAndFollowingId(Long followerId, Long followingId);

}
