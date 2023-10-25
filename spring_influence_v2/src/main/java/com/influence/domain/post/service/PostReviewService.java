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
import com.influence.domain.post.repository.PostRepository;
import com.influence.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Service
@RequiredArgsConstructor
@Log4j2
public class PostReviewService{

	private final InfluencerRepository influencerRepository;
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	
	
//      public PostResponseDTO createInfluencerPost(PostRequestDTO dto) {
//    	  
//    	  List<Post> postInfluencers = dtoToEntity(dto);
//        
//    	  postRepository.saveAll(postInfluencers);
//        
//        return entityToDTO(postInfluencers.get(0));
//        
//    }
//      
//      
//     public boolean deleteInfluencerPost(Long ino, Long pno) {
//    	 
//    	 
//	        Influencer influencer = influencerRepository.findById(ino).orElse(null);
//	        Post post = postRepository.findById(pno).orElse(null);
//
//    	 	Post postInfluencers = postRepository.findByInfluencerAndPost(influencer, post);
//
//	        if (postInfluencers == null) {
//	            return false;
//	        }
//	        
//	        postRepository.delete(postInfluencers);
//         
//		return true;
//	}
//      
//     
//     public boolean deleteInfluencer(Long id) {
//         // Check if influencer exists
//         Influencer existingInfluencer = influencerRepository.findById(id).orElse(null);
//
//         if (existingInfluencer != null) {
//             // Delete the influencer
//             influencerRepository.delete(existingInfluencer);
//             return true;
//         } else {
//             return false;
//         }
//     }
//    
//    public List<PostResponseDTO> getList() {
//    	
//        List<Post> postInfluencers = postRepository.findAll();
//        
//        List<PostResponseDTO> dtoList = new ArrayList<>();
//        
//        for (Post postInfluencer : postInfluencers) {
//            dtoList.add(entityToDTO(postInfluencer));
//        }
//        
//        return dtoList;
//    }
//    
//    
////    
////    	PostInfluencer dtoToEntity(PostRequestDTO dto) {
////    		
////	        Post post = postRepository.findById(dto.getPno()).orElseThrow(() -> new RuntimeException("Post not found"));
////	        Influencer influencer = influencerRepository.findById(dto.getIno()).orElseThrow(() -> new RuntimeException("Influencer not found"));
////		    
////	        
////	        PostInfluencer postInfluencer = PostInfluencer.builder()
////	                .post(post)
////	                .influencer(influencer)
////	                .build();
////	        
////	        return postInfluencer;
////    }
//    
//    
//		private List<Post> dtoToEntity(PostRequestDTO dto) {
//		
//	    	Post post = postRepository.findById(dto.getPno()).orElseThrow(() -> new RuntimeException("Post not found"));
//	        
//	    	List<Long> influencerIds = Arrays.asList(dto.getIno(), dto.getSecondino(), dto.getThirdino());
//	    	
//	        List<Post> postInfluencers = influencerIds.stream()
//	                .map(influencerId -> createPostInfluencer(post, influencerId))
//	                .collect(Collectors.toList());
//	        
//	    return postInfluencers;
//        
//		}
//    		
//		private Post createPostInfluencer(Post post, Long influencerId) {
//			
//		    Influencer influencer = influencerRepository.findById(influencerId).orElseThrow(() -> new RuntimeException("Influencer not found"));
//	
//		    return Post.builder()
//		            .post(post)
//		            .influencer(influencer)
//		            .build();
//		}
//		
//
//	
//    	
//    	private PostResponseDTO entityToDTO(Post postInfluencer) {
//    		
//    		PostResponseDTO postResponseDTO = PostResponseDTO.builder()
//    			
//    		   .pno(postInfluencer.getPost().getPno())
//    		   .ino(postInfluencer.getInfluencer().getIno())
//			   .title(postInfluencer.getPost().getTitle())
//	           .content(postInfluencer.getPost().getContent())
//	           .name(postInfluencer.getInfluencer().getName())
//	           .imageurl(postInfluencer.getPost().getImageUrl())
////	           .influencerContent(postInfluencer.getInfluencer().getContent())
//	            
//			   .build();
//    		
//    		return postResponseDTO;
//    		
//    	}



    	
}
