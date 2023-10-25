package com.influence.domain.meetuser.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.influence.domain.meetuser.dto.MeetUserDTO;
import com.influence.domain.meetuser.service.MeetUserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/meeting")
@RequiredArgsConstructor
public class MeetUserController {

    private final MeetUserService meetuserService;
	
 
    @PostMapping("/register/{id}")
    public ResponseEntity<String> registerMeeting(@PathVariable Long id, @RequestBody MeetUserDTO dto,  Authentication authentication) {
    	
    	String result = meetuserService.registerMeet(id, dto, authentication);
        
        return ResponseEntity.ok(result);
    }
    

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMeeting(@PathVariable Long id, MeetUserDTO dto,  Authentication authentication) {
    	
        String result = meetuserService.deleteMeeting(id, dto, authentication);
        
        return ResponseEntity.ok(result);
    }
    
    
}
