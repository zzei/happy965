package com.zei.happy;

import com.zei.happy.domain.Company;
import com.zei.happy.service.CompanyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Company8005ApplicationTests {

	@Autowired
	CompanyService companyService;

	@Test
	public void contextLoads() {
		for(int i = 0;i<50;i++){
			Company company = new Company().setName("A"+i).setCreateUser(2).setType(1).setTags("AAAAA"+i+"BBB").setCreateTime(new Date()).setState(0);
			companyService.save(company);
		}

	}

}
