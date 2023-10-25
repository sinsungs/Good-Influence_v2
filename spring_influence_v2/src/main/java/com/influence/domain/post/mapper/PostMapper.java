package com.influence.domain.post.mapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.influence.domain.influencer.entity.Influencer;
import com.influence.domain.kakao.KakaoProfile;
import com.influence.domain.post.dto.PostResponseDTO;
import com.influence.domain.post.entity.Post;
import com.influence.domain.post.entity.PostInfluencer;
import com.influence.domain.user.RoleType;
import com.influence.domain.user.dto.UserDTO;
import com.influence.domain.user.entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PostMapper {
	
    
	public PostInfluencer dtoToEntity( Post post, Influencer influencer) {
		  
        return PostInfluencer.builder()
                .post(post)
                .influencer(influencer)
                .build();
        
	}
	
	public PostResponseDTO entityToDTO(PostInfluencer postInfluencer) {
		
		return PostResponseDTO.builder()
			
		   .pno(postInfluencer.getPost().getPno())
		   .ino(postInfluencer.getInfluencer().getIno())
		   .title(postInfluencer.getPost().getTitle())
           .content(postInfluencer.getPost().getContent())
           .name(postInfluencer.getInfluencer().getName())
           .imageurl(postInfluencer.getPost().getImageUrl())
//           .influencerContent(postInfluencer.getInfluencer().getContent())
            
		   .build();
		
	}

    
    
    

}