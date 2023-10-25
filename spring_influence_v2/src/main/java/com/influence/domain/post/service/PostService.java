package com.influence.domain.post.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.influence.domain.post.dto.PostRequestDTO;
import com.influence.domain.post.entity.Post;
import com.influence.domain.post.repository.PostRepository;
import com.influence.domain.user.entity.User;
import com.influence.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostService {
	
	private final PostRepository postRepository;
	private final UserRepository userRepository;

    

		public Long createPost(PostRequestDTO dto, String uploadedFileName, Authentication authentication) {
		
		dto.setWriter(authentication.getName());
	
		User user = userRepository.findByEmail(dto.getWriter()).orElse(null);
		
	    Post post = new Post();
	    post.setTitle(dto.getTitle());
	    post.setContent(dto.getContent());
	    post.setImageUrl(uploadedFileName);
	    post.setUser(user);
	    
	    postRepository.save(post);
	    
	    Long postId = post.getPno();
	    
	    return postId;
	}
    
}