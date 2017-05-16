package com.excilys.cdb.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={com.excilys.cdb.config.SpringConfig.class})
public class CompanyServiceTest {
    @Autowired
	private CompanyService companyService;
    
    @Autowired
	private ComputerService computerService;
	
	@Test
	public void createAndDelete() {
		Company com = new Company.Builder().name("Bob Inc.").build();
		
		com = companyService.create(com);
		companyService.delete(com.getId());
	}
	
	@Test
	public void deleteWithComputer() {		
		Company com = new Company.Builder().name("Bob Inc.").build();
		
		com = companyService.create(com);
		Computer cpu = new Computer.Builder()
				.name("Bob")
				.manufacturer(com)
				.build();
		cpu = computerService.create(cpu);
		assertNotNull(computerService.getDetails(cpu.getId()));
		companyService.delete(com.getId());
		assertNull(computerService.getDetails(cpu.getId()));
		assertNull(companyService.getCompanyById(com.getId()));
	}
}
