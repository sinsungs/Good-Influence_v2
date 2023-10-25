package com.influence.domain.post.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.influence.domain.post.service.PostReviewService;
import com.influence.global.S3UploadService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/postreview")
@RequiredArgsConstructor
public class PostReviewController {
	
    private final PostReviewService postReviweService;
    private final S3UploadService s3UploadService;
    
//    @PostMapping("/register")
//    public  ResponseEntity<PostResponseDTO> createPost(@RequestPart PostRequestDTO dto, @RequestPart("file") MultipartFile file, 
//    		Authentication authentication) throws IOException {
//    	
//        // 파일 업로드 로직 추가
//        String uploadedFileName = s3UploadService.saveFile(file);
//
//    	Long pno = postService.createPost(dto, uploadedFileName, authentication);
//    	
//    	dto.setPno(pno);
//    	
//    	PostResponseDTO postResponseDTO = postInfluencerService.createInfluencerPost(dto);
////    	List<PostResponseDTO> postResponseDTO = postInfluencerService.createInfluencerPost(dto);
//        
//        return ResponseEntity.ok(postResponseDTO);
//    }
//    
//    
//    
//    @GetMapping("/list")
//    public ResponseEntity<List<PostResponseDTO>> listPost() {
//    	
//        List<PostResponseDTO> response = postInfluencerService.getList();
//        
//        return ResponseEntity.ok(response);
//        
//    }
//    
//    @DeleteMapping("/delete/{ino}/{pno}")
//    public  ResponseEntity<String> deletePost(@PathVariable Long ino, @PathVariable Long pno) {
//    	
//        boolean deleted = postInfluencerService.deleteInfluencerPost(ino, pno);
//        
//        if (deleted) {
//        	
//            return ResponseEntity.ok("삭제되었습니다.");
//            
//        } else {
//        	
//            return ResponseEntity.ok("실패했습니다.");
//            
//        }
//        
//    }
//    
    
//    @DeleteMapping("/delete/{meetId}")
//    public ResponseEntity<String> deleteMeet(@PathVariable Long meetId, Authentication authentication) {
//    	
//        boolean deleted = meetService.deleteMeet(meetId);
//        
//        String result = "삭제했습니다.";
//        
//        if (deleted) {
//            return ResponseEntity.ok(result);
//        }
//        
//        return ResponseEntity.notFound().build();
//    }
    
    
}
    
    