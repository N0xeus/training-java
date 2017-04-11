package persistence;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Company;
import model.Computer;

public class DAOTest {
	private List<DAO<?>> DAOList;
	
	@Before
	public void init() {
		DAOList = new ArrayList<>();
		DAOList.add(new CompanyDAO());
		DAOList.add(new ComputerDAO());
	}
	
	@After
	public void end() {
		DAOList.removeAll(DAOList);
		DAOList = null;
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void DAOSameConnection() {
		assertSame(DAOList.get(0).connection, DAOList.get(1).connection);
	}
	
	@Test
	public void ComputerDAOFindNotNull() throws SQLException {
		assertNotNull(DAOList.get(1).findById(1l));
	}
	
	@Test
	public void ComputerDAOFind() throws SQLException {
		//Apple Inc. --> 1
		//Apple III. --> 12
		Computer c = (Computer) DAOList.get(1).findById(12l);
		Computer c1 = new Computer(12l, "ELF II", new Company(4l, "Netronics"), Date.valueOf("1977-1-1"));
		Computer c2 = new Computer(12l, "Apple III", new Company(1l, "Apple Inc."), Date.valueOf("1980-05-01"));
		assertNotEquals(c1, c);
		assertEquals(c2, c);
	}

	@Test
	public void ComputerDAOFindAll() throws SQLException {
		//SELECT COUNT(*) FROM computer --> 574
		assertEquals(574, DAOList.get(1).findAll().size());
	}
	
	@Test
	public void ComputerDAOFindBounded() {
		ComputerDAO dao = (ComputerDAO) DAOList.get(1);
		
		assertEquals(5, dao.findAll(5, 100).size());
		assertTrue(dao.findAll(0, 100).isEmpty());
		assertTrue(dao.findAll(5, 1000).isEmpty());
	}
	
	@Test public void ComputerDAOInsertAndDelete() throws SQLException {
		ComputerDAO dao = (ComputerDAO) DAOList.get(1);
		assertNotNull(dao.create(new Computer(777l, "Test", new Company())));
		assertNull(dao.create(new Computer(777l, "Test", new Company())));	
		assertNotNull(dao.findById(777l));	

		assertTrue(dao.delete(new Computer(777l, "Test", new Company())));
		assertFalse(dao.delete(new Computer(777l, "Test", new Company())));
		assertNull(dao.findById(777l));
	}
	
	@Test
	public void CompanyDAOUpdate() {
		//| 574 | iPhone 4S | 2011-10-14 00:00:00 | NULL         |          1 |
		//| 1 | Apple Inc. |
		ComputerDAO dao = (ComputerDAO) DAOList.get(1);
		Computer c, c2, c3;
		
		assertNull(dao.update(new Computer(777l, "", new Company())));
		c = dao.findById(574l);
		assertNotNull(dao.update(new Computer(574l, "", new Company(1l, "Apple Inc."))));
		c2 = dao.findById(574l);		
		assertNotNull(dao.update(new Computer(574l, "iPhone 4S", new Company(1l, "Apple Inc."), Date.valueOf("2011-10-14"))));
		c3 = dao.findById(574l);
		assertEquals(c, c3);
		assertNotEquals(c, c2);
	}
	
	@Test
	public void CompanyDAOFindAll() throws SQLException {
		//SELECT COUNT(*) FROM company --> 42
		assertEquals(42, DAOList.get(0).findAll().size());
	} 
}
