package com.customer.rewards.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.rewards.dto.CustomerRewardsDTO;
import com.customer.rewards.dto.RewardsDTO;
import com.customer.rewards.model.Rewards;
import com.customer.rewards.repository.RewardsRepository;
import com.customer.rewards.service.RewardsService;

@Service
public class RewardsServiceImpl implements RewardsService{
	
	@Autowired
	private RewardsRepository repositoy;

	@Override
	public CustomerRewardsDTO getCustomerRewards(String customerName) {
		List<Rewards> rewards = repositoy.findByCustomerName(customerName);
		Map<Object, Long> months = rewards.stream()
				.collect(Collectors.groupingBy(element -> element.getCreatedDate().getMonth(), Collectors.summingLong(element->element.getPoints())));

		List<RewardsDTO> rewardsDTO = new ArrayList<RewardsDTO>(); 
		Long total = 0l;
		for (Entry<Object, Long> entry : months.entrySet()) {
			RewardsDTO dto = new RewardsDTO();
			dto.setMonth(((Integer)entry.getKey()+1));
			Long monthTotal = entry.getValue();
			total += monthTotal;
			dto.setMonthPoints(monthTotal);
			
			rewardsDTO.add(dto);
		}
		
		CustomerRewardsDTO customerDetails = new CustomerRewardsDTO();
		customerDetails.setTotalPoints(total);
		customerDetails.setRewards(rewardsDTO);
		return customerDetails;
	}

	@Override
	public void caluclateRewards(List<Rewards> list) {
		for(Rewards r :  list){
			Integer points = caluclatePoints(r);
			r.setPoints(points);
			repositoy.save(r);
		}
	}

	private Integer caluclatePoints(Rewards r) {
		Integer points = 0 ;
		Integer amount = r.getPurchaseAmount();
		if(amount<=50){
			return points;
		}else if(amount<=100){
			points +=(amount-50)*1;
		}else{
			points +=50;
			points += (amount%100)*2;
		}
		
		return points;
	}

}
