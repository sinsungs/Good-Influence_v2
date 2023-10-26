package com.influence.domain.post.mapper;

import org.springframework.stereotype.Component;

import com.influence.domain.influencer.entity.Influencer;
import com.influence.domain.post.dto.PostReviewDTO;
import com.influence.domain.post.entity.Post;
import com.influence.domain.post.entity.PostInfluencer;
import com.influence.domain.post.entity.PostReview;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PostReviewMapper {
	
    
	public PostInfluencer dtoToEntity( Post post, Influencer influencer) {
		  
        return PostInfluencer.builder()
                .post(post)
                .influencer(influencer)
                .build();
        
	}
	
	public PostReviewDTO entityToDTO(PostReview postReview) {
		
		return PostReviewDTO.builder()
				.title(postReview.getTitle())
				.content(postReview.getContent())
				.writer(postReview.getUser())
            
		   .build();
		
	}
    Post post = Post.builder()
            .title(dto.getTitle())
            .content(dto.getContent())
            .imageUrl(uploadedFileName)
            .user(user)
            .build();
    
    
    

}