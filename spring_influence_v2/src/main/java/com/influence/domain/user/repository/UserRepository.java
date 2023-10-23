package com.influence.domain.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.influence.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
	
//	User findByUsernameAndPassword(String username, String password);

	Optional<User> findByEmail(String email);

}
