package com.customer.rewards.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.customer.rewards.model.Rewards;

@Repository
public interface RewardsRepository extends CrudRepository<Rewards, Long>{

	List<Rewards> findByCustomerName(String customerName);
}
