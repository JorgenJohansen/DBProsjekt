package com.group23.program;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.management.Query;
/*
public class Queries extends Database {


	public Queries(String address, String username, String password) {
		super(address, username, password);
		// TODO Auto-generated constructor stub
	}

	//Lager metoder for � fylle inn informasjon i databasen
	//Metode for � fylle inn i Apparat tabellen med id, navn og beskrivelse
	public static void setApparat(Integer id,String navn, String beskrivelse) {
		try {
			//kobler til databasen
			String url = "jdbc:mysql://mysql.stud.ntnu.no:3306/didris_db?useSSL=false";
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
			String url = "jdbc:mysql://mysql.stud.ntnu.no:3306/didris_db?useSSL=false";
			Connection conn = DriverManager.getConnection(url, "didris_db", "1234");
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Notat(id, trenings�kt, treningsform�l, opplevelse) "
					+ "VALUES(?,?,?,?)");
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
	public void setTreningsokt(int id, String dato, int varighet, String informasjon, String personligForm, String prestasjon) {
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Treningsokt(id, dato, varighet, informasjon, personligForm, prestasjon) "
					+ "VALUES(?,?,?,?,?,?)");
			stmt.setInt(1, id);
			stmt.setString(2, dato);
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
	public static void setOvelseUtenApparat(int �velseID, String beskrivelse) {
		try {
			String url = "jdbc:mysql://mysql.stud.ntnu.no:3306/didris_db?useSSL=false";
			Connection conn = DriverManager.getConnection(url, "didris_db", "1234");
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO OvelseUtenApparat(�velseID, beskrivelse) VALUES(?,?)");
			stmt.setInt(1, �velseID);

			stmt.setString(2, beskrivelse);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	//Lager metoder for � hente ut informasjonen
	public Apparat getApparat() {
		Apparat apparat = new Apparat();
		try {
			Connection conn = getConnection();
			ArrayList<Apparat> apparatListe = new ArrayList<>();
			String query = "SELECT * FROM Apparat";
			PreparedStatement getApparat = conn.prepareStatement(query);
			ResultSet results = getApparat.executeQuery();
			while(results.next()) {
				int id = results.getInt("id");
				String navn = results.getString("navn");
				String beskrivelse = results.getString("beskrivelse");
				apparatListe.add(new Apparat(id, navn,beskrivelse));
			}
			return apparatListe;
			

			//while(apparat.next()) {
			//	array.add(apparat.getString("navn, beskrivelse"));
			//}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		
		return null;
	}
	//setNotat(int id, int treningsokt, String treningsformaal, String opplevelse)
	public ArrayList<Notat> GetNotat() {
		try {
			Connection conn = getConnection();
			ArrayList<Notat> notatListe = new ArrayList<>();
			String query = "SELECT * FROM Notat";
			PreparedStatement getNotat = conn.prepareStatement(query);
			ResultSet results = getNotat.executeQuery();
			while(results.next()) {
				int id = results.getInt("id");
				int treningsokt = results.getInt("treningsokt");
				String treningsformaal = results.getString("treningsformaal");
				String opplevelse = results.getString("opplevelse");
				notatListe.add(new Notat(id, treningsokt,treningsformaal, opplevelse));
			}
			return notatListe;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	//setResultat(int treningsokt, int ovelse, int kilo, int sett, int reps, String informasjon)
	public ArrayList<Resultat> GetResultat() {
		try {
			Connection conn = getConnection();
			ArrayList<Resultat> resultatListe = new ArrayList<>();
			String query = "SELECT * FROM Resultat";
			PreparedStatement getResultat = conn.prepareStatement(query);
			ResultSet results = getResultat.executeQuery();
			while(results.next()) {
				int treningsokt = results.getInt("treningsokt");
				int ovelse = results.getInt("ovelse");
				int kilo = results.getInt("kilo");
				int sett = results.getInt("sett");
				int reps = results.getInt("reps");
				String informasjon = results.getString("informasjon");
				resultatListe.add(new Resultat(treningsokt, ovelse,kilo, sett, reps, informasjon));
			}
			return resultatListe;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	public String GetTrenings�kt() {
		return "";
	}
	
	public String Get�velse() {
		return "";
	}
	
	public String Get�velseP�Apparat() {
		return "";
	}
	
	public String Get�velsesGruppe() {
		return "";
	}
	
	public String Get�velseUtenApparat() {
		return "";

	}
	
	//Main metode for � kj�re koden
	public static void main(String[] args) {
		//F�r ikke koblet til databasen
		//Fikse SSL problem
		Queries queries = new Queries("jdbc:mysql://mysql.stud.ntnu.no:3306/didris_db?useSSL=false", "didris_db", "1234");
		System.out.println("Object made!");
		//queries.set�velseUtenApparat(1, "Tar litt push-ups da vell");
	}

}
*/