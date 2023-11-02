package com.influence.test;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.influence.domain.user.entity.User;
import com.influence.domain.user.repository.UserRepository;

@SpringBootTest
public class Kss05RepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
//	@Test
//	public void Join() {
//		
//		IntStream.rangeClosed(1, 100).forEach(i -> {
//			
////			long cno = (long)(Math.random() * 10) + 1;
//			
//			User user = User.builder()
//					.bname("제목타이름..." + (i%5))
//					.bauthor("내용..." + (i%8))
//	                .bpulicationyear(LocalDateTime.now())
//					.bpublisher("내용..." + (i%8))
//					.category(kss05bookcategory)
//					.build();
//			
//			userRepository.save(user);
//			
//			
//		});
//	}
//	
}
