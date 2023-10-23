package com.influence.domain.follow;

import org.springframework.data.jpa.repository.JpaRepository;



public interface FollowRepository extends JpaRepository<Follow, Long> {

	Follow findByFollowerIdAndFollowingId(Long followerId, Long followingId);

}
