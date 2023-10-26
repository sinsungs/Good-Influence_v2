package com.influence.domain.post.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.influence.domain.post.dto.PostResponseDTO;
import com.influence.domain.post.dto.PostReviewDTO;
import com.influence.domain.post.entity.PostInfluencer;
import com.influence.domain.post.entity.PostReview;
import com.influence.domain.post.mapper.PostReviewMapper;
import com.influence.domain.post.repository.PostReviewRepository;
import com.influence.domain.user.entity.User;
import com.influence.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Service
@RequiredArgsConstructor
@Log4j2
public class PostReviewService{
	
	private final PostReviewRepository postReviewRepository;
	private final UserRepository userRepository;
	private final PostReviewMapper postReviewMaper;
	
	
    // 소셜 모임 후기 게시글 작성 
	public Boolean createPost(PostReviewDTO dto, String uploadedFileName, Authentication authentication) {
		
		dto.setWriter(authentication.getName());
	
		User user = userRepository.findByEmail(dto.getWriter()).orElse(null);
		
		PostReview postReview = PostReview.builder()
	            .title(dto.getTitle())
	            .content(dto.getContent())
	            .imageUrl(uploadedFileName)
	            .user(user)
	            .build();
	    
	    postReviewRepository.save(postReview);
	    
	    return true;
}


    // 인플루언서 추천 게시글 전체 조회
    public List<PostReviewDTO> getList() {
   	
       List<PostReview> PostReviews = postReviewRepository.findAll();
       
       List<PostReviewDTO> dtoList = new ArrayList<>();
       
       for (PostReview postReview : PostReviews) {
           dtoList.add(postReviewMaper.entityToDTO(postReview));
       }
       
       return dtoList;
   }
    
    
   // 인플루언서 추천 게시글 삭제 
//   public boolean deleteInfluencerPost(Long ino, Long pno) {
//  	 
//  	 
//	        Influencer influencer = influencerRepository.findById(ino).orElse(null);
//	        Post post = postRepository.findById(pno).orElse(null);
//
//  	 	PostInfluencer postInfluencers = postInfluencerRepository.findByInfluencerAndPost(influencer, post);
//
//	        if (postInfluencers == null) {
//	            return false;
//	        }
//	        
//	        postInfluencerRepository.delete(postInfluencers);
//       
//		return true;
//	}

	
	
    	
}
