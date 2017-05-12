package com.excilys.cdb.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.impl.CompanyDAOImpl;
import com.excilys.cdb.persistence.impl.ComputerDAOImpl;

public class DAOTest {
	private ComputerDAOImpl computerDao = new ComputerDAOImpl();
	private CompanyDAOImpl companyDao = new CompanyDAOImpl();
	
	@Test
	public void correctComputerListSize(){
	    int max = 10;
	    
	    assertEquals(computerDao.getCount(), computerDao.findAll().size());
	    assertEquals(max, computerDao.findAll(max, 0).size());
	}
    
    @Test
    public void computerCreateAndDelete() {
        Computer c = new Computer.Builder()
                                    .name("CPU")
                                    .manufacturer(new Company.Builder().id(1L).name("CPY").build()).build();
        Computer c2;
        
        assertEquals(0l, c.getId());
        c2 = computerDao.create(c);
        assertNotEquals(0L, c2.getId());
        assertNotNull(computerDao.findById(c2.getId()));
        computerDao.delete(c2.getId());
        assertNull(computerDao.findById(c2.getId()));
    }
    
    @Test
    public void computerUpdate() {
        Computer c = new Computer.Builder()
                                .name("CPU")
                                .manufacturer(new Company.Builder().id(1L).name("CPY").build()).build();
        
        c = computerDao.create(c);
        computerDao.update(new Computer.Builder()
                                        .id(c.getId())
                                        .name("CPU2")
                                        .manufacturer(new Company.Builder().id(2L).name("CPY2").build()).build());
        assertNotEquals(c, computerDao.findById(c.getId()));
        computerDao.delete(c.getId());
    }
    
    @Test
    public void correctCompanyListSize() {
    	assertEquals(42, companyDao.findAll().size());
    }
    
    @Test
    public void companyCreateAndDelete() {
    	Company com = new Company.Builder().name("Bob Inc.").build();
    	Company com2;
    	
    	assertEquals(0L, com.getId());
    	com2 = companyDao.create(com);
    	assertNotEquals(0L, com2.getId());
    	assertNotNull(companyDao.findById(com2.getId()));
    	companyDao.delete(com2.getId());
    	assertNull(companyDao.findById(com2.getId()));
    }
}
