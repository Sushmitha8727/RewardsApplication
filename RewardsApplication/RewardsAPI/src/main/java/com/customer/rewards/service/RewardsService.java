package com.customer.rewards.service;

import java.util.List;

import com.customer.rewards.dto.CustomerRewardsDTO;
import com.customer.rewards.model.Rewards;

public interface RewardsService {

	CustomerRewardsDTO getCustomerRewards(String customerName);
	void caluclateRewards(List<Rewards> list);
}
