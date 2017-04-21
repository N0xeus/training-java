package com.excilys.cdb.persistences;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConnectorTest {
	
	@Before
	public void init() {
	
	}
	
	@After
	public void end() {
		Connector.INSTANCE.disconnect();
		System.gc();
	}
	
	@Test
	public void singleInstance() {
		Connector c = Connector.INSTANCE;
		Connector c2 = Connector.INSTANCE;
		
		assertSame(c, c2);
	}
	
	@Test
	public void sameConnection() {
		Connection c1 = Connector.INSTANCE.getConnection();
		Connection c2 = Connector.INSTANCE.getConnection();
		
		assertNotNull(c1);
		assertSame(c1, c2);
	}
	
	@Test
	public void openAndClose() {
		assertTrue(Connector.INSTANCE.isDisconnected());
		Connector.INSTANCE.getConnection();
		assertFalse(Connector.INSTANCE.isDisconnected());
		Connector.INSTANCE.disconnect();
		assertTrue(Connector.INSTANCE.isDisconnected());
	}
}
