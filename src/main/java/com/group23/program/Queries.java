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
	
	//metode for å fylle inn i Apparat tabellen med id, navn og beskrivelse
	public static void setApparat(Integer id,String navn, String beskrivelse) {
		try {
			//kobler til databasen
			String url = "jdbc:mysql://mysql.stud.ntnu.no:3306/didris_db";
			Connection conn = DriverManager.getConnection(url, "didris_db", "1234");
			//lager et statement for å fylle inn informasjon
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
	public static void setNotat(int id, int treningsøkt, String treningsformål, String opplevelse) {
		try {
			String url = "jdbc:mysql://mysql.stud.ntnu.no:3306/didris_db";
			Connection conn = DriverManager.getConnection(url, "didris_db", "1234");
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Apparat(id,navn,beskrivelse) VALUES(?,?,?)");
			stmt.setInt(1, id);
			stmt.setInt(2, treningsøkt);
			stmt.setString(3, treningsformål);
			stmt.setString(4, opplevelse);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	//se over
	public static void setResultat(int treningsøkt, int øvelse, int kilo, int sett, int reps, String informasjon) {
		try {
			String url = "jdbc:mysql://mysql.stud.ntnu.no:3306/didris_db";
			Connection conn = DriverManager.getConnection(url, "didris_db", "1234");
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Apparat(id,navn,beskrivelse) VALUES(?,?,?)");
			stmt.setInt(1, treningsøkt);
			stmt.setInt(2, øvelse);
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
	public static void setTreningsøkt(int id, Date dato, int varighet, String informasjon, String personligFrom, String prestasjon) {
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
	public static void setØvelse(int id, String navn) {
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
	public static void setØvelsePåApparat(int øvelseID, int apparat, String bruksinformasjon) {
		try {
			String url = "jdbc:mysql://mysql.stud.ntnu.no:3306/didris_db";
			Connection conn = DriverManager.getConnection(url, "didris_db", "1234");
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Apparat(id,navn,beskrivelse) VALUES(?,?,?)");
			stmt.setInt(1, øvelseID);
			stmt.setInt(2, apparat);
			stmt.setString(3, bruksinformasjon);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	//se over
	public static void setØvelseGruppe(int id, String navn) {
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
	public static void setØvelsUtenApparat(int øvelseID, String beskrivelse) {
		try {
			String url = "jdbc:mysql://mysql.stud.ntnu.no:3306/didris_db";
			Connection conn = DriverManager.getConnection(url, "didris_db", "1234");
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Apparat(id,navn,beskrivelse) VALUES(?,?,?)");
			stmt.setInt(1, øvelseID);
			stmt.setString(2, beskrivelse);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
