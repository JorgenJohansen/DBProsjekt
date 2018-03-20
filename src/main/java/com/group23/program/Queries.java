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
	public void setNotat(int id, int treningsokt, String treningsformaal, String opplevelse) {
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Notat(id, treningsokt, treningsformaal, opplevelse) "
					+ "VALUES(?,?,?,?)");
			stmt.setInt(1, id);
			stmt.setInt(2, treningsokt);
			stmt.setString(3, treningsformaal);
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
	public void setOvelseUtenApparat(int ovelseID, String beskrivelse) {
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO OvelseUtenApparat(ovelseID, beskrivelse) VALUES(?,?)");
			stmt.setInt(1, ovelseID);
			stmt.setString(2, beskrivelse);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	
	//Lager metoder for å hente ut informasjonen
	public ArrayList<Apparat> getApparat() {
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
	//setTreningsokt(int id, String dato, int varighet, String informasjon, String personligForm, String prestasjon)
	public ArrayList<Treningsokt> GetTreningsokt() {
		try {
			Connection conn = getConnection();
			ArrayList<Treningsokt> treningsoktListe = new ArrayList<>();
			String query = "SELECT * FROM Notat";
			PreparedStatement getTreningsokt = conn.prepareStatement(query);
			ResultSet results = getTreningsokt.executeQuery();
			while(results.next()) {
				int id = results.getInt("id");
				String dato = results.getString("dato");
				int varighet = results.getInt("varighet");
				String informasjon = results.getString("informasjon");
				String personligForm = results.getString("personligform");
				String prestasjon = results.getString("prestasjon");
				treningsoktListe.add(new Treningsokt(id, dato, varighet, informasjon, personligForm, prestasjon));
			}
			return treningsoktListe;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	//setOvelse(int id, String navn)
	public ArrayList<Ovelse> GetOvelse() {
		try {
			Connection conn = getConnection();
			ArrayList<Ovelse> ovelseListe = new ArrayList<>();
			String query = "SELECT * FROM Ovelse";
			PreparedStatement getOvelse = conn.prepareStatement(query);
			ResultSet results = getOvelse.executeQuery();
			while(results.next()) {
				int id = results.getInt("id");
				String navn = results.getString("navn");
				ovelseListe.add(new Ovelse(id, navn));
			}
			return ovelseListe;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	//setOvelsePaaApparat(int ovelseID, int apparat, String bruksinformasjon)
	public ArrayList<OvelsePaaApparat> GetOvelsePåApparat() {
		try {
			Connection conn = getConnection();
			ArrayList<OvelsePaaApparat> ovelsePaaApparatListe = new ArrayList<>();
			String query = "SELECT * FROM Notat";
			PreparedStatement getOvelsePaaApparat = conn.prepareStatement(query);
			ResultSet results = getOvelsePaaApparat.executeQuery();
			while(results.next()) {
				int ovelseID = results.getInt("ovelseID");
				int apparat = results.getInt("apparat");
				String bruksinformasjon = results.getString("bruksinformasjon");
				ovelsePaaApparatListe.add(new OvelsePaaApparat(ovelseID, bruksinformasjon));
			}
			return ovelsePaaApparatListe;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	//setOvelsesGruppe(int id, String navn)
	public ArrayList<OvelsesGruppe> GetOvelsesGruppe() {
		try {
			Connection conn = getConnection();
			ArrayList<OvelsesGruppe> ovelsesGruppeListe = new ArrayList<>();
			String query = "SELECT * FROM OvelsesGruppe";
			PreparedStatement getNotat = conn.prepareStatement(query);
			ResultSet results = getNotat.executeQuery();
			while(results.next()) {
				int id = results.getInt("id");
				String navn = results.getString("navn");
				ovelsesGruppeListe.add(new OvelsesGruppe(id, navn));
			}
			return ovelsesGruppeListe;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	//setOvelseUtenApparat(int ovelseID, String beskrivelse)
	public ArrayList<OvelseUtenApparat> GetOvelseUtenApparat() {
		try {
			Connection conn = getConnection();
			ArrayList<OvelseUtenApparat> ovelseUtenApparatListe = new ArrayList<>();
			String query = "SELECT * FROM Notat";
			PreparedStatement getOvelseUtenApparat = conn.prepareStatement(query);
			ResultSet results = getOvelseUtenApparat.executeQuery();
			while(results.next()) {
				int ovelseID = results.getInt("ovelseID");
				String beskrivelse = results.getString("beskrivelse");
				String opplevelse = results.getString("opplevelse");
				ovelseUtenApparatListe.add(new OvelseUtenApparat(ovelseID, beskrivelse));
			}
			return ovelseUtenApparatListe;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	//Main metode for å kjøre koden
	public static void main(String[] args) {
		//Får ikke koblet til databasen
		//Fikse SSL problem
		Queries queries = new Queries("jdbc:mysql://mysql.stud.ntnu.no:3306/didris_db?useSSL=false", "didris_db", "1234");
		System.out.println("Object made!");
		queries.setOvelseUtenApparat(1, "Tar litt push-ups da vell");
	}

}
