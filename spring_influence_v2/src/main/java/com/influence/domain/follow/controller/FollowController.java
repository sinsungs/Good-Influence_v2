package com.influence.domain.follow.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.influence.domain.follow.dto.FollowRequestDTO;
import com.influence.domain.follow.service.FollowService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FollowController {

	private final FollowService followService;
	
	@PostMapping("/follow")
	public ResponseEntity<String> followUser(@RequestBody FollowRequestDTO followRequest) {
		
		System.out.println(followRequest);
		
	    followService.followUser(followRequest);
	    
	    return ResponseEntity.ok("팔로우 성공");
	}
	
    @DeleteMapping("/unfollow")
    public ResponseEntity<String> unfollowUser(@RequestBody FollowRequestDTO followRequest) {
    	
        followService.unfollowUser(followRequest);
        
        return ResponseEntity.ok("언팔로우 성공");
    }
	
}
