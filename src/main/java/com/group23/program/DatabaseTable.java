package com.group23.program;

/**
 * Class for handling generic database access for tables with autoincrementet primary key if type int
 */
public abstract class DatabaseTable {

    /**
     * id = 0 is treated as unitialized
     */
    protected int id;

    /**
     * Get the database id (primary key) of the table
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Default constructor for unitialized DatabaseTable
     */
    public DatabaseTable() {
        this.id = 0;
    }

    /**
     *
     * @return true if the instance is downloaded from the database, false if it is initialized offline
     */
    public boolean isDownloadedFromDatabase() {
        return (this.id != 0);
    }
}
