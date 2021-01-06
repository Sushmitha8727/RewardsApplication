package com.customer.rewards.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.rewards.dto.CustomerRewardsDTO;
import com.customer.rewards.model.Rewards;
import com.customer.rewards.service.RewardsService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RewardsController {
	@Autowired
	private RewardsService rewardsService;
	
	@GetMapping("/getRewards")
	public CustomerRewardsDTO getCustomerRewards(String name) throws ParseException{
		return rewardsService.getCustomerRewards(name);
	}
	
	@GetMapping("/sampleData")
	public void createDateSet() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
		//1st customer
		Rewards c1 = new Rewards();
		c1.setCustomerName("cust2");
		c1.setPurchaseAmount(140);
		c1.setCreatedDate(sdf.parse("21/11/2020"));
		
		Rewards c2 = new Rewards();
		c2.setCustomerName("cust2");
		c2.setPurchaseAmount(70);
		c2.setCreatedDate(sdf.parse("20/11/2020"));
		
		// 2nd Customer

		Rewards r1 = new Rewards();
		r1.setCustomerName("cust1");
		r1.setPurchaseAmount(100);
		r1.setCreatedDate(sdf.parse("20/08/2020"));
		
		Rewards r2 = new Rewards();
		r2.setCustomerName("cust1");
		r2.setPurchaseAmount(120);
		r2.setCreatedDate(sdf.parse("20/09/2020"));
		
		Rewards r3 = new Rewards();
		r3.setCustomerName("cust1");
		r3.setPurchaseAmount(130);
		r3.setCreatedDate(sdf.parse("10/10/2020"));
		
		Rewards r4 = new Rewards();
		r4.setCustomerName("cust1");
		r4.setPurchaseAmount(60);
		r4.setCreatedDate(sdf.parse("11/11/2020"));
		
		List<Rewards> list = new ArrayList<>();
		list.add(r1);
		list.add(r2);
		list.add(r3);
		list.add(r4);
		list.add(c1);
		list.add(c2);
		
		rewardsService.caluclateRewards(list);
		
	}
}
