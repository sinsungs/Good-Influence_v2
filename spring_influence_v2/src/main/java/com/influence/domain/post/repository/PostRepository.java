package com.influence.domain.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.influence.domain.influencer.entity.Influencer;
import com.influence.domain.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
    @Query("SELECT COUNT(pi) FROM PostInfluencer pi")
	int countAllPost();

    @Query("SELECT COUNT(pi) FROM PostInfluencer pi")
	int recommnedRank();
    
    
    @Query("SELECT pi.influencer, COUNT(pi.influencer)" +
    		"FROM PostInfluencer pi " +
            "GROUP BY pi.influencer " +
            "ORDER BY COUNT(pi.influencer) DESC")
	List<Object[]> findTop5UsersWithMostRecommend();


//	PostInfluencer findByInfluencerAndPost(Influencer influencer, Post post);
    
    
//    @Query("SELECT mu.user FROM MeetUser mu " +
//            "GROUP BY mu.user " +
//            "ORDER BY COUNT(mu.meet) DESC")
//     List<User> findTop5UsersWithMostMeets();

}
