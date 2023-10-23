package com.influence.domain.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.influence.domain.meet.entity.Meet;
import com.influence.domain.orders.entity.Orders;
import com.influence.domain.user.entity.User;


public interface OrdersRepository extends JpaRepository<Orders, Long> {

	void deleteByUserAndMeet(User user, Meet meet);

    
}
	

