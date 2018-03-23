package com.group23.program;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.sql.SQLException;
import java.util.ArrayList;

public class dbTest extends TestCase  {

    private static String db_addr = "jdbc:mysql://mysql.stud.ntnu.no:3306/didris_3?ssl=false&useSSL=false";
    private static String db_user = "didris_db";
    private static String db_pw = "1234";

    public void testConnection() throws SQLException {
        Queries db = new Queries(db_addr, db_user,db_pw);
    }

    public void testTreningsOkt() throws SQLException {
        Queries db = new Queries(db_addr, db_user,db_pw);

        Treningsokt tr = new Treningsokt("2017:03:17", 3, "informasjon", 1, 1);

        int id = db.create(tr);
    }

    public void testOvelsePaaApparat() throws SQLException {
        Queries db = new Queries(db_addr, db_user,db_pw);

        Apparat ap = new Apparat("ApparatNavn", "ApparatBeskrivelse");

        int apparat_id = db.create(ap);

        OvelsePaaApparat o = new OvelsePaaApparat(apparat_id, "OvelseNavn", "Beskrivelse :)");

        db.create(o);

        ArrayList<OvelsePaaApparat> as = db.getOvelsePaaApparat();

        for (OvelsePaaApparat a : as) {
            System.out.println(a.navn);
        }
    }


    public void testOvelseUtenApparat() throws SQLException {
        Queries db = new Queries(db_addr, db_user,db_pw);

        OvelseUtenApparat o = new OvelseUtenApparat("OvelseNavn2", "Øvelse uten apparat");

        db.create(o);

        ArrayList<OvelseUtenApparat> as = db.getOvelseUtenApparat();

        for (OvelseUtenApparat a : as) {
            System.out.println(a.navn + a.beskrivelse);
        }
    }

}
