package com.influence.domain.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.influence.domain.comment.entity.Comment;
import com.influence.domain.post.entity.PostReview;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostPrno(Long prno);

}
