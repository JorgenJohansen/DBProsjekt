package com.group23.program;

import java.sql.*;
import java.util.ArrayList;

import javax.management.Query;

public class Queries extends Database {


	public Queries(String address, String username, String password) {
		super(address, username, password);
	}


    /**
     * Genererer en komplett liste over alle treningsapparater i databasen
     * @return liste over treningsapparater
     * @throws SQLException hvis spørring har syntaxfeil eller ikke kan koble til databasne
     */
	public ArrayList<Apparat> getApparat() throws SQLException {
		ArrayList<Apparat> list = new ArrayList<>();

		try (Connection connection = getConnection()) {

			String query = "SELECT * FROM Apparat";

			PreparedStatement preparedStatement = connection.prepareStatement(query);

			ResultSet results = preparedStatement.executeQuery();

			while (results.next()) {
				int id = results.getInt("id");
				String navn = results.getString("navn");
				String beskrivelse = results.getString("beskrivelse");

				list.add(new Apparat(id, navn, beskrivelse));
			}

		} catch (SQLException e) {
			throw e;
		}
		return list;
	}


    /**
     * Genererer en komplett liste over alle treningsøkter i databasen
     * @return liste over treningsøkter
     * @throws SQLException hvis spørring har syntaxfeil eller ikke kan koble til databasne
     */
	public ArrayList<Treningsokt> GetTreningsOkt() throws SQLException {
		ArrayList<Treningsokt> list = new ArrayList<>();

		try (Connection connection = getConnection()) {

			String query = "SELECT * FROM treningsokt";

			PreparedStatement preparedStatement = connection.prepareStatement(query);

			ResultSet results = preparedStatement.executeQuery();

			while (results.next()) {
				int id = results.getInt("id");
				String dato = results.getString("dato");
				int varighet = results.getInt("varighet");
				String informasjon = results.getString("informasjon");
				int personligForm = results.getInt("personligForm");
				int prestasjon = results.getInt("presentasjon");

				list.add(new Treningsokt(id, dato, varighet, informasjon, personligForm, prestasjon));
			}

		} catch (SQLException e) {
			throw e;
		}
		return list;
	}


    /**
     * Genererer en komplett liste over alle øvelser på treningsapparater i databasen
     * @return liste over øvelse på treningsapparater
     * @throws SQLException hvis spørring har syntaxfeil eller ikke kan koble til databasne
     */
	public ArrayList<OvelsePaaApparat> GetOvelsePaApparat() throws SQLException {
		ArrayList<OvelsePaaApparat> list = new ArrayList<>();

		try (Connection connection = getConnection()) {

			String query = "SELECT id, navn, beskrivelse " +
					"FROM OvelsePaaApparat " +
					"INNER JOIN Ovelse ON Ovelse.id = OvelsePaaApparat.ovelseId";

			PreparedStatement preparedStatement = connection.prepareStatement(query);

			ResultSet results = preparedStatement.executeQuery();

			while (results.next()) {
				int id = results.getInt("id");
				int apparat = results.getInt("apparat");
				String navn = results.getString("navn");
				String bruksinformasjon = results.getString("bruksinformasjon");

				list.add(new OvelsePaaApparat(id, apparat, navn, bruksinformasjon));
			}

		} catch (SQLException e) {
			throw e;
		}
		return list;
	}


    /**
     * Genererer en komplett liste over alle øvelser uten apparat i databasen
     * @return liste over øvelser uten apparat
     * @throws SQLException hvis spørring har syntaxfeil eller ikke kan koble til databasne
     */
	public ArrayList<OvelseUtenApparat> GetOvelseUtenApparat() throws SQLException {
		ArrayList<OvelseUtenApparat> list = new ArrayList<>();

		try (Connection connection = getConnection()) {

			String query = "SELECT id, navn, beskrivelse " +
					"FROM OvelsePaaApparat " +
					"INNER JOIN Ovelse ON Ovelse.id = OvelseUtenApparat.ovelseId";

			PreparedStatement preparedStatement = connection.prepareStatement(query);

			ResultSet results = preparedStatement.executeQuery();

			while (results.next()) {
				int id = results.getInt("id");
				String navn = results.getString("navn");
				String beskrivelse = results.getString("beskrivelse");

				list.add(new OvelseUtenApparat(id, navn, beskrivelse));
			}

		} catch (SQLException e) {
			throw e;
		}
		return list;

	}


}
