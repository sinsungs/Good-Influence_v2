package com.influence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.influence.entity.Meet;
import com.influence.entity.Orders;
import com.influence.entity.User;


public interface OrdersRepository extends JpaRepository<Orders, Long> {

	void deleteByUserAndMeet(User user, Meet meet);

    
}
	

