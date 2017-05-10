package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.impl.ComputerDAOImpl;

public enum ComputerService {
	INSTANCE;
	
    private int count;

    /**
     * Constructor.
     */
    private ComputerService() {
        this.count = ComputerDAOImpl.INSTANCE.getCount();
    }

    /**
     * Inserts the computer.
     * @param computer computer to insert
     * @return inserted computer
     */
    public Computer create(Computer computer) {
        count++;
        try {
			return ComputerDAOImpl.INSTANCE.create(computer);
		} catch (Exception e) {
			count--;
			throw e;
		}
    }

    /**
     * Deletes the computer corresponding to the identifier.
     * @param id identifier
     */
    public void delete(long id) {
    	ComputerDAOImpl.INSTANCE.delete(id);
        count--;
    }

    /**
     * Deletes the computers corresponding to the identifiers.
     * @param idsList identifiers
     */
    public void deleteList(List<Long> idsList) {
    	ComputerDAOImpl.INSTANCE.delete(idsList);
        count -= idsList.size();
    }

    /**
     * Updates the computer corresponding to the identifier after conversion.
     * @param computer computer to modify
     */
    public void update(Computer computer) {
    	ComputerDAOImpl.INSTANCE.update(computer);
    }

    /**
     * Gets the details of the computer corresponding to the identifier.
     * @param id identifier
     * @return computer
     */
    public Computer getDetails(long id) {
        return ComputerDAOImpl.INSTANCE.findById(id);
    }
    
    /**
     * Gets the total number of computers.
     * @return total number of computers
     */
    public int getCount(){
        return count;
    }
    
    /**
     * Gets the page of computers.
     * @param number number of the page
     * @param maxPerPage maximum of items
     * @return
     */
    public Page<Computer> getPage(int number, int maxPerPage) {
        return new Page<>(number, ComputerDAOImpl.INSTANCE.findAll(maxPerPage, maxPerPage * (number - 1)));
    }
    
    public Page<Computer> getFilteredByNamePage(int number, int maxPerPage, String name){
        return new Page<>(number, ComputerDAOImpl.INSTANCE.getFilteredByName(maxPerPage, maxPerPage * (number - 1), name));
    }
}