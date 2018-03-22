package com.group23.program;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class dbTest extends TestCase  {
    public void testConnection() {
        Database db = new Database("jdbc:mysql://mysql.stud.ntnu.no:3306/didris_db",
                "didris_db","1234");
    }
}
