package com.influence.domain.post.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.influence.domain.influencer.entity.Influencer;
import com.influence.domain.influencer.repository.InfluencerRepository;
import com.influence.domain.post.dto.PostRequestDTO;
import com.influence.domain.post.dto.PostResponseDTO;
import com.influence.domain.post.entity.Post;
import com.influence.domain.post.entity.PostInfluencer;
import com.influence.domain.post.repository.PostInfluencerRepository;
import com.influence.domain.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostInfluencerService{

	private final PostRepository postRepository;
	private final InfluencerRepository influencerRepository;
	private final PostInfluencerRepository postInfluencerRepository;
	
	
	// 인플루언서 추천 게시글 작성 
	public Boolean createInfluencerPost(PostRequestDTO dto, Long pno) {
		
		dto.setPno(pno);
  
		PostInfluencer postInfluencer = dtoToEntity(dto);

		postInfluencerRepository.save(postInfluencer);
        
        return true;
    }

     
    
     // 인플루언서 추천 게시글 전체 조회
     public List<PostResponseDTO> getList() {
    	
        List<PostInfluencer> postInfluencers = postInfluencerRepository.findAll();
        
        List<PostResponseDTO> dtoList = new ArrayList<>();
        
        for (PostInfluencer postInfluencer : postInfluencers) {
            dtoList.add(entityToDTO(postInfluencer));
        }
        
        return dtoList;
    }
     
     
     
    public boolean deleteInfluencerPost(Long ino, Long pno) {
   	 
   	 
	        Influencer influencer = influencerRepository.findById(ino).orElse(null);
	        Post post = postRepository.findById(pno).orElse(null);

   	 	PostInfluencer postInfluencers = postInfluencerRepository.findByInfluencerAndPost(influencer, post);

	        if (postInfluencers == null) {
	            return false;
	        }
	        
	        postInfluencerRepository.delete(postInfluencers);
        
		return true;
	}
     
    
    public boolean deleteInfluencer(Long id) {
        // Check if influencer exists
        Influencer existingInfluencer = influencerRepository.findById(id).orElse(null);

        if (existingInfluencer != null) {
            // Delete the influencer
            influencerRepository.delete(existingInfluencer);
            return true;
        } else {
            return false;
        }
    }
    
    
    
    	private PostInfluencer dtoToEntity(PostRequestDTO dto) {
    		
	        Post post = postRepository.findById(dto.getPno()).orElseThrow(() -> new RuntimeException("Post not found"));
	        Influencer influencer = influencerRepository.findById(dto.getIno()).orElseThrow(() -> new RuntimeException("Influencer not found"));
		    
	        
	        PostInfluencer postInfluencer = PostInfluencer.builder()
	                .post(post)
	                .influencer(influencer)
	                .build();
	        
	        return postInfluencer;
    }
    
    		
		private PostInfluencer createPostInfluencer(Post post, Long influencerId) {
			
		    Influencer influencer = influencerRepository.findById(influencerId).orElseThrow(() -> new RuntimeException("Influencer not found"));
	
		    return PostInfluencer.builder()
		            .post(post)
		            .influencer(influencer)
		            .build();
		}
		

	
    	
    	private PostResponseDTO entityToDTO(PostInfluencer postInfluencer) {
    		
    		PostResponseDTO postResponseDTO = PostResponseDTO.builder()
    			
    		   .pno(postInfluencer.getPost().getPno())
    		   .ino(postInfluencer.getInfluencer().getIno())
			   .title(postInfluencer.getPost().getTitle())
	           .content(postInfluencer.getPost().getContent())
	           .name(postInfluencer.getInfluencer().getName())
	           .imageurl(postInfluencer.getPost().getImageUrl())
//	           .influencerContent(postInfluencer.getInfluencer().getContent())
	            
			   .build();
    		
    		return postResponseDTO;
    		
    	}



    	
}