package com.influence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.influence.entity.Follow;



public interface FollowRepository extends JpaRepository<Follow, Long> {

	Follow findByFollowerIdAndFollowingId(Long followerId, Long followingId);

}
