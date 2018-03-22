package com.group23.program;

import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;

import javax.management.Query;

public class Queries extends Database {


	public Queries(String address, String username, String password) {
		super(address, username, password);
	}


    /**
     * Genererer en komplett liste over alle treningsøkter i databasen
     * @return liste over treningsøkter og tilhørende notater
     * @throws SQLException hvis spørring har syntaxfeil eller ikke kan koble til databasne
     */
	public ArrayList<Pair<Treningsokt, Notat>> GetTreningsOktMedNotat() throws SQLException {
		ArrayList<Pair<Treningsokt, Notat>> list = new ArrayList<>();

		try (Connection connection = getConnection()) {

			String query = "SELECT *, Treningsokt.id AS TID, Notat.id AS NID " +
					"FROM Treningsokt " +
					"LEFT JOIN Notat ON Treningsokt.id = Notat.Treningsokt " +
					"ORDER BY Treningsokt.dato DESC";


			PreparedStatement preparedStatement = connection.prepareStatement(query);

			ResultSet results = preparedStatement.executeQuery();

			while (results.next()) {
				int TID = results.getInt("TID");
				String dato = results.getString("dato");
				int varighet = results.getInt("varighet");
				String informasjon = results.getString("informasjon");
				int personligForm = results.getInt("personligForm");
				int presentasjon = results.getInt("prestasjon");
				Treningsokt okt = new Treningsokt(TID, dato, varighet, informasjon, personligForm, presentasjon);

				int NID = results.getInt("TID");
				if(NID != 0) {
					int treningsokt = results.getInt("treningsokt");
					String treningsformaal = results.getString("treningsformaal");
					String opplevelse = results.getString("opplevelse");

					list.add(new Pair(okt, new Notat(NID, treningsokt, treningsformaal, opplevelse)));
				}
				else {
					list.add(new Pair(okt, null));
				}
			}

		} catch (SQLException e) {
			throw e;
		}
		return list;
	}

//
	/**
	 * Generer en komplett liste av alle resultat i databasen
	 * @return liste over resultat
	 */
	public Resultat GetResultat(int EID, int SID) {
		try (Connection connection = getConnection()) {
			
			ArrayList<Resultat> resultatListe = new ArrayList<>();
			String query = "SELECT * FROM Resultat WHERE Resultat.ovelse = ? AND Resultat.treningsokt = ?";

			PreparedStatement getResultat = connection.prepareStatement(query);

			getResultat.setInt(1, EID);
			getResultat.setInt(2, SID);

			ResultSet results = getResultat.executeQuery();
			while(results.next()) {
				int treningsokt = results.getInt("treningsokt");
				int ovelse = results.getInt("ovelse");
				int kilo = results.getInt("kilo");
				int sett = results.getInt("sett");
				int reps = results.getInt("reps");
				String informasjon = results.getString("informasjon");
				return new Resultat(treningsokt, ovelse,kilo, sett, reps, informasjon);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	/**
	 * Generer en liste ut ifra et intervall
	 * @param Start setter en start på intervallet
	 * @param Slutt setter en slutt på intervallet
	 * @return en liste med resultater i et satt intervall
	 * @throws SQLException
	 */
	public ArrayList<Resultat> resultatTidsInterval(String start, String slutt) throws SQLException {
		ArrayList<Resultat> list = new ArrayList<>(); 
	
		try (Connection connection = getConnection()) {
			String query = "SELECT Resultat.treningsokt, Resultat.ovelse, Resultat.kilo, Resultat.sett, Resultat.reps, Resultat.informasjon "+
					"FROM Resultat "+
					"JOIN Treningsokt ON Resultat.treningsokt = Treningsokt.id " +
					"WHERE dato BETWEEN ? AND ? ";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, start);
			preparedStatement.setString(2, slutt);

			ResultSet results = preparedStatement.executeQuery();

			while (results.next()) {
				int treningsokt = results.getInt("treningsokt");
				int ovelse = results.getInt("ovelse"); 
				int kilo = results.getInt("kilo"); 
				int sett = results.getInt("sett");  
				int reps = results.getInt("reps"); 
				String informasjon = results.getString("informasjon");

				list.add(new Resultat(treningsokt, ovelse, kilo, sett, reps, informasjon));
			}

		} catch (SQLException e) {
			throw e;
		}
		return list;
	}

	/**
	 * Generer en liste med navn på ovelser i ovelsesGruppe
	 * @param ovelsesGruppeID er ID til ovelsesGruppe
	 * @return en liste med navn på ovelser
	 */
	public ArrayList<String> getOvelseFromOvelsesGruppe(int ovelsesGruppeID) {
		try (Connection connection = getConnection()) {
			ArrayList<String> list = new ArrayList<>();
			String query = "SELECT * " + 
					"from OvelseIGruppe " + 
					"JOIN Ovelse ON Ovelse.id=OvelseIGruppe.ovelsesgruppe " + 
					"WHERE OvelseIGruppe.ovelse = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, ovelsesGruppeID);
			ResultSet results = preparedStatement.executeQuery();
			while(results.next()) {
				String navn = results.getString("navn");
				list.add(navn);
			}
			return list;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
}
