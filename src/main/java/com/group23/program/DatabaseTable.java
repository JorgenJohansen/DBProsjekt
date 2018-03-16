package com.group23.program;

public abstract class DatabaseTable {

    /**
     * id = 0 is treated as unitialized
     */
    private int id;

    /**
     * Default constructor for unitialized DatabaseTable
     */
    public DatabaseTable() {
        this.id = 0;
    }

    public boolean isDownloadedFromDatabase() {
        return (this.id != 0);
    }
}
