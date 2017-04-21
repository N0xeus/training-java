package com.excilys.cdb.persistences;

import java.util.List;

import com.excilys.cdb.models.Computer;

public interface ComputerDAO {
    /**
     * Inserts a computer in DB.
     * @param computer computer to insert
     * @return inserted computer with its identifier
     */
    Computer create(Computer computer);

    /**
     * Finds the computer corresponding to the identifier.
     * @param id identifier
     * @return found computer
     */
    Computer findById(long id);

    /**
     * Finds all computers in DB.
     * @return list of all computers
     */
    List<Computer> findAll();

    /**
     * Finds all computers an interval fixed by limit and offset parameters.
     * @param limit limit size of the list returned
     * @param offset start of the search
     * @return list of all computers in the interval
     */
    List<Computer> findAll(int limit, int offset);

    /**
     * Updates the computer corresponding to the identifier.
     * @param computer modified computer
     */
    void update(Computer computer);

    /**
     * Deletes the computer corresponding to the identifier.
     * @param id identifier
     */
    void delete(long id);
    
    /**
     * Gets the total number of computers
     * @return total number of computers
     */
    int getCount();
}
