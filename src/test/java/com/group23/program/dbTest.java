package com.group23.program;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.sql.SQLException;
import java.util.ArrayList;

public class dbTest extends TestCase  {

    private static String db_addr = "jdbc:mysql://mysql.stud.ntnu.no:3306/didris_3?ssl=false";
    private static String db_user = "didris_db";
    private static String db_pw = "1234";

    public void testConnection() throws SQLException {
        Queries db = new Queries(db_addr, db_user,db_pw);
    }

    public void testApparat() throws SQLException {
        Queries db = new Queries(db_addr, db_user,db_pw);

        Apparat a = new Apparat("TEST: Navn_på_apparat", "TEST: beskrivelse_på_apparat");
        int id = db.create(a);
        ArrayList<Apparat> as = db.getApparat();
        for (Apparat e : as) {
            System.out.println(e.navn);
        }
    }

    public void testTreningsOkt() throws SQLException {
        Queries db = new Queries(db_addr, db_user,db_pw);

        Treningsokt tr = new Treningsokt("2017:03:17", 3, "informasjon", 1, 1);

        int id = db.create(tr);
    }
}
