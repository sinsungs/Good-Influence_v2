package com.influence.domain.follow.service;

import org.springframework.stereotype.Service;

import com.influence.domain.follow.dto.FollowRequestDTO;
import com.influence.domain.follow.entity.Follow;
import com.influence.domain.follow.repository.FollowRepository;
import com.influence.domain.user.entity.User;
import com.influence.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class FollowService {

	private final FollowRepository followRepository;
	private final UserRepository userRepository;
	
    public void followUser(FollowRequestDTO followRequest) {
    	
    	System.out.println(followRequest+" Service단계");
    	
        User follower = userRepository.findById(followRequest.getFollowerId()).orElse(null);
        User following = userRepository.findById(followRequest.getFollowingId()).orElse(null);
        
        if (follower != null && following != null) {
            Follow follow = new Follow();
            follow.setFollower(follower);
            follow.setFollowing(following);
            followRepository.save(follow);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
    
    public void unfollowUser(FollowRequestDTO followRequest) {
    	
        Long followerId = followRequest.getFollowerId();
        Long followingId = followRequest.getFollowingId();
        
        Follow follow = followRepository.findByFollowerIdAndFollowingId(followerId, followingId);
        
        System.out.println(follow);

        if (follow != null) {
            followRepository.delete(follow);
        } else {
            throw new IllegalArgumentException("Follow relationship not found");
        }
    }
    
}
