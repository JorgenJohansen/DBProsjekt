package com.group23.program;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Queries extends Database {

	public Queries(String address, String username, String password) {
		super(address, username, password);
		// TODO Auto-generated constructor stub
	}
	
	//metode for � fylle inn i Apparat tabellen med id, navn og beskrivelse
	public static void setApparat(Integer id,String navn, String beskrivelse) {
		try {
			//kobler til databasen
			String url = "jdbc:mysql://mysql.stud.ntnu.no:3306/didris_db";
			Connection conn = DriverManager.getConnection(url, "didris_db", "1234");
			//lager et statement for � fylle inn informasjon
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Apparat(id,navn,beskrivelse) VALUES(?,?,?)");
			//setter tilstanden
			stmt.setInt(1, id);
			stmt.setString(2, navn);
			stmt.setString(3, beskrivelse);
			//oppdaterer
			stmt.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
	//se over
	public static void setNotat(int id, int trenings�kt, String treningsform�l, String opplevelse) {
		try {
			String url = "jdbc:mysql://mysql.stud.ntnu.no:3306/didris_db";
			Connection conn = DriverManager.getConnection(url, "didris_db", "1234");
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Apparat(id,navn,beskrivelse) VALUES(?,?,?)");
			stmt.setInt(1, id);
			stmt.setInt(2, trenings�kt);
			stmt.setString(3, treningsform�l);
			stmt.setString(4, opplevelse);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	//se over
	public static void setResultat(int trenings�kt, int �velse, int kilo, int sett, int reps, String informasjon) {
		try {
			String url = "jdbc:mysql://mysql.stud.ntnu.no:3306/didris_db";
			Connection conn = DriverManager.getConnection(url, "didris_db", "1234");
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Apparat(id,navn,beskrivelse) VALUES(?,?,?)");
			stmt.setInt(1, trenings�kt);
			stmt.setInt(2, �velse);
			stmt.setInt(3, kilo);
			stmt.setInt(4, sett);
			stmt.setInt(5, reps);
			stmt.setString(6, informasjon);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	//se over
	public static void setTrenings�kt(int id, Date dato, int varighet, String informasjon, String personligFrom, String prestasjon) {
		try {
			String url = "jdbc:mysql://mysql.stud.ntnu.no:3306/didris_db";
			Connection conn = DriverManager.getConnection(url, "didris_db", "1234");
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Apparat(id,navn,beskrivelse) VALUES(?,?,?)");
			stmt.setInt(1, id);
			stmt.setDate(2, dato);
			stmt.setInt(3, varighet);
			stmt.setString(4, informasjon);
			stmt.setString(5, personligFrom);
			stmt.setString(6, prestasjon);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	//se over
	public static void set�velse(int id, String navn) {
		try {
			String url = "jdbc:mysql://mysql.stud.ntnu.no:3306/didris_db";
			Connection conn = DriverManager.getConnection(url, "didris_db", "1234");
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Apparat(id,navn,beskrivelse) VALUES(?,?,?)");
			stmt.setInt(1, id);
			stmt.setString(2, navn);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	//se over
	public static void set�velseP�Apparat(int �velseID, int apparat, String bruksinformasjon) {
		try {
			String url = "jdbc:mysql://mysql.stud.ntnu.no:3306/didris_db";
			Connection conn = DriverManager.getConnection(url, "didris_db", "1234");
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Apparat(id,navn,beskrivelse) VALUES(?,?,?)");
			stmt.setInt(1, �velseID);
			stmt.setInt(2, apparat);
			stmt.setString(3, bruksinformasjon);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	//se over
	public static void set�velseGruppe(int id, String navn) {
		try {
			String url = "jdbc:mysql://mysql.stud.ntnu.no:3306/didris_db";
			Connection conn = DriverManager.getConnection(url, "didris_db", "1234");
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Apparat(id,navn,beskrivelse) VALUES(?,?,?)");
			stmt.setInt(1, id);
			stmt.setString(2, navn);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	//se over
	public static void set�velsUtenApparat(int �velseID, String beskrivelse) {
		try {
			String url = "jdbc:mysql://mysql.stud.ntnu.no:3306/didris_db";
			Connection conn = DriverManager.getConnection(url, "didris_db", "1234");
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Apparat(id,navn,beskrivelse) VALUES(?,?,?)");
			stmt.setInt(1, �velseID);
			stmt.setString(2, beskrivelse);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
