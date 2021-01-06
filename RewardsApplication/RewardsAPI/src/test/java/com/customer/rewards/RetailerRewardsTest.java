package com.customer.rewards;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.customer.rewards.dto.CustomerRewardsDTO;
import com.customer.rewards.dto.RewardsDTO;
import com.customer.rewards.model.Rewards;
import com.customer.rewards.service.RewardsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RetailerRewardsTest {

	@Autowired
	private RewardsService rewardsService;
	
	@Before
	public void before() throws ParseException{

	}
	
	@Test
	public void getRewardPointsForCutomer1() throws ParseException {
		customer1();
		CustomerRewardsDTO  customerDetails = rewardsService.getCustomerRewards("c1");
		assertTrue(customerDetails.getTotalPoints() == 100);
		assertTrue(customerDetails.getRewards().size() == 3);
		List<RewardsDTO> rewards = customerDetails.getRewards();
		assertTrue(rewards.get(0).getMonth() == 6);
		assertTrue(rewards.get(0).getMonthPoints() == 20);
	}
	
	private void customer1() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
		Rewards r1 = new Rewards();
		r1.setCustomerName("c1");
		r1.setPurchaseAmount(70);
		r1.setCreatedDate(sdf.parse("10/06/2020"));
		
		Rewards r2 = new Rewards();
		r2.setCustomerName("c1");
		r2.setPurchaseAmount(80);
		r2.setCreatedDate(sdf.parse("12/07/2020"));
		
		Rewards r3 = new Rewards();
		r3.setCustomerName("c1");
		r3.setPurchaseAmount(100);
		r3.setCreatedDate(sdf.parse("10/08/2020"));
		
		Rewards r4 = new Rewards();
		r4.setCustomerName("c1");
		r4.setPurchaseAmount(40);
		r4.setCreatedDate(sdf.parse("11/08/2020"));
		List<Rewards> list = new ArrayList<>();
		list.add(r1);
		list.add(r2);
		list.add(r3);
		list.add(r4);
		rewardsService.caluclateRewards(list);
	}
	@Test
	public void getRewardPointsForCutomer2() throws ParseException{
		customer2();
		CustomerRewardsDTO  customerDetails = rewardsService.getCustomerRewards("c2");
		assertTrue(customerDetails.getTotalPoints() == 200);
		assertTrue(customerDetails.getRewards().size() == 2);
		List<RewardsDTO> rewards = customerDetails.getRewards();
		assertTrue(rewards.get(0).getMonth() == 7);
		assertTrue(rewards.get(0).getMonthPoints() == 90);
	}
	
	private void customer2() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
		// 2nd Customer
		Rewards c1 = new Rewards();
		c1.setCustomerName("c2");
		c1.setPurchaseAmount(120);
		c1.setCreatedDate(sdf.parse("13/07/2020"));
		
		Rewards c2 = new Rewards();
		c2.setCustomerName("c2");
		c2.setPurchaseAmount(130);
		c2.setCreatedDate(sdf.parse("12/08/2020"));
		
		List<Rewards> list = new ArrayList<>();
		list.add(c1);
		list.add(c2);
		
		rewardsService.caluclateRewards(list);
	}
}
