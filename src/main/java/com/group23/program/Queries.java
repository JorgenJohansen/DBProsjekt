package com.group23.program;

import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;

import javax.management.Query;

public class Queries extends Database {


	public Queries(String address, String username, String password) {
		super(address, username, password);
	}

//
//    /**
//     * Genererer en komplett liste over alle treningsapparater i databasen
//     * @return liste over treningsapparater
//     * @throws SQLException hvis spørring har syntaxfeil eller ikke kan koble til databasne
//     */
//	public ArrayList<Apparat> getApparat() throws SQLException {
//		ArrayList<Apparat> list = new ArrayList<>();
//
//		try (Connection connection = getConnection()) {
//
//			String query = "SELECT * FROM Apparat";
//
//			PreparedStatement preparedStatement = connection.prepareStatement(query);
//
//			ResultSet results = preparedStatement.executeQuery();
//
//			while (results.next()) {
//				int id = results.getInt("id");
//				String navn = results.getString("navn");
//				String beskrivelse = results.getString("beskrivelse");
//
//				list.add(new Apparat(id, navn, beskrivelse));
//			}
//
//		} catch (SQLException e) {
//			throw e;
//		}
//		return list;
//	}


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
				String personligForm = results.getString("personligForm");
				String presentasjon = results.getString("prestasjon");
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
//    /**
//     * Genererer en komplett liste over alle øvelser på treningsapparater i databasen
//     * @return liste over øvelse på treningsapparater
//     * @throws SQLException hvis spørring har syntaxfeil eller ikke kan koble til databasne
//     */
//	public ArrayList<OvelsePaaApparat> GetOvelsePaApparat() throws SQLException {
//		ArrayList<OvelsePaaApparat> list = new ArrayList<>();
//
//		try (Connection connection = getConnection()) {
//
//			String query = "SELECT id, navn, beskrivelse " +
//					"FROM OvelsePaaApparat " +
//					"INNER JOIN Ovelse ON Ovelse.id = OvelsePaaApparat.ovelseId";
//
//			PreparedStatement preparedStatement = connection.prepareStatement(query);
//
//			ResultSet results = preparedStatement.executeQuery();
//
//			while (results.next()) {
//				int id = results.getInt("id");
//				String navn = results.getString("navn");
//				int apparat = results.getInt("apparat");
//				String bruksinformasjon = results.getString("bruksinformasjon");
//
//				list.add(new OvelsePaaApparat(id, navn, apparat, bruksinformasjon));
//			}
//
//		} catch (SQLException e) {
//			throw e;
//		}
//		return list;
//	}
//
//
//    /**
//     * Genererer en komplett liste over alle øvelser uten apparat i databasen
//     * @return liste over øvelser uten apparat
//     * @throws SQLException hvis spørring har syntaxfeil eller ikke kan koble til databasne
//     */
//	public ArrayList<OvelseUtenApparat> GetOvelseUtenApparat() throws SQLException {
//		ArrayList<OvelseUtenApparat> list = new ArrayList<>();
//
//		try (Connection connection = getConnection()) {
//
//			String query = "SELECT id, navn, beskrivelse " +
//					"FROM OvelsePaaApparat " +
//					"INNER JOIN Ovelse ON Ovelse.id = OvelseUtenApparat.ovelseId";
//
//			PreparedStatement preparedStatement = connection.prepareStatement(query);
//
//			ResultSet results = preparedStatement.executeQuery();
//
//			while (results.next()) {
//				int id = results.getInt("id");
//				String navn = results.getString("navn");
//				String beskrivelse = results.getString("beskrivelse");
//
//				list.add(new OvelseUtenApparat(id, navn, beskrivelse));
//			}
//
//		} catch (SQLException e) {
//			throw e;
//		}
//		return list;
//
//	}


}
