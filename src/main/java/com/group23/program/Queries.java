package com.group23.program;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.management.Query;

public class Queries extends Database {

	public Queries(String address, String username, String password) {
		super(address, username, password);
		// TODO Auto-generated constructor stub
	}
	
	//Lager metoder for å fylle inn informasjon i databasen
	//Metode for å fylle inn i Apparat tabellen med id, navn og beskrivelse
	public void setApparat(Integer id,String navn, String beskrivelse) {
		try {
			//kobler til databasen
			Connection conn = getConnection();
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
	public void setNotat(int id, int treningsøkt, String treningsformål, String opplevelse) {
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Notat(id, treningsøkt, treningsformål, opplevelse) "
					+ "VALUES(?,?,?,?)");
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
	public void setResultat(int treningsokt, int ovelse, int kilo, int sett, int reps, String informasjon) {
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Resultat(treningsokt, ovelse, kilo, sett, reps, informasjon) "
					+ "VALUES(?,?,?,?,?,?)");
			stmt.setInt(1, treningsokt);
			stmt.setInt(2, ovelse);
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
	public void setTreningsokt(int id, Date dato, int varighet, String informasjon, String personligForm, String prestasjon) {
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Treningsokt(id, dato, varighet, informasjon, personligForm, prestasjon) "
					+ "VALUES(?,?,?,?,?,?)");
			stmt.setInt(1, id);
			stmt.setDate(2, dato);
			stmt.setInt(3, varighet);
			stmt.setString(4, informasjon);
			stmt.setString(5, personligForm);
			stmt.setString(6, prestasjon);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	//se over
	public void setOvelse(int id, String navn) {
		try {
			String url = "jdbc:mysql://mysql.stud.ntnu.no:3306/didris_db?useSSL=false";
			Connection conn = DriverManager.getConnection(url, "didris_db", "1234");
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Ovelse(id, navn) VALUES(?,?)");
			stmt.setInt(1, id);
			stmt.setString(2, navn);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	//se over
	public void setOvelsePaaApparat(int ovelseID, int apparat, String bruksinformasjon) {
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO OvelsePaaApparat(0velseID, apparat, bruksinformasjon) "
					+ "VALUES(?,?,?)");
			stmt.setInt(1, ovelseID);
			stmt.setInt(2, apparat);
			stmt.setString(3, bruksinformasjon);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	//se over
	public void setOvelsesGruppe(int id, String navn) {
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO OvelsesGruppe(id, navn) VALUES(?,?)");
			stmt.setInt(1, id);
			stmt.setString(2, navn);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	//se over
	public void setOvelseUtenApparat(int øvelseID, String beskrivelse) {
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO OvelseUtenApparat(øvelseID, beskrivelse) VALUES(?,?)");
			stmt.setInt(1, øvelseID);
			stmt.setString(2, beskrivelse);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	//Lager metoder for å hente ut informasjonen
	public ArrayList<Apparat> getApparat() {
		try {
			ArrayList<Apparat> apparatListe = new ArrayList<>();
			
			Connection conn = getConnection();
			String query = "SELECT id, navn, beskrivelse FROM Apparat";
			PreparedStatement getApparat = conn.prepareStatement(query);
			ResultSet results = getApparat.executeQuery();
			while(results.next()) {
				int id = results.getInt("id");
				String navn = results.getString("navn");
				String beskrivelse = results.getString("beskrivelse");
				apparatListe.add(new Apparat(id, navn,beskrivelse));
			}
			return apparatListe;
			
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		
		return null;
	}
	
	public String GetNotat() {
		
		return null;
	}
	
	public String GetResultat() {
		return "";
	}
	
	public String GetTreningsøkt() {
		return "";
	}
	
	public String GetØvelse() {
		return "";
	}
	
	public String GetØvelsePåApparat() {
		return "";
	}
	
	public String GetØvelsesGruppe() {
		return "";
	}
	
	public String GetØvelseUtenApparat() {
		return "";
	}
	
	//Main metode for å kjøre koden
	public static void main(String[] args) {
		//Får ikke koblet til databasen
		//Fikse SSL problem
		Queries queries = new Queries("jdbc:mysql://mysql.stud.ntnu.no:3306/didris_db", "didris_db", "1234");
		System.out.println("Object made!");
		//queries.setØvelseUtenApparat(1, "Tar litt push-ups da vell");
	}

}
